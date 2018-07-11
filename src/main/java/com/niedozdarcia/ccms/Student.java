package com.niedozdarcia.ccms;

import java.util.HashMap;

public class Student extends User{
    private HashMap<String,String> assignments;

    public Student (String email, String password, String name, String surname, HashMap<String, String> assignments, View view){
        setEmail(email);
        setPassword(password);
        setName(name);
        setSurname(surname);
        this.assignments = assignments;
    }

    public void addAssignment(String assignment){
        assignments.put(assignment.substring(0, assignment.length() - 1), assignment.substring(assignment.length() - 2, assignment.length() - 1));
    }

    private void submitAssignment(String assignment){
        if (assignments.get(assignment).equals("x")){ // x - task not commited.
            assignments.put(assignment, "0");
        }
    }

    private void showAssigments(View view){
        view.showAssigments(assignments);
    }

    public void changeAssignmentStatus(String assignment, String mark){
        assignments.get(assignment) = mark;
    }

    public void activate(View view){
        int input = 5;
        while(!(input == 0)){
            view.printStudentMenu();
            input = view.getInput();
            if (input == 1){
                showAssigments(view);
                String assignment = view.getString("Assignment name?");
                submitAssignment(assignment);
            }
            if (input == 2){
                showAssigments(view);
            }
        }

    }
}
