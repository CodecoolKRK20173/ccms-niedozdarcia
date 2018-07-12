package com.niedozdarcia.ccms;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Mentor extends User {

    private List<Student> students;
    private List<String> assignments;
    private Map<String, List<String>> attendance;

    public Mentor(String email,
                  String password,
                  String name,
                  String surname,
                  List<Student> students,
                  List<String> assignments) {

        setEmail(email);
        setPassword(password);
        setName(name);
        setSurname(surname);
        this.students = students;
        this.assignments = assignments;
    }

    public void activate(View view) {
        setView(view);
        boolean isLogged = true;
        int choice = 0;

        while (isLogged) {

            getView().printMenu("Log out",
                    "Show students",
                    "Add student",
                    "Remove student",
                    "Edit student",
                    "Show assignments",
                    "Add assignment",
                    "Grade assignment",
                    "Check attendance");

            // get user input

            switch (choice) {

                case 0:
                    // TODO: log out
                case 1:
                    showStudents();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    editStudent();
                case 5:
                    // TODO: show assignments method
                case 6:
                    // TODO: add assignment method
                case 7:
                    // TODO: grade assignment method
                case 8:
                    // TODO: check attendance method
                default:
                    getView().print("Invalid parameter");

            }

        }
    }

    private void showStudents() {
        StringBuilder builder = new StringBuilder();
        int i = 1;

        for (Student student: students) {
            String line = String.format("%d. %s %s\n", i, student.getName(), student.getSurname());
            builder.append(line);
            i++;
        }

        getView().print(builder.toString());
    }

    private void addStudent() {

        String name = getView().getInputString("Enter student name: ");
        String surname = getView().getInputString("Enter student surname: ");
        String email = getView().getInputString("Enter student email: ");
        String password = getView().getInputString("Enter student password: ");

        Map<String, String> studentAssignments = new LinkedHashMap<>();

        for (String assignment: this.assignments) {
            studentAssignments.put(assignment, "x");
        }

        this.students.add(new Student(email, password, name, surname, studentAssignments));
    }

    private void removeStudent() {

        Student student = null;
        int i = -1;

        while (student == null) {

            showStudents();
            i = getView().getInputInt("Enter number of student you wish to remove: ");

            try {
                student =  this.students.get(i);
            } catch (IndexOutOfBoundsException e) {
                getView().print("No such number");
            }
        }

        String confirm = "";

        while (!confirm.equals("y") || !confirm.equals("n")) {
            String message = String.format("Are you sure you want to remove %s %s from system? (y/n): ",
                    student.getName(),
                    student.getSurname());
            confirm = getView().getInputString(message);
        }

        if (confirm.equals("y")) {
            this.students.remove(i);
        }
    }

    private void editStudent() {

        Student student = null;

        while (student == null) {

            showStudents();
            int i = getView().getInputInt("Enter number of student you wish to edit: ");

            try {
                student =  this.students.get(i);
            } catch (IndexOutOfBoundsException e) {
                getView().print("No such number");
            }
        }

        getView().printMenu("Go back", "Change name", "Change surname", "Change email address", "Change password");
        int choice = getView().getInputInt("Enter number: ");

        switch (choice) {

            case 1:
                student.setName(getView().getInputString("Enter new name: "));
                break;
            case 2:
                student.setSurname(getView().getInputString("Enter new surname: "));
                break;
            case 3:
                student.setEmail(getView().getInputString("Enter new email address: "));
                break;
            case 4:
                student.setPassword(getView().getInputString("Enter new password: "));
                break;
        }
    }

}
