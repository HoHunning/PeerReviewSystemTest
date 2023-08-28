package com.example.demo.PeerReviewSystem;
import java.util.ArrayList;

public class Rubric {
    private Assignment assignment;
    private ArrayList<Criteria> criterion;


    public Rubric(Assignment assignment, ArrayList<Criteria> criterion) {
        this.assignment = assignment;
        this.criterion = criterion;
    }

    public ArrayList<Criteria> getCriterion() {
        return criterion;
    }

    public Assignment getAssignment() {
        return assignment;
    }
}
