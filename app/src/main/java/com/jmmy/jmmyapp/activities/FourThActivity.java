package com.jmmy.jmmyapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.adaptercontent.RecyclerViewAdapter;
import com.jmmy.jmmyapp.listeners.RecyclerViewPageChangeListenerHelper;
import com.jmmy.jmmyapp.utils.ContactsUtils;
import com.jmmy.jmmyapp.utils.MyContacts;
import com.jmmy.jmmysdk.utils.LogUtils;

import java.util.ArrayList;

public class FourThActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "FourThActivity";
    private RecyclerView mRecycleView;
    private Button mPrePage;
    private Button mNextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        initView();
    }

    private void initView() {
        mRecycleView = findViewById(R.id.recycle_view_fourth);
        mPrePage = findViewById(R.id.button_pre_page);
        mNextPage = findViewById(R.id.button_next_page);
        mPrePage.setOnClickListener(this::onClick);
        mNextPage.setOnClickListener(this::onClick);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, false);
        ArrayList<MyContacts> list = ContactsUtils.getAllContacts(this);
        adapter.setData(list);
        mRecycleView.setAdapter(adapter);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
            return super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
            }
        };
        pagerSnapHelper.attachToRecyclerView(mRecycleView);

        mRecycleView.addOnScrollListener(new RecyclerViewPageChangeListenerHelper(
            pagerSnapHelper, new RecyclerViewPageChangeListenerHelper.OnPageChangeListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            }

            @Override
            public void onPageSelected(int position) {
            }
        }));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_pre_page:
                LogUtils.i(TAG, "button_pre_page");
                break;
            case R.id.button_next_page:
                LogUtils.i(TAG, "button_next_page");
                break;
            default:
                break;
        }
    }
}