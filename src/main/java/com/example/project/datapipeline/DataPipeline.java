package com.example.project.datapipeline;

public class DataPipeline {
    private final StartingBuildingBlock mStartingBlock;


    public DataPipeline(StartingBuildingBlock startingBlock) {
        mStartingBlock = startingBlock;
    }

    public void onReceive(int num) {
        mStartingBlock.onReceive(num);
    }
}
