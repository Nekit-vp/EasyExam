package com.EasyExam.Entities;

public class Topic extends BaseEntity {

    private Integer id_lesson;
    private Integer number_task;
    private String theory;
    private String files;

    public Topic(Integer id, Integer id_lesson, Integer number_task, String theory, String files) {
        super(id);
        this.id_lesson = id_lesson;
        this.number_task = number_task;
        this.theory = theory;
        this.files = files;
    }

    public Integer getId_lesson() {
        return id_lesson;
    }

    public void setId_lesson(Integer id_lesson) {
        this.id_lesson = id_lesson;
    }

    public Integer getNumber_task() {
        return number_task;
    }

    public void setNumber_task(Integer number_task) {
        this.number_task = number_task;
    }

    public String getTheory() {
        return theory;
    }

    public void setTheory(String theory) {
        this.theory = theory;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }
}
