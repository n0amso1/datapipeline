package com.example.project;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Filter {

    private final Predicate<Integer> mPredicate;
    private final Consumer<Integer> mCallback;

    public Filter(Predicate<Integer> predicate, Consumer<Integer> callback) {
        mPredicate = predicate;
        mCallback = callback;
    }

    public void onReceive(int num) {
        if (mPredicate.test(num)) {
            mCallback.accept(num);
        }
    }
}
