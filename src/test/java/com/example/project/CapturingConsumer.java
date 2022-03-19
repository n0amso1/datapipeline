package com.example.project;

import java.util.function.Consumer;

public class CapturingConsumer<T> implements Consumer<T> {

    private T mVal;

    @Override
    public void accept(T t) {
        if (mVal != null) {
            throw new RuntimeException("This consumer has already captured a value," +
                    " probably a bug in your test");
        }
        mVal = t;
    }

    public T getCapturedValue() {
        return mVal;
    }
}
