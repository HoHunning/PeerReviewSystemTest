package com.example.demo.PeerReviewSystem;
public class Level {
    private String name ;
    private Double score ;
    private Descriptor descriptor ;

    public void setContent(String name, Double score, Descriptor descriptor ) {
        this.name = name;
        this.score = score;
        this.descriptor = descriptor;
    }

    public String getName() {
        return name;
    }

    public Double getScore() {
        return score;
    }
}
