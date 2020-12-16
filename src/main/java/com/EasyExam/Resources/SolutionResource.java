package com.EasyExam.Resources;

import com.EasyExam.Entities.Solution;

public class SolutionResource extends BaseResource{

    private Integer id;
    private Integer id_user;
    private Integer id_task;
    private String answer_user;
    private Boolean mark;

    public SolutionResource() {
    }

    public SolutionResource(Solution solution) {
        this.id = solution.getId();
        this.id_user = solution.getId_user();
        this.id_task = solution.getId_task();
        this.answer_user = solution.getAnswer_user();
        this.mark = solution.getMark();
    }

    public Solution toEntity(){
        return new Solution(
                this.id,
                this.id_user,
                this.id_task,
                this.answer_user,
                this.mark
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
