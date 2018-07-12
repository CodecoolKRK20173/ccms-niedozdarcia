package com.niedozdarcia.ccms;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

public class MentorController implements Controller {

    private View view;
    private Mentor mentor;

    public MentorController(String email) {
        List<Mentor> mentors = new MentorsCSVDAO().getMentors();

        for (Mentor mentor: mentors) {
            if (mentor.getEmail().equals(email)) {
                this.mentor = mentor;
                break;
            }
        }

        mentor.setAttendance(new AttendanceCSVDao().getAttendance());
    }

    @Override
    public void activate(View view) {
        this.view = view;
        boolean isLogged = true;
        int choice;

        while (isLogged) {

            this.view.printMenu("Log out",
                    "Show students",
                    "Add student",
                    "Remove student",
                    "Edit student",
                    "Show assignments",
                    "Add assignment",
                    "Grade assignment",
                    "Check attendance");

            this.view.print("Option: ");
            choice = this.view.getInputInt(0, 8);

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
                    break;
                case 5:
                    showAssignments(this.mentor.getAssignments());
                    break;
                case 6:
                    addAssignment();
                    break;
                case 7:
                    gradeAssignment();
                    break;
                case 8:
                    checkAttendance();
                    break;
                default:
                    break;

            }

        }
    }

    private void showStudents() {
        StringBuilder builder = new StringBuilder();
        int i = 1;

        for (Student student: this.mentor.getStudents()) {
            String line = String.format("%d. %s %s\n", i, student.getName(), student.getSurname());
            builder.append(line);
            i++;
        }

        this.view.print(builder.toString());
    }

    private void addStudent() {

        String name = this.view.getInputString("Enter student name: ");
        String surname = this.view.getInputString("Enter student surname: ");
        String email = this.view.getInputString("Enter student email: ");
        String password = this.view.getInputString("Enter student password: ");

        Map<String, String> studentAssignments = new LinkedHashMap<>();

        for (String assignment: this.mentor.getAssignments()) {
            studentAssignments.put(assignment, "x");
        }

        this.mentor.addStudent(new Student(email, password, name, surname, studentAssignments));
    }

    private void removeStudent() {

        showStudents();
        this.view.print("Choose student to remove");
        int i = this.view.getInputInt(1, this.mentor.getStudents().size()) - 1;
        Student student = this.mentor.getStudents().get(i);

        String confirm = "";

        while (!confirm.equals("y") || !confirm.equals("n")) {
            String message = String.format("Are you sure you want to remove %s %s from system? (y/n): ",
                    student.getName(),
                    student.getSurname());
            confirm = this.view.getInputString(message);
        }

        if (confirm.equals("y")) {
            this.mentor.removeStudent(i);
        }
    }

    private void editStudent() {

        showStudents();
        this.view.print("Choose student to edit");
        int i = this.view.getInputInt(1, this.mentor.getStudents().size()) - 1;
        Student student = this.mentor.getStudents().get(i);

        this.view.printMenu("Go back", "Change name", "Change surname", "Change email address", "Change password");
        int choice = this.view.getInputInt(0, 5);

        switch (choice) {

            case 1:
                student.setName(this.view.getInputString("Enter new name: "));
                break;
            case 2:
                student.setSurname(this.view.getInputString("Enter new surname: "));
                break;
            case 3:
                student.setEmail(this.view.getInputString("Enter new email address: "));
                break;
            case 4:
                student.setPassword(this.view.getInputString("Enter new password: "));
                break;
            default:
                break;
        }
    }

    private void showAssignments(List<String> assignments) {
        StringBuilder builder = new StringBuilder();
        int i = 1;

        for (String assignment: assignments) {
            String line = String.format("%d. %s\n", i, assignment);
            builder.append(line);
            i++;
        }

        this.view.print(builder.toString());
    }

    private void addAssignment() {
        String assignment = this.view.getInputString("Enter the name of the new assignment: ");
        mentor.addAssignment(assignment);
    }

    private void gradeAssignment() {

        showStudents();
        this.view.print("Choose student to grade their submitted assignments");
        int i = this.view.getInputInt(1, this.mentor.getStudents().size()) - 1;
        Student student = this.mentor.getStudents().get(i);

        List<String> submittedAssignments = getSubmittedAssignments(student);
        showAssignments(submittedAssignments);
        this.view.print("Choose assignment to grade");
        int j = this.view.getInputInt(1, submittedAssignments.size());
        String grade = this.view.getInputString("Enter grade for the assignment: ");
        student.changeAssignmentStatus(submittedAssignments.get(j), grade);
    }

    private List<String> getSubmittedAssignments(Student student) {

        Map<String, String> studentAssignments = student.getAssignments();
        List<String> submittedAssignments = new ArrayList<>();

        for (String assignment: studentAssignments.keySet()) {
            String status = studentAssignments.get(assignment);
            if (status.equals("x")) {
                submittedAssignments.add(assignment);
            }
        }

        return submittedAssignments;
    }

    private void checkAttendance() {

        Format formatter = new SimpleDateFormat("dd.MM.yyyy");
        String date = formatter.format(new Date());
        List<String> presentStudents = new ArrayList<>();

        for (Student student: mentor.getStudents()) {
            String studentFullName = String.format("%s %s", student.getName(), student.getSurname());
            if (isPresent(studentFullName))
                presentStudents.add(studentFullName);
        }

        mentor.getAttendance().put(date, presentStudents);
    }

    private boolean isPresent(String studentFullName) {
        String present = "";

        while (!present.equals("y") || !present.equals("n")) {
            present = view.getInputString(String.format("Is %s present? (y/n): ", studentFullName));
        }

        return present.equals("y");
    }
}
