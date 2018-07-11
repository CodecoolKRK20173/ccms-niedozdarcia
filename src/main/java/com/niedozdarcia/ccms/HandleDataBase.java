package com.niedozdarcia.ccms;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public interface HandleDataBase {
    ArrayList<Student> getAllStudents();
    Student getStudent(String name);
    ArrayList<Mentor> getAllMentors();
    Mentor getMentor(String name);
    ArrayList<String> getAssignments();
    HashMap<Date, ArrayList<Student>> getAttendance();
    ArrayList<Employee> getAllEmployee();
    Employee getEmployee(String name);
    ArrayList<Manager> getAllMenagers();
    Manager getMenager(String name);
    void saveData();
}

