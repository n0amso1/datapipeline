package com.example.project.datapipeline;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

public class DataPipelineBuilder {

    public interface Factory<T extends BuildingBlock> {
        T createAndStreamOutputTo(Consumer<?> otherBlockInput);
    }

    private final Factory<StartingBuildingBlock> mStartingBlock;
    private final Deque<Factory<? extends BuildingBlock>> mOtherBlocks = new ArrayDeque<>();

    public static DataPipelineBuilder startingWith(Factory<StartingBuildingBlock> creatorFunc) {
        return new DataPipelineBuilder(creatorFunc);
    }

    DataPipelineBuilder(Factory<StartingBuildingBlock> startingBlock) {
        mStartingBlock = startingBlock;
    }

    public DataPipelineBuilder andThen(Factory<? extends BuildingBlock> creatorFunc) {
        mOtherBlocks.add(creatorFunc);
        return this;
    }

    public DataPipeline andFinally(Consumer<?> resultCallback) {
        if (mOtherBlocks.size() == 0) {
            var startingBlock = mStartingBlock.createAndStreamOutputTo(resultCallback);
            return new DataPipeline(startingBlock);
        }

        var lastBlockFactory = mOtherBlocks.pollLast();
        var afterBlock = lastBlockFactory.createAndStreamOutputTo(resultCallback);
        while (mOtherBlocks.size() > 0) {
            var beforeBlock = mOtherBlocks.pollLast();
            afterBlock = beforeBlock.createAndStreamOutputTo(afterBlock.getInputFunc());
        }
        var startingBlock = mStartingBlock.createAndStreamOutputTo(afterBlock.getInputFunc());
        return new DataPipeline(startingBlock);
    }

}
