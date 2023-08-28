package com.example.demo.PeerReviewSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PeerReviewSystem {

    public void CommandHandler(String path) {
        Scanner myInput = null;
        Scanner criterionInput = null;
        // initialize variables
        ArrayList<Student> students = new ArrayList<>();
        HashMap<String, Double> schoolStrategy = new HashMap<>();
        ArrayList<Assignment> assignments = new ArrayList<>();
        Instructor instructor = new Instructor(students, assignments);

        try {
            File myObj = new File(path);
            myInput = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // before peer review
        while (myInput.hasNextLine()) {
            String[] stringElement;
            try {
                stringElement = myInput.nextLine().split(" ");
            } catch (Exception e) {
                break;
            }

            if (stringElement[0].equals(("student"))) {
                HashSet<String> ids = new HashSet<String>();
                boolean repeat = false;
                for(int i = 1; i < stringElement.length; i++) {
                    if (! ids.contains(stringElement[i])) {
                        ids.add(stringElement[i]);
                    }
                    else {
                        repeat = true;
                        break;
                    }
                }
                if (!repeat) {
                    for(int i = 1; i < stringElement.length; i++) {
                        students.add(new Student(stringElement[i]));
                    }
                }else {
                    System.out.println("Error");
                    continue;
                }
                break;
            }else if (stringElement[0].equals(("schoolStrategy"))) {
                boolean level_repeat = false;
                HashSet<String> levels = new HashSet<String>();
                for(int i = 1; i < stringElement.length; i++) {
                    String[] input;
                    try {
                        input = stringElement[i].split(",");
                    } catch (Exception e) {
                        break;
                    }
                    if(! levels.contains(input[0])) {
                        levels.add(input[0]);
                    }else {
                        level_repeat = true;
                        break;
                    }
                }
                if(level_repeat) {
                    System.out.println("Error");
                    continue;
                }else {
                    for(int i = 1; i < stringElement.length; i++) {
                        String[] input;
                        try {
                            input = stringElement[i].split(",");
                        } catch (Exception e) {
                            break;
                        }
                        schoolStrategy.put(input[0], Double.parseDouble(input[1]));
                    }
                }
                break;
            }else {
                System.out.println("Error");
                continue;
            }
        }

        while (myInput.hasNextLine()) {
            String[] stringElement;
            try {
                stringElement = myInput.nextLine().split(" ");
            } catch (Exception e) {
                break;
            }

            if (stringElement[0].equals(("student"))) {
                HashSet<String> ids = new HashSet<String>();
                boolean repeat = false;
                for(int i = 1; i < stringElement.length; i++) {
                    if (! ids.contains(stringElement[i])) {
                        ids.add(stringElement[i]);
                    }
                    else {
                        repeat = true;
                        break;
                    }
                }
                if (!repeat) {
                    for(int i = 1; i < stringElement.length; i++) {
                        students.add(new Student(stringElement[i]));
                    }
                }else {
                    System.out.println("Error");
                    continue;
                }
                break;
            }else if (stringElement[0].equals(("schoolStrategy"))) {
                boolean level_repeat = false;
                HashSet<String> levels = new HashSet<String>();
                for(int i = 1; i < stringElement.length; i++) {
                    String[] input;
                    try {
                        input = stringElement[i].split(",");
                    } catch (Exception e) {
                        break;
                    }
                    if(! levels.contains(input[0])) {
                        levels.add(input[0]);
                    }else {
                        level_repeat = true;
                        break;
                    }
                }
                if(level_repeat) {
                    System.out.println("Error");
                    continue;
                }else {
                    for(int i = 1; i < stringElement.length; i++) {
                        String[] input;
                        try {
                            input = stringElement[i].split(",");
                        } catch (Exception e) {
                            break;
                        }
                        schoolStrategy.put(input[0], Double.parseDouble(input[1]));
                    }
                }
                break;
            }else {
                System.out.println("Error");
                continue;
            }
        }

        // peer review (不一定照順序）

        while (myInput.hasNextLine()) {
            String[] stringElement;
            try {
                stringElement = myInput.nextLine().split(" ");
            } catch (Exception e) {
                break;
            }

            if (stringElement[0].equals(("designCriterion"))) {
                ArrayList<String> criterionRows = new ArrayList<>();
                // read criterion
                try {
                    File criterionObj = new File(stringElement[2]);
                    criterionInput = new Scanner(criterionObj);
                } catch (FileNotFoundException e) {
                    System.out.println("Error");
                    continue;
                }
                // if assignment doesn't exist, add a new assignment
                boolean assignment_created = false;
                for (Assignment a: assignments) {
                    if (a.getAssignmentId().equals(stringElement[1])) {
                        assignment_created = true;
                        break;
                    }
                }
                if (!assignment_created) {
                    assignments.add(new Assignment(stringElement[1]));
                }else {
                    System.out.println("Error");
                    continue;
                }


                // create association class Homework
                for (Student s : students) {
                    Homework h = new Homework(s, assignments.get(assignments.size() - 1));
                    s.addHomework(h);
                    assignments.get(assignments.size() - 1).addHomework(h);
                }

                while(criterionInput.hasNextLine()) {
                    criterionRows.add(criterionInput.nextLine());
                }
                instructor.design(assignments.get(assignments.size() - 1), schoolStrategy, criterionRows);

            }else if (stringElement[0].equals("assignment")) {
                String aId = stringElement[1];
                String sId = stringElement[2];

                // find current student
                Student currentStudent =null;
                for (Student s : students) {
                    if (s.getStudentId().equals(sId)) {
                        currentStudent = s;
                    }
                }
                if (currentStudent == null) {
                    System.out.println("Error");
                    continue;
                }

                // Check if assignment exists
                boolean a_exists = false;
                for (Assignment a: assignments) {
                    if (a.getAssignmentId().equals(aId)) {
                        a_exists = true;
                    }
                }
                if ( !a_exists) {
                    System.out.println("Error");
                    continue;
                }

                HashMap<String, String> reviewers = new HashMap<String, String>();
                HashSet<String> reviewer_ids = new HashSet<String>();
                boolean file_exists = true;
                boolean reviewer_repeat = false;

                for(int i = 3; i < stringElement.length; i++) {
                    // read reviewer
                    String[] input;
                    try {
                        input = stringElement[i].split(",");
                    } catch (Exception e) {
                        break;
                    }
                    if (! reviewer_ids.contains(input[0])) {
                        reviewer_ids.add(input[0]);
                    }else {
                        reviewer_repeat = true;
                        break;
                    }
                }

                for(int i = 3; i < stringElement.length; i++) {
                    // read reviewer
                    String[] input;
                    try {
                        input = stringElement[i].split(",");
                    } catch (Exception e) {
                        break;
                    }
                    File scoreObj = new File(input[1]);
                    if (!scoreObj.exists()) {
                        file_exists = false;
                        break;
                    }
                }

                List<Boolean> reviewer_exists = new ArrayList<>();
                for(int i = 3; i < stringElement.length; i++) {
                    boolean exist = false;
                    // read reviewer
                    String[] input;
                    try {
                        input = stringElement[i].split(",");
                    } catch (Exception e) {
                        break;
                    }
                    for (Student s: students) {
                        if (s.getStudentId().equals(input[0])) {
                            reviewer_exists.add(true);
                            exist = true;
                            break;
                        }
                    }
                    if(!exist) {
                        reviewer_exists.add(false);
                    }
                }
                boolean all_reviewers_exist = true;
                for(int i =0; i < reviewer_exists.size(); i ++) {
                    if (!reviewer_exists.get(i)) {
                        all_reviewers_exist = false;
                        break;
                    }
                }
                if (!all_reviewers_exist) {
                    System.out.println("Error");
                    continue;
                }
                if (file_exists && !reviewer_repeat) {
                    for(int i = 3; i < stringElement.length; i++) {
                        // read reviewer
                        String[] input;
                        try {
                            input = stringElement[i].split(",");
                        } catch (Exception e) {
                            break;
                        }
                        reviewers.put(input[0], input[1]);
                    }
                }else {                    
                    System.out.println("Error");
                    continue;
                }

                boolean idCollision = false;
                for (Map.Entry<String, String> e : reviewers.entrySet()) {
                    if (e.getKey().equals(sId)) {
                        idCollision = true;
                    }
                }

                boolean wrongNumber = false;
                if (reviewers.size() < 3 || reviewers.size() > 5) {
                    wrongNumber = true;
                }

                if (idCollision) {
                    System.out.println("Cannot review one’s own assignment.");
                    continue;
                }else if (wrongNumber) {
                    System.out.println("Assignment should be reviewed by 3-5 students.");
                    continue;
                }else {
                    instructor.setReviewers(reviewers, currentStudent, aId, schoolStrategy);
                }
            }else if (stringElement[0].equals("printRubric")) {
                String aId = stringElement[1];
                Rubric targetRubric = null;
                for (Assignment assignment : assignments) {
                    if (assignment.getAssignmentId().equals(aId)) {
                        targetRubric= assignment.getRubric();
                    }
                }
                // assignment doesn't exist
                if ( targetRubric == null) {
                    System.out.println("Error");
                    continue;
                }
                for (Criteria c : targetRubric.getCriterion()) {
                   for (Descriptor d: c.getDescriptors()) {
                       System.out.println("(" + c.getName() + "," + d.getLevel().getName() + ")\t" + d.getContent());
                   }
                }
            }else if (stringElement[0].equals("averageCriterion")) {
                String aId = stringElement[1];
                // Check if assignment exists
                boolean a_exists = false;
                for (Assignment a: assignments) {
                    if (a.getAssignmentId().equals(aId)) {
                        a_exists = true;
                    }
                }
                if ( !a_exists) {
                    System.out.println("Error");
                    continue;
                }
                instructor.averageCriterion(aId);

            }else if (stringElement[0].equals("calculateScore")) {
                if (stringElement[3].equals("MeanRankingStrategy")) {
                    instructor.setStrategy(new Average());
                }else if (stringElement[3].equals("MedianRankingStrategy")) {
                    instructor.setStrategy(new Median());
                }else {
                    System.out.println("Error");
                    continue;
                }
                instructor.calculateScore(stringElement[2], stringElement[1]);
            }else if (stringElement[0].equals("findStrength")) {
                if (stringElement[3].equals("MeanRankingStrategy")) {
                    instructor.setStrategy(new Average());
                }else if (stringElement[3].equals("MedianRankingStrategy")) {
                    instructor.setStrategy(new Median());
                }else {
                    System.out.println("Error");
                    continue;
                }
                instructor.findStrength(stringElement[2], stringElement[1]);
            }else if (stringElement[0].equals("findWeakness")) {
                if (stringElement[3].equals("MeanRankingStrategy")) {
                    instructor.setStrategy(new Average());
                }else if (stringElement[3].equals("MedianRankingStrategy")) {
                    instructor.setStrategy(new Median());
                }else {
                    System.out.println("Error");
                    continue;
                }
                instructor.findWeakness(stringElement[2], stringElement[1]);
            }else {
                System.out.println("Error");
            }
        }


    }
}