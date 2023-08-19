package com.jmmy.jmmyapp.ui.viewpager.vm;

import androidx.annotation.NonNull;

import com.jmmy.mvvmhabit.base.ItemViewModel;
import com.jmmy.mvvmhabit.binding.command.BindingAction;
import com.jmmy.mvvmhabit.binding.command.BindingCommand;

public class ViewPagerItemViewModel extends ItemViewModel<ViewPagerViewModel> {
    public String text;

    public ViewPagerItemViewModel(@NonNull ViewPagerViewModel viewModel, String text) {
        super(viewModel);
        this.text = text;
    }

    public BindingCommand onItemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //点击之后将逻辑转到activity中处理
            viewModel.itemClickEvent.setValue(text);
        }
    });
}
