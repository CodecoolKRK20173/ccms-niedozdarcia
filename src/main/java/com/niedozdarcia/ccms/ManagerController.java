package com.niedozdarcia.ccms;

import java.util.List;

public class ManagerController implements Controller {

    private Manager manager;
    private View view;
    private ManagersCSVDAO managersDAO;
    
    public ManagerController(String email) {
        managersDAO = new ManagersCSVDAO();

        List<Manager> managers = managersDAO.getManagers();

        for (Manager manager : managers) {
            if (manager.getEmail().equals(email)) {
                this.manager = manager;
                break;
            }
        }
    }


    private void addMentor() {
        String email = view.getInputString("Enter email: ");
        String password = view.getInputString("Enter password: ");
        String name = view.getInputString("Enter name: ");
        String surname = view.getInputString("Enter surname: ");
        manager.getMentors().add(new Mentor(email, password, name, surname, manager.getStudents(), manager.getAssigments()));
    }


    private void addEmployee() {
        String email = view.getInputString("Enter email: ");
        String password = view.getInputString("Enter password: ");
        String name = view.getInputString("Enter name: ");
        String surname = view.getInputString("Enter surname: ");
        manager.getEmployees().add(new Employee(email, password, name, surname, manager.getStudents()));
    }


    private void showUsers(List<? extends User> users) {
        for (int index = 0; index < users.size(); index++) {
            view.print(String.format("%d. %s %s: %s\n",
                    ++index,
                    users.get(index).getName(),
                    users.get(index).getName(),
                    users.get(index).getEmail()));
        }
    }


    private void showMentors() {
        showUsers(manager.getMentors());

    }

    private void showEmployees() {
        showUsers(manager.getEmployees());
    }


    private void showStudents() {
        showUsers(manager.getStudents());
    }


    private void removeMentor() {
        showMentors();
        view.print("Enter index of mentor who you want to edit: ");
        int index = view.getInputInt(1, manager.getMentors().size());
        view.print(String.format("Mentor %s %s removed.", manager.getMentor(index).getName(), manager.getMentor(index).getSurname()));
        manager.getMentors().remove(index);

    }

    private void removeEmployee() {
        showEmployees();
        view.print("Enter index of employee who you want to edit: ");
        int index = view.getInputInt(1, manager.getEmployees().size());
        view.print(String.format("Employee %s %s removed.", manager.getEmployee(index).getName(), manager.getEmployee(index).getSurname()));
        manager.getEmployees().remove(index);
    }


    private void editData(List<? extends User> users) {
        view.print("Enter index of person who you want to edit: ");
        int index = view.getInputInt(1, users.size());

        boolean isFinish = false;
        while (!isFinish) {
            view.printMenu("Back to main menu",
                                "Edit name",
                                "Edit surname",
                                "Edit password",
                                "Edit email");

            int choice = view.getInputInt(0,4);

            switch (choice) {
                case 0:
                    isFinish = true;
                    managersDAO.save();
                    break;

                case 1:
                    String name = view.getInputString("Enter new name: ");
                    users.get(index).setName(name);
                    break;

                case 2:
                    String surname = view.getInputString("Enter new surname: ");
                    users.get(index).setSurname(surname);
                    break;

                case 3:
                    String password = view.getInputString("Enter new password: ");
                    users.get(index).setPassword(password);
                    break;

                case 4:
                    String email = view.getInputString("Enter new email: ");
                    users.get(index).setEmail(email);
                    break;

                default:
                    view.print("There is not that option.");
            }
        }
    }


    private void editMentorsData(){
        showMentors();
        editData(manager.getMentors());
    }


    private void editEmployeeData(){
        showEmployees();
        editData(manager.getEmployees());
    }




    public void activate(View view){
        this.view = view;

        boolean isFinish = false;
        while (!isFinish) {
            view.printMenu("Log out",
                    "Add mentor",
                    "Remove mentor",
                    "Edit mentor's data",
                    "List of mentors",
                    "Add employee",
                    "Remove employee",
                    "Edit employee's data",
                    "List of employees",
                    "List of students");

            int choice = view.getInputInt(0, 9);

            switch (choice) {
                case 0:
                    isFinish = true;
                    managersDAO.save();
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
                    addEmployee();
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

            }
        }

    }
}
