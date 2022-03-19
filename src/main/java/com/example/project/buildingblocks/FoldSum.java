package com.example.project.buildingblocks;

import com.example.project.datapipeline.BuildingBlock;
import com.example.project.datapipeline.DataPipelineBuilder;

import java.util.List;
import java.util.function.Consumer;

public class FoldSum implements BuildingBlock {

    private final Consumer<Integer> mCallback;

    public static DataPipelineBuilder.Factory<BuildingBlock> create() {
        return (callback) -> new FoldSum((Consumer<Integer>) callback);
    }

    FoldSum(Consumer<Integer> callback) {
        mCallback = callback;
    }

    void onReceive(List<Integer> nums) {
        mCallback.accept(sum(nums));
    }

    private static int sum(List<Integer> nums) {
        int sum = 0;
        for (int val : nums) {
            sum += val;
        }
        return sum;
    }

    @Override
    public Consumer<?> getInputFunc() {
        return (consumer) -> this.onReceive((List<Integer>) consumer);
    }
}
