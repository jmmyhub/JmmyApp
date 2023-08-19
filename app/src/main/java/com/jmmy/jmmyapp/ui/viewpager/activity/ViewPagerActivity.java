package com.jmmy.jmmyapp.ui.viewpager.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.google.android.material.tabs.TabLayout;
import com.jmmy.jmmyapp.BR;
import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.databinding.FragmentViewpagerBinding;
import com.jmmy.jmmyapp.ui.viewpager.adapter.ViewPagerBindingAdapter;
import com.jmmy.jmmyapp.ui.viewpager.vm.ViewPagerViewModel;
import com.jmmy.mvvmhabit.base.BaseActivity;
import com.jmmy.mvvmhabit.utils.ToastUtils;

public class ViewPagerActivity extends BaseActivity<FragmentViewpagerBinding, ViewPagerViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.fragment_viewpager;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        // 使用 TabLayout 和 ViewPager 相关联
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
        //给ViewPager设置adapter
        binding.setAdapter(new ViewPagerBindingAdapter());
    }

    @Override
    public void initViewObservable() {
        viewModel.itemClickEvent.observe(this, text -> ToastUtils.showShort("position：" + text));
    }
}
