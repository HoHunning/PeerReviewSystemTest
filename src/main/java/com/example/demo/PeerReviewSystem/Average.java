package com.example.demo.PeerReviewSystem;
import java.util.List;

public class Average implements ScoreStrategy{

    @Override
    public double getScore(List<Double> scores) {
        double temp = 0;
        for(Double score : scores){
            temp+= Double.valueOf(score);
        }
        return temp/ Double.valueOf(scores.size());
    }
}
