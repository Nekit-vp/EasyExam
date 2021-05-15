package com.EasyExam.Entities;

public class User extends BaseEntity{

    private String login;
    private String password;
    private Boolean user_role;



    public User(Integer id, String login, String password, Boolean user_role) {
        super(id);
        this.login = login;
        this.password = password;
        this.user_role = user_role;
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

    public boolean isUser_role() {
        return user_role;
    }

    public void setUser_role(boolean user_role) {
        this.user_role = user_role;
    }
}
