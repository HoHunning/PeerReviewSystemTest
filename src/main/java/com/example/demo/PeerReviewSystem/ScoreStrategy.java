package com.example.demo.PeerReviewSystem;
import java.util.List;

public interface ScoreStrategy {

    double getScore(List<Double> scores);
}
