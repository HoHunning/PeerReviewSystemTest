package com.example.demo.PeerReviewSystem;
import java.util.ArrayList;
import java.util.HashMap;

public class ConcreteRubricBuilder implements RubricBuilder{
    private HashMap<String,Double> schoolStrategy = new HashMap<>();
    private ArrayList<Criteria> criterion  = new ArrayList<>();
    private Assignment assignment = null;
    private Rubric rubric;


    @Override
    public RubricBuilder setSchoolStrategy(HashMap<String,Double> schoolStrategy) {
        this.schoolStrategy = schoolStrategy;
        return this;
    }

    @Override
    public RubricBuilder setCriteria(ArrayList<String>  criterion) {
        for(String criteria : criterion) {
            String stringElement[] = null;
            try {
                stringElement = criteria.split("\t");
            } catch (Exception e) {
            }
            String criteriaName = stringElement[0];
            String level = stringElement[1];
            String descriptor = stringElement[2];
            //See if criteria exists
            boolean exist = false;
            for (int i = 0; i < this.criterion.size(); i++) {
                if (this.criterion.get(i).getName().equals(criteriaName)) {
                    this.criterion.get(i).addLevel(level, schoolStrategy.get(level), descriptor);
                    exist = true;
                }
            }
            if (exist == true)
                continue;
            //Create criteria
            Criteria c = new Criteria(stringElement[0]);
            c.addLevel(stringElement[1], schoolStrategy.get(stringElement[1]), stringElement[2]);
            this.criterion.add(c);
        }
        return this;
    }

    @Override
    public RubricBuilder setAssignment(Assignment assignment) {
        this.assignment = assignment;
        return this;
    }

    @Override
    public Rubric build() {
        return new Rubric( assignment,criterion );
    }


}
