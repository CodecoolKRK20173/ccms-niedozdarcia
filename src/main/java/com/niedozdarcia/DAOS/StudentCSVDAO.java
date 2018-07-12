package com.niedozdarcia.DAOS;

import com.niedozdarcia.ccms.HandleCsv;
import com.niedozdarcia.ccms.Student;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class StudentCSVDAO extends  CSVHandler{
    private List<Student> students;

    public StudentCSVDAO(){
        setFilePath(HandleCsv.class.getResource("/users/students.csv").getPath());
        load();
        prepareData();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void prepareData(){
        students = new ArrayList<>();
        for (String[] record: getRecords()){
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

    public void prepareDataToSave(){
        for (Student student : students){
            String[] preparedString = new String[4 + student.getAssignments().size()];
            preparedString[0] = student.getEmail();
            preparedString[1] = student.getPassword();
            preparedString[2] = student.getName();
            preparedString[3] = student.getSurname();
            int i = 4;
            for (String assignment : student.getAssignments().keySet()){
                preparedString[i] = assignment + student.getAssignments().get(assignment);
                i++;
            }
            addRecordToSave(preparedString);
        }
    }
}
