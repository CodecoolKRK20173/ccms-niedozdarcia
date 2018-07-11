package com.niedozdarcia.ccms;

import java.io.Writer;
import java.util.*;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HandleCsv {
    private List<Student> students;
    private List<Mentor> mentors;
    private List<Employee> employees;
    private List<Manager> managers;
    private Map<String, ArrayList<String>> attendance;
    private List<String> assignments;
    private ArrayList<String[]> assToSave;
    private ArrayList<String[]> attToSave;
    private ArrayList<String[]> studToSave;
    private ArrayList<String[]> mentsToSave;
    private ArrayList<String[]> empsToSave;
    private ArrayList<String[]> manToSave;
    private ArrayList<ArrayList<String[]>> packedData;
    private ArrayList<String> packedFilePaths;
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

    private void CSVWriter( ) {
        int i = 0;
        for (ArrayList<String[]> dataSet : packedData){
        String currentFilePath = packedFilePaths.get(i);
            try {
            Writer writer = Files.newBufferedWriter(Paths.get(currentFilePath));

            CSVWriter csvWriter = new CSVWriter(writer,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            for (String[] record : dataSet) {
                csvWriter.writeNext(record);
            }

        i++;
        } catch (IOException e) {
            System.out.print(e);
        }

    }}
    private List<String[]> CSVReader(String filePath) {
        List<String[]> records = new ArrayList<>();
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
        students = new ArrayList<>();
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
        assignments = new ArrayList<>();
        List<String[]> assignmentList = CSVReader(assignmentsFilePath);
        for(String[] record : assignmentList){
            assignments.add(record[0]);
        }
    }

    private void loadMentors(){
        mentors = new ArrayList<>();
        List<String[]> mentorsList = CSVReader(mentorsFilePath);
        for (String[] record : mentorsList){
            mentors.add(new Mentor(record[0], record[1], record[2], record[3], students, assignments));
        }
    }

    private void loadAttendance(){
        attendance = new HashMap<>();
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

    private void loadEmployees(){
        employees = new ArrayList<>();
        List<String[]> employeesList = CSVReader(employeFilePath);
        for (String[] record : employeesList){
            employees.add(new Employee(record[0], record[1], record[2], record[3], students);
        }
    }

    private void loadManagers(){
        managers = new ArrayList<>();
        List<String[]> managersList = CSVReader(managersFilePath);
        for (String[] record : managersList){
            managers.add(new Manager(record[0], record[1], record[2], record[3], students, mentors, employees, assignments));
        }
    }

    public void saveData(){
        prepareAssignmentsForSaving();
        prepareAttendanceForSaving();
        prepareStudentsForSaving();
        prepareEmployeesForSaving();
        packData();
        packFilePaths();
        CSVWriter();
    }

    private void prepareAssignmentsForSaving(){
        for (String assingment : assignments){
            String[] preparedString = new String[1];
            preparedString[0] = assingment;
            assToSave.add(preparedString);
        }
    }

    private void  prepareAttendanceForSaving(){
        for (String day : attendance.keySet()){
            String[] preparedString = prepareArray(day);
            attToSave.add(preparedString);
        }
    }

    private String[] prepareArray(String day){
        String[] preparedString = new String[attendance.get(day).size()+1];
        preparedString[0] = day;
        int i = 1;
        for (String student : attendance.get(day)){
            preparedString[i] = student;
            i++;
        }
    }

    private void prepareStudentsForSaving(){
        for (Student student : students){
            String[] preparedString = new String[4 + assignments.size()];
            preparedString[0] = student.getEmail();
            preparedString[1] = student.getPassword();
            preparedString[2] = student.getName();
            preparedString[3] = student.getSurname();
            int i = 4;
            for (String assignment : assignments){
                preparedString[i] = assignment;
                i++;
            }
            studToSave.add(preparedString);
        }
    }

    private void prepareEmployeesForSaving(){
        for (Mentor mentor : mentors){
            String[] preparedString = new String[4];
            preparedString[1] = mentor.getEmail();
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Mentor> getMentors() {
        return mentors;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public Map<String, ArrayList<String>> getAttendance() {
        return attendance;
    }

    public List<String> getAssignments() {
        return assignments;
    }
}
