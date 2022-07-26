package com.digitoon.batman.retrofits.Detail;

import com.digitoon.batman.room.detail.DetailRoom;

import java.util.concurrent.ExecutionException;

public interface GetDetailResponseInterface {
    public void updateUI(boolean response, String str
            , DetailRoom detailOutput) throws
            ExecutionException, InterruptedException;
}
