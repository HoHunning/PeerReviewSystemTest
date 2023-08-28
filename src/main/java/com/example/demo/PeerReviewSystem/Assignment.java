package com.example.demo.PeerReviewSystem;

import java.util.ArrayList;

public class Assignment {

    private String assignmentId;
    private ArrayList<Homework> homeworks = new ArrayList<>();
    private Rubric rubric ;

    public Assignment(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void addHomework(Homework homework) {
        homeworks.add(homework);
    }

    public ArrayList<Homework> getHomeworks() {
        return homeworks;
    }

    public void setRubric(Rubric rubric) {
        this.rubric = rubric;
    }

    public Rubric getRubric() {
        return rubric;
    }
}
