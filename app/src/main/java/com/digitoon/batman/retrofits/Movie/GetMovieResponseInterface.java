package com.digitoon.batman.retrofits.Movie;


import com.digitoon.batman.models.RequestOutput;

import java.util.concurrent.ExecutionException;

public interface GetMovieResponseInterface {
    public void updateUI(boolean response, String str
            , RequestOutput requestOutput) throws ExecutionException, InterruptedException;
}
