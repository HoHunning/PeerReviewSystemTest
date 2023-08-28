package com.example.demo.PeerReviewSystem;
import java.util.Arrays;
import java.util.List;

public class Median implements ScoreStrategy{

    @Override
    public double getScore(List<Double> scores) {
        Double[] numArray = new Double[scores.size()];
        for(int i=0; i<scores.size(); i++){
            numArray[i] = scores.get(i);
        }
        Arrays.sort(numArray);
        if (numArray.length % 2 == 0)
            return ((double)numArray[numArray.length/2] + (double)numArray[numArray.length/2 - 1])/2;
        else
            return (double) numArray[numArray.length/2];
    }
}
