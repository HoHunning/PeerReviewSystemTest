package com.example.demo.PeerReviewSystem;
import java.util.ArrayList;

public class Criteria {

    private String name;

    private ArrayList<Descriptor> descriptors = new ArrayList<>();


    public void addLevel(String level ,Double score  ,String descriptor){
        Level l = new Level();
        Descriptor d = new Descriptor();
        l.setContent(level ,score  ,d);
        d.setContent(this ,l , descriptor);

        this.descriptors.add(d);
    }

    public Criteria(String name) {
        this.name = name;
    }

    public ArrayList<Descriptor> getDescriptors() {
        return descriptors;
    }

    public String getName() {
        return name;
    }
}
