package com.EasyExam.Controllers;

import com.EasyExam.Repositories.TopicRepository;
import com.EasyExam.Resources.TopicResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("topic")
public class TopicController {

    protected final TopicRepository topicRepository;

    @Autowired
    public TopicController(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @GetMapping("")
    public TopicResource[] getAll(){
        return Arrays.stream(topicRepository.select())
                .map(topic -> new TopicResource(topic))
                .toArray(TopicResource[]::new);
    }

    @GetMapping("{id}")
    public TopicResource get(@PathVariable Integer id){
        return new TopicResource(topicRepository.select(id));
    }

    @PostMapping("")
    public TopicResource post(@RequestBody TopicResource topicResource){
        return new TopicResource(topicRepository.insert(topicResource.toEntity()));
    }

    @PutMapping("{id}")
    public TopicResource put(@PathVariable Integer id, @RequestBody TopicResource topicResource){
        return new TopicResource(topicRepository.update(id, topicResource.toEntity()));
    }

    @DeleteMapping("{id}")
    public TopicResource delete(@PathVariable Integer id){
        return new TopicResource(topicRepository.delete(id));
    }

}
