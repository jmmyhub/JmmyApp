package com.jmmy.jmmyapp.ui.rv_multi;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.jmmy.mvvmhabit.base.MultiItemViewModel;
import com.jmmy.mvvmhabit.binding.command.BindingAction;
import com.jmmy.mvvmhabit.binding.command.BindingCommand;
import com.jmmy.mvvmhabit.utils.ToastUtils;

public class MultiRecycleLeftItemViewModel extends MultiItemViewModel<MultiRecycleViewModel> {
    public ObservableField<String> text = new ObservableField<>("");

    public MultiRecycleLeftItemViewModel(@NonNull MultiRecycleViewModel viewModel, String text) {
        super(viewModel);
        this.text.set(text);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //拿到position
            int position = viewModel.observableList.indexOf(MultiRecycleLeftItemViewModel.this);
            ToastUtils.showShort("position：" + position);
        }
    });
}
