package com.example.project;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class FoldMedian {

    private final Consumer<Integer> mCallback;

    public FoldMedian(Consumer<Integer> callback) {
        mCallback = callback;
    }

    public void onReceive(List<Integer> nums) {
        mCallback.accept(calcMedian(nums));
    }

    private static int calcMedian(List<Integer> nums) {
        Collections.sort(nums);
        if (nums.size() % 2 == 1) {
            return nums.get(nums.size() / 2);
        }
        throw new UnsupportedOperationException("median of even number is not yet supported");
    }


}
