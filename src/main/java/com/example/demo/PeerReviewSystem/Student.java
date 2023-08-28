package com.example.demo.PeerReviewSystem;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Student {
    private String StudentId;
    private ArrayList<Homework> homeworks = new ArrayList<>();

    public Student(String ID){
        this.StudentId = ID;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void addHomework(Homework homework) {
        homeworks.add(homework);
    }

    public ArrayList<Homework> getHomeworks() {
        return homeworks;
    }

    public void review(String file , Homework homework , ArrayList<Criteria> criterion , HashMap<String,Double> schoolStrategy){
        Scanner myInput = null;
        try {
            File myObj = new File(file);
            myInput = new Scanner(myObj);
        } catch (FileNotFoundException r) {
            System.out.println("An error occurred.");
            r.printStackTrace();
        }
        for(int i = 0 ; i < criterion.size() ; i++ ){

            Double score = schoolStrategy.get(myInput.next());
            homework.addScore(criterion.get(i),score);

        }


    }
}
