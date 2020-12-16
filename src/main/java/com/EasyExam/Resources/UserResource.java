package com.EasyExam.Resources;

import com.EasyExam.Entities.User;

public class UserResource extends BaseResource {

    private Integer id;
    private String login;
    private String password;

    public UserResource() {
    }

    public UserResource(User user){
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

    public User toEntity(){
        return new User(
                this.id,
                this.login,
                this.password
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
