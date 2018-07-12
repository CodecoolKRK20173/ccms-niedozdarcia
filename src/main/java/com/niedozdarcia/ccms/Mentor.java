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
        this.attendance = attendance;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(int index) {
        this.students.remove(index);
    }

    public List<String> getAssignments() {
        return assignments;
    }

    public void addAssignment(String assignment) {
        this.assignments.add(assignment);
    }

    public Map<String, List<String>> getAttendance() {
        return attendance;
    }

    public void setAttendance(Map<String, List<String>> attendance) {
        this.attendance = attendance;
    }

}
