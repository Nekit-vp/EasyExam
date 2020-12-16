package com.EasyExam.Resources;

import com.EasyExam.Entities.Task;

public class TaskResource extends BaseResource{

    private Integer id;
    private Integer id_topic;
    private String task_text;
    private String answer;
    private Integer complexity;

    public TaskResource() {
    }

    public TaskResource(Task task){
        this.id = task.getId();
        this.id_topic = task.getId_topic();
        this.task_text = task.getTask_text();
        this.answer = task.getAnswer();
        this.complexity = task.getComplexity();
    }

    public Task toEntity(){
        return new Task(
                this.id,
                this.id_topic,
                this.task_text,
                this.answer,
                this.complexity
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
