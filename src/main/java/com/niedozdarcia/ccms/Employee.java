package com.niedozdarcia.ccms;

import java.util.ArrayList;
import java.util.List;


public class Employee extends User {
    List<Student> students;

    public Manager(String email,
                   String password,
                   String name,
                   String surname,
                   List<Student> students) {

        setEmail(email);
        setPassword(password);
        setName(name);
        setSurname(surname);
        this.students = students;
    }


    private void showStudents() {
        String studentsTable = "";
        for (int index = 0; i < students.size(); index++) {
            usersTable += String.format("%d. %s %s: %s\n", ++index, students.get(index).getName());
            students.get(index).getSurname(), students.get(index).getEmail();
        }
        getView().print(studentsTable);
    }


    @Override
    public void activate(View view){
        setView(view);

        boolean isFinish = false;
        while (!isFinish) {
            getView().printMenu("Exit",
                                "List of students");

            int choice = getView().getInputInt("Option: ");

            switch (choice) {
                case 0:
                    isFinish = true;
                    break;

                case 1:
                    showStudents();
                    break;

                default:
                    getView().print("There is not that option");
            }
        }

    }
}
