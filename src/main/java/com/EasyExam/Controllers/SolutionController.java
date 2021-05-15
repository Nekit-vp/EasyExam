package com.EasyExam.Controllers;


import com.EasyExam.Entities.Solution;
import com.EasyExam.Repositories.SolutionRepository;
import com.EasyExam.Resources.SolutionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("solution")
public class SolutionController {

    protected final SolutionRepository solutionRepository;

    @Autowired
    public SolutionController(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }


    @GetMapping("")
    public SolutionResource[] getAll(){
        return Arrays.stream(solutionRepository.select())
                .map(solution -> new SolutionResource(solution))
                .toArray(SolutionResource[]::new);
    }

    @GetMapping("{id}")
    public SolutionResource get(@PathVariable Integer id){
        return new SolutionResource(solutionRepository.select(id));
    }

    @PostMapping("")
    public SolutionResource post(@RequestBody SolutionResource solutionResource){
        return new SolutionResource(solutionRepository.insert(solutionResource.toEntity()));
    }

    @PutMapping("{id}")
    public SolutionResource put(@PathVariable Integer id, @RequestBody SolutionResource solutionResource){
        return new SolutionResource(solutionRepository.update(id, solutionResource.toEntity()));
    }

    @DeleteMapping("{id}")
    public SolutionResource delete(@PathVariable Integer id){
        return new SolutionResource(solutionRepository.delete(id));
    }
}
