package com.niedozdarcia.ccms;

import java.util.List;


public class Manager extends User {


    private List<Student> students;
    private List<Mentor> mentors;
    private List<Employee> employees;
    private List<String> assignments;

    public Manager(String email,
                   String password,
                   String name,
                   String surname,
                   List<Student> students,
                   List<Mentor> mentors,
                   List<Employee> employees,
                   List<String> assignments) {

        setEmail(email);
        setPassword(password);
        setName(name);
        setSurname(surname);
        this.students = students;
        this.mentors = mentors;
        this.employees = employees;
        this.assignments = assignments;
    }


    public List<User> getMentors() {
        return mentors;
    }


    public List<String> getAssigments() {
        return assignments;
    }



    public List<User> getStudents() {
        return students;
    }



    public List<User> getEmployees() {
        return employees;
    }

    public Mentor getMentor(int index) {
        return mentors.get(index);
    }

    public Student getStudent(int index) {
        return students.get(index);
    }

    public Employee getEmployee(int index) {
        return employees.get(index);
    }
}
