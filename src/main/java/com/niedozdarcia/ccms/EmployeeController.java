package com.niedozdarcia.ccms;

public class EmployeeController implements Controller {

    Employee employee;
    View view;

    public EmployeeController(String email) {

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
            getView().printMenu("Exit",
                    "List of students");

            int choice = getView().getInputInt(0,1);

            switch (choice) {
                case 0:
                    isFinish = true;
                    break;

                case 1:
                    showStudents();
                    break;
            }
        }

    }
}
