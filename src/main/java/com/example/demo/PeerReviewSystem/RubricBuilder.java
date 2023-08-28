package com.example.demo.PeerReviewSystem;
import java.util.ArrayList;
import java.util.HashMap;

public interface RubricBuilder {
    public RubricBuilder setSchoolStrategy(HashMap<String,Double> schoolStretagy);
    public RubricBuilder setCriteria(ArrayList<String> criteria);
    public RubricBuilder setAssignment(Assignment assignment);
    public Rubric build();
}
