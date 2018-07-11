package com.niedozdarcia.ccms;


public class Manager extends User {


    public Manager(String email, String password, String name, String surname,
    List<Student> students, List<Mentor> mentors, List<Employee> employees){
        super(email, password, name, surname);
        this.students = students;
        this.mentors = mentors;
        this.employees = employees;
        view = new View;
    }


    private void addMentor() {
        String emile = view.getInput();
        String password = view.getInput();
        String name = view.getInput();
        String surname = view.getInput();

        mentors.add(new Mentor(email, password, name, surname, students, ))

    }
}
