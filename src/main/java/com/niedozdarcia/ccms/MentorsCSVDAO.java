package com.niedozdarcia.ccms;

import java.util.ArrayList;
import java.util.List;

public class MentorsCSVDAO extends  CSVHandler {
    private List<Mentor> mentors;
    private List<Student> students;
    private List<String> assignments;
    private StudentCSVDAO studentsDao = new StudentCSVDAO();
    private AssignmentCSVDAO assignmentDAO = new AssignmentCSVDAO();

    public MentorsCSVDAO(){
        setFilePath(HandleCsv.class.getResource("/users/mentors.csv").getPath());
        students = studentsDao.getStudents();
        assignments = assignmentDAO.getAssignments();
        load();
        prepareData();
    }

    public void prepareData(){
        mentors = new ArrayList<>();
        for (String[] record : getRecords()){
            mentors.add(new Mentor(record[0], record[1], record[2], record[3], students, assignments));
        }
    }

    public void prepareDataToSave(){

        for (Mentor mentor : mentors){
            String[] preparedString = new String[4 + Student.getAssignments().size()];
            preparedString[0] = student.getEmail();
            preparedString[1] = student.getPassword();
            preparedString[2] = student.getName();
            preparedString[3] = student.getSurname();
            int i = 4;
            for (String assignment : Student.getAssignments()){
                preparedString[i] = assignment;
                i++;
            }
            addRecordToSave(preparedString);
        }
}
