package com.jmmy.jmmyapp.ui.rv_multi;

import androidx.annotation.NonNull;

import com.jmmy.mvvmhabit.base.BaseViewModel;
import com.jmmy.mvvmhabit.base.MultiItemViewModel;
import com.jmmy.mvvmhabit.binding.command.BindingAction;
import com.jmmy.mvvmhabit.binding.command.BindingCommand;
import com.jmmy.mvvmhabit.utils.ToastUtils;

public class MultiRecycleHeadViewModel extends MultiItemViewModel {

    public MultiRecycleHeadViewModel(@NonNull BaseViewModel viewModel) {
        super(viewModel);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("我是头布局");
        }
    });
}
