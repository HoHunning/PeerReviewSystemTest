package com.example.demo.PeerReviewSystem;
public class Descriptor {
    private Criteria criteria;
    private Level level ;
    private String content;


    public void setContent(Criteria criteria, Level level, String content) {
        this.criteria = criteria;
        this.level = level;
        this.content = content;
    }

    public Level getLevel() {
        return level;
    }

    public String getContent() {
        return content;
    }
}
