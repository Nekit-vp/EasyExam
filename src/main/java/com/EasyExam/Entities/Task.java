package com.EasyExam.Entities;

public class Task extends BaseEntity {

    private Integer id_topic;
    private String task_text;
    private String answer;
    private Integer complexity;

    public Task(Integer id, Integer id_topic, String task_text, String answer, Integer complexity) {
        super(id);
        this.id_topic = id_topic;
        this.task_text = task_text;
        this.answer = answer;
        this.complexity = complexity;
    }


    public Integer getId_topic() {
        return id_topic;
    }

    public void setId_topic(Integer id_topic) {
        this.id_topic = id_topic;
    }

    public String getTask_text() {
        return task_text;
    }

    public void setTask_text(String task_text) {
        this.task_text = task_text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getComplexity() {
        return complexity;
    }

    public void setComplexity(Integer complexity) {
        this.complexity = complexity;
    }
}
