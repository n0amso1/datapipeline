package com.example.project;

import java.util.List;
import java.util.function.Consumer;

public class FoldSum {

    private final Consumer<Integer> mCallback;

    public FoldSum(Consumer<Integer> callback) {
        mCallback = callback;
    }

    public void onReceive(List<Integer> nums) {
        mCallback.accept(sum(nums));
    }

    private static int sum(List<Integer> nums) {
        int sum = 0;
        for (int val : nums) {
            sum += val;
        }
        return sum;
    }
}
