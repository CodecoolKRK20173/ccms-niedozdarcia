package com.niedozdarcia.ccms;

import java.util.*;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HandleCsv {
    private ArrayList<Student> students;
    private ArrayList<Mentors> mentors;
    private ArrayList<Employee> employees;
    private ArrayList<Manager> managers;
    private Map<String, ArrayList<String>> attendance;
    private ArrayList<String> assignments;

    private String studentFilePath = HandleCsv.class.getResource("/users/students.csv").getPath();
    private String mentorsFilePath = HandleCsv.class.getResource("/users/mentors.csv").getPath();
    private String employeFilePath = HandleCsv.class.getResource("/users/employees.csv").getPath();
    private String managersFilePath = HandleCsv.class.getResource("/users/managers.csv").getPath();
    private String attendanceFilePath = HandleCsv.class.getResource("/users/attendance.csv").getPath();
    private String assignmentsFilePath = HandleCsv.class.getResource("/users/assignments.csv").getPath();

    public HandleCsv(){
        loadAssignments();
        loadAttendance();
        loadStudents();
        loadMentors();
        loadEmployees();
        loadManagers();
    }

    private List<String[]> CSVReader(String filePath) {
        List<String[]> records = new ArrayList<String[]>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVReader csvReader = new CSVReader(reader);
            records = csvReader.readAll();
        } catch (IOException e){
            System.out.print(e);
        }
        return records;
    }

    private void loadStudents(){
        List<String[]> studentList = CSVReader(studentFilePath);
        for (String[] record: studentList){
            LinkedHashMap<String, String> studentsAssignments = prepareStudentAssignments(record);
            students.add(new Student(record[0], record[1], record[2], record[3], studentsAssignments));
        }
    }

    private LinkedHashMap<String, String> prepareStudentAssignments(String[] data){
        LinkedHashMap<String, String> studentsAssignments = new LinkedHashMap<>();
        for (int i = 4, n = data.length -1 ; i<n; i++){
            studentsAssignments.put(data[i].substring(0, data[i].length() - 2),
                    data[i].substring(data[i].length() - 2, data[i].length() - 1));
        }
        return  studentsAssignments;
    }

    private void loadAssignments(){
        List<String[]> assignmentList = CSVReader(assignmentsFilePath);
        for(String[] record : assignmentList){
            assignments.add(record[0]);
        }
    }

    private void loadMentors(){
        List<String[]> mentorsList = CSVReader(mentorsFilePath);
        for (String[] record : mentorsList){
            mentors.add(new Mentor(record[0], record[1], record[2], record[3], students, assignments));
        }
    }

    private void loadAttendance(){
        List<String[]> attendanceList = CSVReader(attendanceFilePath);
        for (String[] record : attendanceList){
            ArrayList<String> studentsAttendance = prepareAttendanceList(record);
            attendance.put(record[0], studentsAttendance);
        }
    }

    private ArrayList<String> prepareAttendanceList(String[] record){
        ArrayList<String> studentsAttendance = new ArrayList<>();
        for (int i = 1, n = record.length - 1; i<n; i++){
            studentsAttendance.add(record[i]);
        }
        return  studentsAttendance;
    }

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
