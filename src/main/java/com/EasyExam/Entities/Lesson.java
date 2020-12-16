package com.EasyExam.Entities;

public class Lesson extends BaseEntity {

    private String name;

    public Lesson(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
