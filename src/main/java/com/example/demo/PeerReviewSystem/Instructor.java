package com.example.demo.PeerReviewSystem;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Instructor {

    List<Student> students = new ArrayList<>();
    List<Assignment> assignments = new ArrayList<>();
    ScoreStrategy scoreStrategy;

    Instructor(){}
    Instructor(ArrayList<Student> students, ArrayList<Assignment> assignments){
        this.students = students;
        this.assignments = assignments;
    }
    public void setReviewers(HashMap<String ,String > reviewers , Student student , String assignmentId , HashMap<String,Double> schoolStrategy){
        Homework homework = null;
        for(Homework h : student.getHomeworks() ){     //find Homework
            if(h.getAssignment().getAssignmentId().equals(assignmentId)) {
                homework = h;
                break;
            }
        }
        ArrayList<Criteria> criterion = homework.getAssignment().getRubric().getCriterion();

        for(Map.Entry<String ,String> e: reviewers.entrySet()){ //for all reviewer
            Student reviewer  = null;
            for (Student s :this.students){ //find the reviewer
                if(e.getKey().equals( s.getStudentId())){
                    reviewer = s;
                }
            }
            if(reviewer == null ){
                System.out.println("Error");
                continue;
            }
            reviewer.review(e.getValue() , homework, criterion , schoolStrategy);
            System.out.print(assignmentId +"-"+student.getStudentId()+" was reviewed by "+reviewer.getStudentId()+". Level: ");
            Scanner myInput = null;
            try {
                File myObj = new File(e.getValue());
                myInput = new Scanner(myObj);
            } catch (FileNotFoundException r) {
                System.out.println("An error occurred.");
                r.printStackTrace();
            }
            for(int i =0; i< criterion.size() ; i++){
                System.out.print(myInput.next());
                if(i != criterion.size()-1)
                    System.out.print(" ");
            }
            System.out.println();
        }



    }

    public void design(Assignment assignment, HashMap<String,Double> schoolStrategy, ArrayList<String > designCriteria ){
        RubricBuilder rubricBuilder = new ConcreteRubricBuilder();
        rubricBuilder.setAssignment(assignment).setSchoolStrategy(schoolStrategy);
        rubricBuilder.setCriteria(designCriteria);
        assignment.setRubric(rubricBuilder.build());
    }

    public void setStrategy(ScoreStrategy scoreStrategy){
        this.scoreStrategy = scoreStrategy;
    }
    public void calculateScore(String studentID, String assignmentID){
        double finalScore = 0;
        Student targetStudent = null;
        Homework targetHomework = null;
        for(Student s : students){
            if(studentID.equals(s.getStudentId())){
                targetStudent = s;
            }
        }
        if(targetStudent == null){
            System.out.println("Error");
            return;
        }
        for(Homework h : targetStudent.getHomeworks()) {
            if (h.getAssignment().getAssignmentId().equals(assignmentID)) {
                targetHomework = h;
            }
        }
        if(targetHomework == null){
            System.out.println("Error");
            return;
        }
        if(!targetHomework.isGraded()){
            System.out.println("Assignment: "+assignmentID+", Student: "+studentID+", Score: " + String.format("%.1f",0.0));
            return;
        }

        double sum = 0.0;
        for(Criteria c: targetHomework.getAssignment().getRubric().getCriterion()){
            ArrayList<Double> score = targetHomework.getScores().get(c.getName());

            sum += scoreStrategy.getScore(score);
        }

        finalScore = sum/Double.valueOf(targetHomework.getAssignment().getRubric().getCriterion().size());
        System.out.println("Assignment: "+assignmentID+", Student: "+studentID+", Score: " + String.format("%.1f",finalScore));
    }




    public void findStrength(String studentID, String assignmentID){
        Student targetStudent = null;
        Homework targetHomework = null;
        for(Student s : students){
            if(studentID.equals(s.getStudentId())){
                targetStudent = s;
            }
        }
        if(targetStudent==null){
            System.out.println("Error");
            return;
        }
        for(Homework h : targetStudent.getHomeworks()) {
            if (h.getAssignment().getAssignmentId().equals(assignmentID)) {
                targetHomework = h;
            }
        }
        if(targetHomework==null){
            System.out.println("Error");
            return;
        }
        if(!targetHomework.isGraded()){
            System.out.println("Error");
            return;
        }

        double tempMax = 0;
        ArrayList<String> strength = null;
        for(Criteria c: targetHomework.getAssignment().getRubric().getCriterion()){
            //for all criteria in a homework, find the {strategy } in that criteria
            //and print the maximum score of all criteria
            ArrayList<Double> score = targetHomework.getScores().get(c.getName());

            if(tempMax<= scoreStrategy.getScore(score)){
                if(tempMax < scoreStrategy.getScore(score)){
                    strength = new ArrayList<>();
                    tempMax = scoreStrategy.getScore(score);
                    strength.add(c.getName());
                }
                else{
                    strength.add(c.getName());
                }

            }

        }


        System.out.print("Assignment: "+assignmentID+", Student: "+studentID+", Strength: " );
        for(int i =0 ;i < strength.size();i++){
            System.out.print(strength.get(i));
            if(i != strength.size() -1)
                System.out.print(" ");
        }
        System.out.println();


    }

    public void findWeakness(String studentID, String assignmentID){
        Student targetStudent = null;
        Homework targetHomework = null;
        for(Student s : students){
            if(studentID.equals(s.getStudentId())){
                targetStudent = s;
            }
        }
        if(targetStudent==null){
            System.out.println("Error");
            return;
        }
        for(Homework h : targetStudent.getHomeworks()) {
            if (h.getAssignment().getAssignmentId().equals(assignmentID)) {
                targetHomework = h;
            }
        }
        if(targetHomework==null){
            System.out.println("Error");
            return;
        }
        if(!targetHomework.isGraded()){
            System.out.println("Error");
            return;
        }
        double tempMax = 1000;
        ArrayList<String> weakness = null;
        for(Criteria c: targetHomework.getAssignment().getRubric().getCriterion()){
            //for all criteria in a homework, find the {strategy } in that criteria
            //and print the maximum score of all criteria
            ArrayList<Double> score = targetHomework.getScores().get(c.getName());
            if(tempMax>= scoreStrategy.getScore(score)){
                if(tempMax > scoreStrategy.getScore(score)){
                    weakness = new ArrayList<>();
                    tempMax = scoreStrategy.getScore(score);
                    weakness.add(c.getName());
                }
                else{
                    weakness.add(c.getName());
                }
            }
        }

        System.out.print("Assignment: "+assignmentID+", Student: "+studentID+", Weakness: ");
        for(int i =0 ;i < weakness.size();i++){
            System.out.print(weakness.get(i));
            if(i != weakness.size() -1)
                System.out.print(" ");
        }
        System.out.println();
    }
    public void averageCriterion(String AssignmentId){
        for(Assignment assignment : assignments){
            if(AssignmentId.equals(assignment.getAssignmentId())){
                for(Criteria criteria : assignment.getRubric().getCriterion()){
                    //對於每個 criteria，去對他的每個功課先平均之後取作業平均分數
                    double allHomeworkScore = 0;
                    int count =0;
                    for(Homework hw : assignment.getHomeworks()) {
                        if(!hw.isGraded())
                            continue;
                        for (Double i : hw.getScores().get(criteria.getName())) {
                            allHomeworkScore += i;
                            count++;
                        }
                    }
                    System.out.println("Assignment: "+ AssignmentId +
                            ", Criterion: " + criteria.getName()
                            + ", AvgScore: " + String.format("%.1f", allHomeworkScore / count));
                }
            }
        }
    }
}
