package com.niedozdarcia.ccms;

import java.util.List;

public class Main {

    private Controller controller;
    private View view;

    public Main() {
        this.view = new View();
    }

    public static void main(String[] args) {

        Main main = new Main();
        int option = -1;

        while (option != 0) {
            main.view.printMenu("Exit", "Login");
            option = main.view.getInputInt(0,1);

            if (option == 1){
                main.logIn();
            }
        }
    }

    private void logIn() {
        
        List<Mentor> mentors = new MentorsCSVDAO().getMentors();
        List<Student> students = new StudentCSVDAO().getStudents();
        List<Employee> employees = new EmployessCSVDAO().getEmployees();
        List<Manager> managers = new ManagersCSVDAO().getManagers();

        String username = this.view.getInputString("Login: ");
        String password = this.view.getInputString("Password: ");

        for (Manager manager : managers){
            if (manager.getEmail().equals(username)){
                if (manager.getPassword().equals(password)){
                    this.controller = new ManagerController(manager.getEmail());
                }
            }
        }

        for (Employee employee : employees){
            if (employee.getEmail().equals(username)){
                if (employee.getPassword().equals(password)){
                    this.controller = new EmployeeController(employee.getEmail());
                }
            }
        }

        for (Mentor mentor : mentors){
            if (mentor.getEmail().equals(username)){
                if (mentor.getPassword().equals(password)){
                    this.controller = new MentorController(mentor.getEmail());
                }
            }
        }

        for (Student student : students){
            if (student.getEmail().equals(username)){
                if (student.getPassword().equals(password)){
                    this.controller = new StudentController(student.getEmail());
                }
            }
        }
        this.controller.activate(view);
    }
}
