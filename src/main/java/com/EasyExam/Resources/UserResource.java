package com.EasyExam.Resources;

import com.EasyExam.Entities.User;

public class UserResource extends BaseResource {

    private Integer id;
    private String login;
    private String password;
    private Boolean user_role;

    public UserResource() {
    }

    public UserResource(User user){
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.user_role = user.isUser_role();

    }

    public Boolean getUser_role() {
        return user_role;
    }

    public void setUser_role(Boolean user_role) {
        this.user_role = user_role;
    }

    public User toEntity(){
        return new User(
                this.id,
                this.login,
                this.password,
                this.user_role
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
