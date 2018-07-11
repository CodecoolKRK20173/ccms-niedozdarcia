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

        setEmail(email);
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


    private void addEmpoloyee() {
        String emile = getView().getInput();
        String password = getView().getInput();
        String name = getView().getInput();
        String surname = getView().getInput();
        employees.add(new Employee(email, password, name, surname, students));
    }


    private String makeTable(List<User> users) {
        String usersTable = "";

        for (int index = 0; i < users.size(); index++) {
            usersTable += String.format("%d. %s %s: %s\n", ++index, user.getName(),
            user.getSurname(), user.getEmail());
        }
        return usersTable;
    }


    private void showMentors() {
        getView().print(makeTable(mentors));
    }

    private void showEmployees() {
        getView().print(makeTable(employees));
    }


    private void showStudents() {
        getView().print(makeTable(students));
    }


    private void removeMentor() {
        showMentors();
        int index = getView().getInputInt("Enter index of mentor who you want to remove.");
        getView().getInputInt("Mentor %s removed", mentors.get(index).getName());
        mentors.remove(index);

    }

    private void removeEmployee() {
        showEmployees();
        int index = getView().getInputInt("Enter index of employee who you want to remove.");
        getView().getInputInt("Employee %s removed", mentors.get(index).getName());
        employees.remove(index);
    }


    private void editData(List<User> users) {
        int index = getView().getInputInt("Enter index of person who you want to edit.");

        boolean isFinish = false;
        while (!isFinish) {
            getView().printMenu("Back to main menu",
                                "Edit name",
                                "Edit surname",
                                "Edit password",
                                "Edit email");

            int choice = getView().getInputInt("Option: ");

            switch (choice) {
                case 0:
                    isFinish = true;
                    break;

                case 1:
                    String name = getView().getInputString("Enter new name: ");
                    users.get(index).setName(name);
                    break;

                case 2:
                    String surname = getView().getInputString("Enter new surname: ");
                    users.get(index).setSurname(surname);
                    break;

                case 3:
                    String password = getView().getInputString("Enter new password: ");
                    users.get(index).setPassword(password);
                    break;

                case 4:
                    String email = getView().getInputString("Enter new email: ");
                    users.get(index).setEmail(email);
                    break;

                default:
                    getView().print("There is not that option");
            }
        }
    }


    private void editMentorsData(){
        showMentors();
        editData(mentors);
    }


    private void editEmployeeData(){
        showEmployees();
        editData(employees);
    }


    @Override
    public void activate(View view){
        setView(view);

        boolean isFinish = false;
        while (!isFinish) {
            getView().printMenu("Exit",
                                "Add mentor",
                                "Remove mentor",
                                "Edit mentor's data",
                                "List of mentors",
                                "Add employee",
                                "Remove employee",
                                "Edit employee's data",
                                "List of employees",
                                "List of students");

            int choice = getView().getInputInt("Option: ");

            switch (choice) {
                case 0:
                    isFinish = true;
                    break;

                case 1:
                    addMentor();
                    break;

                case 2:
                    removeMentor();
                    break;

                case 3:
                    editMentorsData();
                    break;

                case 4:
                    showMentors();
                    break;

                case 5:
                    addEmpoloyee();
                    break;

                case 6:
                    removeEmployee();
                    break;

                case 7:
                    editEmployeeData();
                    break;

                case 8:
                    showEmployees();
                    break;

                case 9:
                    showStudents();
                    break;


                default:
                    getView().print("There is not that option");
            }
        }

    }
}
