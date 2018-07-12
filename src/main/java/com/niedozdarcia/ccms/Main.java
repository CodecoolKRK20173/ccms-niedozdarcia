package com.niedozdarcia.ccms;

import java.util.List;

public class Main {


    public static void main(String[] args) {
    View view = new View();
    int option = 0;
    while (!(option ==0)){
        Controller controller;
        view.printMenu("Logout", "Login");
        option = view.getInputInt(0,1);
        if (option == 1){
            List<Mentor> mentors = new MentorsCSVDAO().getMentors();
            List<Student> students = new StudentCSVDAO().getStudents();
            List<Employee> employees = new EmployessCSVDAO().getEmployees();
            List<Manager> managers = new ManagersCSVDAO().getManagers();
            String username = view.getInputString("Login?");
            String password = view.getInputString("Password?");
            for (Manager manager : managers){
                if (manager.getEmail().equals(username)){
                    if (manager.getPassword().equals(password)){
                        controller = new ManagerController(manager.getEmail());
                    }
                }
            }
            for (Employee employee : employees){
                if (employee.getEmail().equals(username)){
                    if (employee.getPassword().equals(password)){
                        controller = new EmployeeController(employee.getEmail());
                    }
                }
            }
            for (Mentor mentor : mentors){
                if (mentor.getEmail().equals(username)){
                    if (mentor.getPassword().equals(password)){
                        controller = new MentorController(mentor.getEmail());
                    }
                }
            }
            for (Student student : students){
                if (student.getEmail().equals(username)){
                    if (student.getPassword().equals(password)){
                        controller = new StudentController(student.getEmail());
                    }
                }
            }
            controller.activate(view);
        }
    }
    }
}
