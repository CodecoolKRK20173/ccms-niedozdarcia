package com.niedozdarcia.ccms;

import java.util.HashMap;
import java.util.Map;

public class Student extends User{
    private Map<String,String> assignments;

    public Student (String email, String password, String name, String surname, HashMap<String, String> assignments){
        setEmail(email);
        setPassword(password);
        setName(name);
        setSurname(surname);
        this.assignments = assignments;
    }

    public void addAssignment(String assignment){
        assignments.put(assignment.substring(0, assignment.length() - 2), assignment.substring(assignment.length() - 2, assignment.length() - 1));
    }

    private void submitAssignment(String assignment){
        if (assignments.get(assignment).equals("x")){ // x - task not commited.
            assignments.put(assignment, "0");
        }
    }

    private void showAssigments(View view){
        for (String key : assignments.keySet()){
            view.print(key + " " + assignments.get(key));
        }
    }

    public void changeAssignmentStatus(String assignment, String mark){
        assignments.put(assignment, mark);
    }

    public void activate(View view){
        setView(view);
        int input = 5;
        while(!(input == 0)){
            view.printMenu("Exit", "SubmitAssignment", "ShowAssignment");
            input = view.getInputInt("Pick option");
            if (input == 1){
                showAssigments(view);
                String assignment = view.getInputString("Assignment name?");
                submitAssignment(assignment);
            }
            if (input == 2){
                showAssigments(view);
            }
        }

    }
}
