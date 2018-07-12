package com.niedozdarcia.ccms;

import java.util.ArrayList;
import java.util.List;

public class EmployessCSVDAO extends  CSVHandler{
    private List<Employee> employees;
    private List<Student> students;

    public EmployessCSVDAO() {
        setFilePath(EmployessCSVDAO.class.getResource("/users/mentors.csv").getPath());
        StudentCSVDAO studentsDao = new StudentCSVDAO();
        students = studentsDao.getStudents();
        load();
        prepareData();
    }

    public void prepareData() {
        employees = new ArrayList<>();
        for (String[] record : getRecords()) {
            employees.add(new Employee(record[0], record[1], record[2], record[3], students));
        }
    }

    public void prepareDataToSave() {
        for (Employee employee : employees) {
            String[] preparedString = new String[4];
            preparedString[0] = employee.getEmail();
            preparedString[1] = employee.getPassword();
            preparedString[2] = employee.getName();
            preparedString[3] = employee.getSurname();
            addRecordToSave(preparedString);
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
