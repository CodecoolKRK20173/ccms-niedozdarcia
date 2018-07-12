package com.niedozdarcia.ccms;

import java.util.ArrayList;
import java.util.List;

public class ManagersCSVDAO extends  CSVHandler{
    private List<Manager> managers;
    private List<Employee> employees;
    private List<Student> students;
    private List<Mentor> mentors;
    private List<String> assignments;




    public ManagersCSVDAO() {
        StudentCSVDAO studentsDao = new StudentCSVDAO();
        MentorsCSVDAO mentorsCSVDAO = new MentorsCSVDAO();
        EmployessCSVDAO employessCSVDAO = new EmployessCSVDAO();
        AssignmentCSVDAO assignmentCSVDAO = new AssignmentCSVDAO();
        setFilePath(ManagersCSVDAO.class.getResource("/users/managers.csv").getPath());
        students = studentsDao.getStudents();
        mentors = mentorsCSVDAO.getMentors();
        employees = employessCSVDAO.getEmployees();
        assignments = assignmentCSVDAO.getAssignments();
        load();
        prepareData();
    }

    public void prepareData() {
        managers= new ArrayList<>();
        for (String[] record : getRecords()) {
            managers.add(new Manager(record[0], record[1], record[2], record[3], students, mentors, employees, assignments));
        }
    }

    public void prepareDataToSave() {
        for (Manager manager : managers) {
            String[] preparedString = new String[4];
            preparedString[0] = manager.getEmail();
            preparedString[1] = manager.getPassword();
            preparedString[2] = manager.getName();
            preparedString[3] = manager.getSurname();
            addRecordToSave(preparedString);
        }
    }

    public List<Manager> getManagers() {
        return managers;
    }
}
