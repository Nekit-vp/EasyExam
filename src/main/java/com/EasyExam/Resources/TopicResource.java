package com.EasyExam.Resources;

import com.EasyExam.Entities.Topic;

public class TopicResource extends BaseResource {

    private Integer id;
    private Integer id_lesson;
    private Integer number_task;
    private String theory;
    private String files;

    public TopicResource() {
    }

    public TopicResource(Topic topic){
        this.id = topic.getId();
        this.id_lesson = topic.getId_lesson();
        this.number_task = topic.getNumber_task();
        this.theory = topic.getTheory();
        this.files = topic.getFiles();
    }

    public Topic toEntity(){
        return new Topic(
                this.id,
                this.id_lesson,
                this.number_task,
                this.theory,
                this.files
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
