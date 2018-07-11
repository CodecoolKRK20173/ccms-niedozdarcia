package com.niedozdarcia.ccms;

public abstract class User{
    private String email;
    private String password;
    private String name;
    private String surname;
    private View view;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setView(View view) { this.view = view; }

    public View getView() { return this.view; }

    public abstract void activate(View view);
}
