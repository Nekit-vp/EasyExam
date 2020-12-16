package com.EasyExam.Resources;

import com.EasyExam.Entities.Lesson;

public class LessonResource extends BaseResource {
    private Integer id;
    private String name;

    public LessonResource() {
    }

    public LessonResource(Lesson lesson) {
        this.id = lesson.getId();
        this.name = lesson.getName();
    }

    public Lesson toEntity(){
        return new Lesson(this.id, this.name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
