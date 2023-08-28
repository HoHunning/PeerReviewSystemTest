package com.example.demo.PeerReviewSystem;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Homework {
    private Student writer = null;
    private Assignment assignment = null;
    private HashMap<String, ArrayList<Double>> scores = new HashMap<String, ArrayList<Double>>();
    private boolean graded = false;

    public Homework(Student writer, Assignment assignment) {
        this.writer = writer;
        this.assignment = assignment;
    }

    public Student getWriter() {
        return writer;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public HashMap<String, ArrayList<Double>> getScores() {
        return scores;
    }

    public void addScore(Criteria criteria,Double  score ){
        if(!scores.containsKey(criteria.getName())){
            ArrayList<Double> score1 = new ArrayList<>( );
            score1.add(score);
            scores.put(criteria.getName(),score1);
            graded = true;
            return;
        }
        scores.get(criteria.getName()).add(score);
        graded = true;
    }
    public boolean isGraded() {
        return graded;
    }

}