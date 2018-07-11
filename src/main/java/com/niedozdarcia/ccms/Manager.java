package com.niedozdarcia.ccms;

import java.util.ArrayList;
import java.util.List;


public class Manager extends User {
    List<Student> students;
    List<Mentor> mentors;
    List<Employee> employees;
    List<String> assigments;

    public Manager(String email,
                   String password,
                   String name,
                   String surname,
                   ArrayList<Student> students,
                   ArrayList<Mentor> mentors,
                   ArrayList<Employee> employees,
                   ArrayList<String> assigments) {

        setName(name);
        setPassword(password);
        setName(name);
        setSurname(surname);
        this.students = students;
        this.mentors = mentors;
        this.employees = employees;
        this.assigments = assigments;
    }


    private void addMentor() {
        String emile = getView().getInput();
        String password = getView().getInput();
        String name = getView().getInput();
        String surname = getView().getInput();

        mentors.add(new Mentor(email, password, name, surname, students, assigments));
    }


    private void removeMentor(int index) {
        mentors.remove(index);
    }


    private void showMentors() {
        getView().print(makeTable(mentors));
    }


    private void addEmpoloyee() {
        String emile = getView().getInput();
        String password = getView().getInput();
        String name = getView().getInput();
        String surname = getView().getInput();

        employees.add(new Employee(email, password, name, surname, students));
    }


    private void removeEmployee(int index) {
        employees.remove(index);
    }


    private void showEmployees() {
        getView().print(makeTable(employees));
    }


    private String makeTable(List<User> users) {
        String usersTable = "";

        for (int index = 0; i < users.size(); index++) {
            usersTable += String.format("%d. %s %s: %s\n", ++index, user.getName(),
            user.getSurname(), user.getEmail());
        }
        return usersTable;
    }


    @Override
    public void activate(View view){
        setView(view);
    }
}
