package com.niedozdarcia.ccms;

import java.util.ArrayList;
import java.util.List;

public class StudentController implements Controller {

    private Student student;
    private View view;

    public StudentController(String email) {
        List<Student> students = new StudentCSVDAO().getStudents();

        for (Student student : students) {
            if (student.getEmail().equals(email)) {
                this.student = student;
                break;
            }
        }
    }


    private void submitAssignment() {
        showAssignments();
        ArrayList<String> assignmentsList = putAssignmentToList();
        view.print("Enter index of assignment which you want to submit: ");

        int index = view.getInputInt(1, assignmentsList.size());

        String assignment = assignmentsList.get(index);

        this.student.changeAssignmentStatus(assignment, "0");

    }

    private ArrayList<String> putAssignmentToList(){
        ArrayList<String> assignmentList = new ArrayList<>();
        for (String key : student.getAssignments().keySet()) {
            assignmentList.add(key);
        }
        return assignmentList;
    }

    private void showAssignments() {
        int index = 1;
        for (String key : student.getAssignments().keySet()) {
            view.print(index + ". " + key + " " + student.getAssignments().get(key));
            index++;
        }
    }


    public void activate(View view) {
        this.view = view;

        boolean isFinish = false;
        while (!isFinish) {
            view.printMenu("Log out",
                    "Submit assignment",
                    "Show assignments");

            int choice = view.getInputInt(0, 2);

            switch (choice) {
                case 0:
                    isFinish = true;
                    break;

                case 1:
                    submitAssignment();
                    break;

                case 2:
                    showAssignments();
                    break;
            }
        }
    }
}


