package com.niedozdarcia.ccms;

import java.util.List;




public class Employee extends User {

    private List<Student> students;

    public Employee(String email,
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

    public List<Student> getStudents() {
        return students;
    }

    public User getStudent(int index) {
        return students.get(index);
    }

}
