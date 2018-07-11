package com.niedozdarcia.ccms;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import com.opencsv.CSVReader;


public class HandleCsv {
    private ArrayList<Student> students;
    private ArrayList<Mentors> mentors;
    private ArrayList<Employee> employees;
    private ArrayList<Manager> managers;
    private HashMap<Date, ArrayList<Student>> attendance;
    private ArrayList<String> assignments;

    private String studentFilePath = HandleCsv.class.getResource("/users/students.csv").getPath();
    private String mentorsFilePath = HandleCsv.class.getResource("/users/mentors.csv").getPath();
    private String employeFilePath = HandleCsv.class.getResource("/users/employees.csv").getPath();
    private String managersFilePath = HandleCsv.class.getResource("/users/managers.csv").getPath();
    private String attendanceFilePath = HandleCsv.class.getResource("/users/attendance.csv").getPath();
    private String assignmentsFilePath = HandleCsv.class.getResource("/users/assignments.csv").getPath();

    public HandleCsv(){
        loadStudents();
        loadMentors();
        loadEmployees();
        loadManagers();
        loadAssignments();
    }

    private ();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Mentors> getMentors() {
        return mentors;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Manager> getManagers() {
        return managers;
    }

    public HashMap<Date, ArrayList<Student>> getAttendance() {
        return attendance;
    }

    public ArrayList<String> getAssignments() {
        return assignments;
    }
}
