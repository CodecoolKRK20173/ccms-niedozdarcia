package com.niedozdarcia.ccms;

import java.util.List;

public class EmployeeController implements Controller {

    private Employee employee;
    private View view;
    private EmployessCSVDAO employeesDAO;

    public EmployeeController(String email) {
        employeesDAO = new EmployessCSVDAO();

        List<Employee> employees = employeesDAO.getEmployees();

        for (Employee employee : employees) {
            if (employee.getEmail().equals(email)) {
                this.employee = employee;
                break;
            }
        }

    }

    private void showStudents() {
        for (int index = 0; index < employee.getStudents().size(); index++) {
            view.print(String.format("%d. %s %s: %s\n",
                    ++index,
                    employee.getStudent(index).getName(),
                    employee.getStudent(index).getName(),
                    employee.getStudent(index).getEmail()));
        }
    }

      public void activate(View view){
        this.view = view;

        boolean isFinish = false;
        while (!isFinish) {
            view.printMenu("Log out",
                    "List of students");

            int choice = view.getInputInt(0,1);

            switch (choice) {
                case 0:
                    isFinish = true;
                    employeesDAO.save();
                    break;

                case 1:
                    showStudents();
                    break;
            }
        }

    }
}
