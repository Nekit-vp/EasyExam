package com.EasyExam.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



import com.EasyExam.Repositories.TaskRepository;
import com.EasyExam.Resources.TaskResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("task")
public class TaskController {

    protected final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("")
    public TaskResource[] getAll(){
        return Arrays.stream(taskRepository.select())
                .map(task -> new TaskResource(task))
                .toArray(TaskResource[]::new);
    }

    @GetMapping("{id}")
    public TaskResource get(@PathVariable Integer id){
        return new TaskResource(taskRepository.select(id));
    }

    @PostMapping("")
    public TaskResource post(@RequestBody TaskResource taskResource){
        return new TaskResource(taskRepository.insert(taskResource.toEntity()));
    }

    @PutMapping("{id}")
    public TaskResource put(@PathVariable Integer id, @RequestBody TaskResource taskResource){
        return new TaskResource(taskRepository.update(id, taskResource.toEntity()));
    }

    @DeleteMapping("{id}")
    public TaskResource delete(@PathVariable Integer id){
        return new TaskResource(taskRepository.delete(id));
    }
}
