package com.jmmy.mvvmhabit.binding.command;

public interface BindingConsumer<T> {
    void call(T t);
}
