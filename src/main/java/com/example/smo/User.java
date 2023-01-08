package com.example.smo;

public class User {
    private String firstName;
    private String last_name;
    private String middle_name;
    private String login;
    private String password;
    private String roles;

    public User(String firstName, String last_name, String middle_name, String login, String password, String roles) {
        this.firstName = firstName;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public User() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return roles;
    }

    public void setRole(String roles) {
        this.roles = roles;
    }
}
