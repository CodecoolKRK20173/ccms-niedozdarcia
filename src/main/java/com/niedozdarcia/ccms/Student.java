package com.niedozdarcia.ccms;

import java.util.Map;

public class Student extends User{
    private Map<String,String> assignments;

    public Student (String email, String password, String name, String surname, Map<String, String> assignments){
        setEmail(email);
        setPassword(password);
        setName(name);
        setSurname(surname);
        this.assignments = assignments;
    }

    public Map<String, String> getAssignments() {
        return assignments;
    }

}