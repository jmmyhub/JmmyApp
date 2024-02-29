package com.jmmy.jmmyapp.ble;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.jmmy.jmmyapp.data.DemoRepository;
import com.jmmy.mvvmhabit.base.BaseViewModel;
import com.jmmy.mvvmhabit.binding.command.BindingAction;
import com.jmmy.mvvmhabit.binding.command.BindingCommand;
import com.jmmy.mvvmhabit.bus.event.SingleLiveEvent;
import com.jmmy.mvvmhabit.utils.LogUtil;

public class BLEViewModel extends BaseViewModel {
    private static final String TAG = "BLEViewModel: ";
    public ObservableField<String> tv_cur_con_state = new ObservableField<>("");
    public ObservableField<String> tv_name = new ObservableField<>("");
    public ObservableField<String> tv_address = new ObservableField<>("");
    public ObservableField<String> et_send_msg = new ObservableField<>("");
    public ObservableField<String> tv_send_result = new ObservableField<>("");
    public ObservableField<String> tv_receive_result = new ObservableField<>("");

    public UIChangeObservable uc = new UIChangeObservable();

    public final BindingCommand bt_search = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            LogUtil.i(TAG, "call bt_search begin search.");
        }
    });
    public final BindingCommand bt_connect = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            LogUtil.i(TAG, "call bt_search begin search.");
        }
    });

    public final BindingCommand bt_disconnect = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            LogUtil.i(TAG, "call bt_disconnect begin disconnect.");
        }
    });

    public final BindingCommand bt_to_send = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            LogUtil.i(TAG, "call bt_to_send begin to send.");
        }
    });

    public class UIChangeObservable {
        //密码开关观察者
        public SingleLiveEvent<Boolean> bt_search = new SingleLiveEvent<>();
        public SingleLiveEvent<Boolean> bt_connect = new SingleLiveEvent<>();
        public SingleLiveEvent<Boolean> bt_disconnect = new SingleLiveEvent<>();
        public SingleLiveEvent<Boolean> bt_to_send = new SingleLiveEvent<>();
    }

    public BLEViewModel(@NonNull Application application) {
        super(application);
    }

    public BLEViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
    }
}
