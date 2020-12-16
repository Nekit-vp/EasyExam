package com.EasyExam.Controllers;

import com.EasyExam.Entities.Lesson;
import com.EasyExam.Repositories.LessonRepository;
import com.EasyExam.Resources.LessonResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("lesson")
public class LessonController {

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonController(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @GetMapping("")
    public LessonResource[] getAll() {
        return Arrays.stream(lessonRepository.select())
                .map(lesson -> new LessonResource(lesson))
                .toArray(LessonResource[]::new);
    }

    @GetMapping("{id}")
    public LessonResource get(@PathVariable Integer id) {
        return new LessonResource(lessonRepository.select(id));
    }

    @PostMapping("")
    public LessonResource post(@RequestBody LessonResource lessonResource) {
        return new LessonResource(lessonRepository.insert(lessonResource.toEntity()));
    }

    @PutMapping("{id}")
    public LessonResource put(@PathVariable Integer id, @RequestBody LessonResource lessonResource) {
        return new LessonResource(lessonRepository.update(id, lessonResource.toEntity()));
    }

    @DeleteMapping("{id}")
    public LessonResource delete(@PathVariable Integer id){
        return new LessonResource(lessonRepository.delete(id));
    }
}
