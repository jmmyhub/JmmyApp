package com.jmmy.jmmyapp.ble;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.jmmy.jmmyapp.AppViewModelFactory;
import com.jmmy.jmmyapp.BR;
import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.ble.blemanager.BLEManager;
import com.jmmy.jmmyapp.ble.blemanager.OnBleConnectListener;
import com.jmmy.jmmyapp.ble.blemanager.OnDeviceSearchListener;
import com.jmmy.jmmyapp.ble.permission.PermissionListener;
import com.jmmy.jmmyapp.ble.permission.PermissionRequest;
import com.jmmy.jmmyapp.ble.util.TypeConversion;
import com.jmmy.jmmyapp.databinding.FragmentBleBinding;
import com.jmmy.mvvmhabit.base.BaseFragment;
import com.jmmy.mvvmhabit.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class BLEFragment extends BaseFragment<FragmentBleBinding, BLEViewModel> {
    private static final String TAG = "BLEFragment:";

    //bt_patch(mtu).bin
    public static final String SERVICE_UUID = "49535343-fe7d-4ae5-8fa9-9fafd205e455";  //蓝牙通讯服务
    public static final String READ_UUID = "49535343-1e4d-4bd9-ba61-23c647249616";  //读特征
    public static final String WRITE_UUID = "49535343-8841-43f4-a8d4-ecbe34729bb3";  //写特征

    //动态申请权限
    private String[] requestPermissionArray = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    // 声明一个集合，在后面的代码中用来存储用户拒绝授权的权限
    private List<String> deniedPermissionList = new ArrayList<>();

    private static final int CONNECT_SUCCESS = 0x01;
    private static final int CONNECT_FAILURE = 0x02;
    private static final int DISCONNECT_SUCCESS = 0x03;
    private static final int SEND_SUCCESS = 0x04;
    private static final int SEND_FAILURE = 0x05;
    private static final int RECEIVE_SUCCESS = 0x06;
    private static final int RECEIVE_FAILURE = 0x07;
    private static final int START_DISCOVERY = 0x08;
    private static final int STOP_DISCOVERY = 0x09;
    private static final int DISCOVERY_DEVICE = 0x0A;
    private static final int DISCOVERY_OUT_TIME = 0x0B;
    private static final int SELECT_DEVICE = 0x0C;
    private static final int BT_OPENED = 0x0D;
    private static final int BT_CLOSED = 0x0E;
    private LVDevicesAdapter lvDevicesAdapter;
    private Context mContext;
    private BLEManager bleManager;
    private BLEBroadcastReceiver bleBroadcastReceiver;
    private BluetoothDevice curBluetoothDevice;  //当前连接的设备
    //当前设备连接状态
    private boolean curConnState = false;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getContext();
        return R.layout.fragment_ble;
    }

    @Override
    public BLEViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(BLEViewModel.class);
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START_DISCOVERY:
                    LogUtil.d(TAG, "开始搜索设备...");
                    break;

                case STOP_DISCOVERY:
                    LogUtil.d(TAG, "停止搜索设备...");
                    break;

                case DISCOVERY_DEVICE:  //扫描到设备
                    BLEDevice bleDevice = (BLEDevice) msg.obj;
                    lvDevicesAdapter.addDevice(bleDevice);

                    break;

                case SELECT_DEVICE:
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) msg.obj;
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    binding.tvName.setText(bluetoothDevice.getName());
                    binding.tvAddress.setText(bluetoothDevice.getAddress());
                    curBluetoothDevice = bluetoothDevice;
                    break;

                case CONNECT_FAILURE: //连接失败
                    LogUtil.d(TAG, "连接失败");
                    binding.tvCurConState.setText("连接失败");
                    curConnState = false;
                    break;

                case CONNECT_SUCCESS:  //连接成功
                    LogUtil.d(TAG, "连接成功");
                    binding.tvCurConState.setText("连接成功");
                    curConnState = true;
                    binding.llDataSendReceive.setVisibility(View.VISIBLE);
                    binding.llDeviceList.setVisibility(View.GONE);
                    break;

                case DISCONNECT_SUCCESS:
                    LogUtil.d(TAG, "断开成功");
                    binding.tvCurConState.setText("断开成功");
                    curConnState = false;

                    break;

                case SEND_FAILURE: //发送失败
                    byte[] sendBufFail = (byte[]) msg.obj;
                    String sendFail = TypeConversion.bytes2HexString(sendBufFail,sendBufFail.length);
                    binding.tvSendResult.setText("发送数据失败，长度" + sendBufFail.length + "--> " + sendFail);
                    break;

                case SEND_SUCCESS:  //发送成功
                    byte[] sendBufSuc = (byte[]) msg.obj;
                    String sendResult = TypeConversion.bytes2HexString(sendBufSuc,sendBufSuc.length);
                    binding.tvSendResult.setText("发送数据成功，长度" + sendBufSuc.length + "--> " + sendResult);
                    break;

                case RECEIVE_FAILURE: //接收失败
                    String receiveError = (String) msg.obj;
                    binding.tvReceiveResult.setText(receiveError);
                    break;

                case RECEIVE_SUCCESS:  //接收成功
                    byte[] recBufSuc = (byte[]) msg.obj;
                    String receiveResult = TypeConversion.bytes2HexString(recBufSuc,recBufSuc.length);
                    binding.tvReceiveResult.setText("接收数据成功，长度" + recBufSuc.length + "--> " + receiveResult);
                    break;

                case BT_CLOSED:
                    LogUtil.d(TAG, "系统蓝牙已关闭");
                    break;

                case BT_OPENED:
                    LogUtil.d(TAG, "系统蓝牙已打开");
                    break;
            }
        }
    };

    @Override
    public void initViewObservable() {
        LogUtil.i(TAG, "init view observable.");
        //初始化监听
        initListener();
        //检测BLE
        checkBleManager();
        //注册广播
        initBLEBroadcastReceiver();
        //初始化权限
        initPermissions();
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        LogUtil.i(TAG, "initListener viewmodel: " + viewModel + ", binding:" + binding);

        binding.btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.i(TAG, "initListener bt search");
            }
        });

        binding.btConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.i(TAG, "initListener bt connect");
            }
        });

        binding.btDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.i(TAG, "initListener bt dicconnect");
            }
        });

        binding.btToSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.i(TAG, "initListener bt to send");
            }
        });

        viewModel.uc.bt_search.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                LogUtil.i(TAG, "onChanged bt_search begin to search.");
                binding.llDataSendReceive.setVisibility(View.GONE);
                binding.llDeviceList.setVisibility(View.VISIBLE);
                searchBtDevice();
            }
        });
        viewModel.uc.bt_connect.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                LogUtil.i(TAG, "onChanged bt_connect begin to connect.");
                if(!curConnState) {
                    if(bleManager != null){
                        bleManager.connectBleDevice(mContext,curBluetoothDevice,15000,SERVICE_UUID,READ_UUID,WRITE_UUID,onBleConnectListener);
                    }
                }else{
                    Toast.makeText(mContext, "当前设备已连接", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.uc.bt_disconnect.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                LogUtil.i(TAG, "onChanged bt_disconnect begin to disconnect.");
                if(curConnState) {
                    if(bleManager != null){
                        bleManager.disConnectDevice();
                    }
                }else{
                    Toast.makeText(mContext, "当前设备未连接", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.uc.bt_to_send.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                LogUtil.i(TAG, "onChanged bt_to_send begin to send.");
                if(curConnState){
                    String sendMsg = binding.etSendMsg.getText().toString();
                    if(sendMsg.isEmpty()){
                        Toast.makeText(mContext, "发送数据为空！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(bleManager != null) {
                        bleManager.sendMessage(sendMsg);  //以16进制字符串形式发送数据
                    }
                }else{
                    Toast.makeText(mContext, "请先连接当前设备", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.lvDevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BLEDevice bleDevice = (BLEDevice) lvDevicesAdapter.getItem(i);
                BluetoothDevice bluetoothDevice = bleDevice.getBluetoothDevice();
                if(bleManager != null){
                    bleManager.stopDiscoveryDevice();
                }
                Message message = new Message();
                message.what = SELECT_DEVICE;
                message.obj = bluetoothDevice;
                mHandler.sendMessage(message);
            }
        });
    }

    /**
     * 初始化数据
     */
    public void checkBleManager() {
        //列表适配器
        lvDevicesAdapter = new LVDevicesAdapter(mContext);
        binding.lvDevices.setAdapter(lvDevicesAdapter);

        //初始化ble管理器
        bleManager = new BLEManager();
        if(!bleManager.initBle(mContext)) {
            LogUtil.d(TAG, "该设备不支持低功耗蓝牙");
            Toast.makeText(mContext, "该设备不支持低功耗蓝牙", Toast.LENGTH_SHORT).show();
        } else {
            LogUtil.i(TAG, "init view ble enable:" + bleManager.isEnable());
            if(!bleManager.isEnable()){
                //去打开蓝牙
                bleManager.openBluetooth(mContext,false);
            }
        }
    }


    /**
     * 注册广播
     */
    private void initBLEBroadcastReceiver() {
        //注册广播接收
        bleBroadcastReceiver = new BLEBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED); //开始扫描
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);//扫描结束
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);//手机蓝牙状态监听
        mContext.registerReceiver(bleBroadcastReceiver,intentFilter);
    }

    /**
     * 初始化权限
     */
    private void initPermissions() {
        //Android 6.0以上动态申请权限
        final PermissionRequest permissionRequest = new PermissionRequest();
        permissionRequest.requestRuntimePermission(mContext, requestPermissionArray, new PermissionListener() {
            @Override
            public void onGranted() {
                LogUtil.d(TAG,"所有权限已被授予");
            }

            //用户勾选“不再提醒”拒绝权限后，关闭程序再打开程序只进入该方法！
            @Override
            public void onDenied(List<String> deniedPermissions) {
                deniedPermissionList = deniedPermissions;
                for (String deniedPermission : deniedPermissionList) {
                    LogUtil.e(TAG,"被拒绝权限：" + deniedPermission);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //注销广播接收
        mContext.unregisterReceiver(bleBroadcastReceiver);
    }

    //////////////////////////////////  搜索设备  /////////////////////////////////////////////////
    private void searchBtDevice() {
        if(bleManager == null){
            LogUtil.d(TAG, "searchBtDevice()-->bleManager == null");
            return;
        }

        if (bleManager.isDiscovery()) { //当前正在搜索设备...
            bleManager.stopDiscoveryDevice();
        }

        if(lvDevicesAdapter != null){
            lvDevicesAdapter.clear();  //清空列表
        }

        //开始搜索
        bleManager.startDiscoveryDevice(onDeviceSearchListener,15000);
    }

    //扫描结果回调
    private OnDeviceSearchListener onDeviceSearchListener = new OnDeviceSearchListener() {

        @Override
        public void onDeviceFound(BLEDevice bleDevice) {
            Message message = new Message();
            message.what = DISCOVERY_DEVICE;
            message.obj = bleDevice;
            mHandler.sendMessage(message);
        }

        @Override
        public void onDiscoveryOutTime() {
            Message message = new Message();
            message.what = DISCOVERY_OUT_TIME;
            mHandler.sendMessage(message);
        }
    };

    //连接回调
    private OnBleConnectListener onBleConnectListener = new OnBleConnectListener() {
        @Override
        public void onConnecting(BluetoothGatt bluetoothGatt, BluetoothDevice bluetoothDevice) {

        }

        @Override
        public void onConnectSuccess(BluetoothGatt bluetoothGatt, BluetoothDevice bluetoothDevice, int status) {
            //因为服务发现成功之后，才能通讯，所以在成功发现服务的地方表示连接成功
        }

        @Override
        public void onConnectFailure(BluetoothGatt bluetoothGatt, BluetoothDevice bluetoothDevice, String exception, int status) {
            Message message = new Message();
            message.what = CONNECT_FAILURE;
            mHandler.sendMessage(message);
        }

        @Override
        public void onDisConnecting(BluetoothGatt bluetoothGatt, BluetoothDevice bluetoothDevice) {

        }

        @Override
        public void onDisConnectSuccess(BluetoothGatt bluetoothGatt, BluetoothDevice bluetoothDevice, int status) {
            Message message = new Message();
            message.what = DISCONNECT_SUCCESS;
            message.obj = status;
            mHandler.sendMessage(message);
        }

        @Override
        public void onServiceDiscoverySucceed(BluetoothGatt bluetoothGatt, BluetoothDevice bluetoothDevice, int status) {
            //因为服务发现成功之后，才能通讯，所以在成功发现服务的地方表示连接成功
            Message message = new Message();
            message.what = CONNECT_SUCCESS;
            mHandler.sendMessage(message);
        }

        @Override
        public void onServiceDiscoveryFailed(BluetoothGatt bluetoothGatt, BluetoothDevice bluetoothDevice, String failMsg) {
            Message message = new Message();
            message.what = CONNECT_FAILURE;
            mHandler.sendMessage(message);
        }

        @Override
        public void onReceiveMessage(BluetoothGatt bluetoothGatt, BluetoothDevice bluetoothDevice, BluetoothGattCharacteristic characteristic, byte[] msg) {
            Message message = new Message();
            message.what = RECEIVE_SUCCESS;
            message.obj = msg;
            mHandler.sendMessage(message);
        }

        @Override
        public void onReceiveError(String errorMsg) {
            Message message = new Message();
            message.what = RECEIVE_FAILURE;
            mHandler.sendMessage(message);
        }

        @Override
        public void onWriteSuccess(BluetoothGatt bluetoothGatt, BluetoothDevice bluetoothDevice, byte[] msg) {
            Message message = new Message();
            message.what = SEND_SUCCESS;
            message.obj = msg;
            mHandler.sendMessage(message);
        }

        @Override
        public void onWriteFailure(BluetoothGatt bluetoothGatt, BluetoothDevice bluetoothDevice, byte[] msg, String errorMsg) {
            Message message = new Message();
            message.what = SEND_FAILURE;
            message.obj = msg;
            mHandler.sendMessage(message);
        }

        @Override
        public void onReadRssi(BluetoothGatt bluetoothGatt, int Rssi, int status) {

        }

        @Override
        public void onMTUSetSuccess(String successMTU, int newMtu) {

        }

        @Override
        public void onMTUSetFailure(String failMTU) {

        }
    };


    /**
     * 蓝牙广播接收器
     */
    private class BLEBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.equals(action, BluetoothAdapter.ACTION_DISCOVERY_STARTED)) { //开启搜索
                Message message = new Message();
                message.what = START_DISCOVERY;
                mHandler.sendMessage(message);

            } else if (TextUtils.equals(action, BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {//完成搜素
                Message message = new Message();
                message.what = STOP_DISCOVERY;
                mHandler.sendMessage(message);

            } else if(TextUtils.equals(action,BluetoothAdapter.ACTION_STATE_CHANGED)){   //系统蓝牙状态监听

                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,0);
                if(state == BluetoothAdapter.STATE_OFF){
                    Message message = new Message();
                    message.what = BT_CLOSED;
                    mHandler.sendMessage(message);

                }else if(state == BluetoothAdapter.STATE_ON){
                    Message message = new Message();
                    message.what = BT_OPENED;
                    mHandler.sendMessage(message);

                }
            }
        }
    }

}
