package com.EasyExam.Entities;

public class Solution extends BaseEntity{

    private Integer id_user;
    private Integer id_task;
    private String answer_user;
    private Boolean mark;

    public Solution(Integer id, Integer id_user, Integer id_task, String answer_user, Boolean mark) {
        super(id);
        this.id_user = id_user;
        this.id_task = id_task;
        this.answer_user = answer_user;
        this.mark = mark;
    }


    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_task() {
        return id_task;
    }

    public void setId_task(Integer id_task) {
        this.id_task = id_task;
    }

    public String getAnswer_user() {
        return answer_user;
    }

    public void setAnswer_user(String answer_user) {
        this.answer_user = answer_user;
    }

    public Boolean getMark() {
        return mark;
    }

    public void setMark(Boolean mark) {
        this.mark = mark;
    }
}
