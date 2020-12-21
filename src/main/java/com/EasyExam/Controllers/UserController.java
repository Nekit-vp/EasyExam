package com.EasyExam.Controllers;



import com.EasyExam.Entities.User;
import com.EasyExam.Repositories.UserRepository;
import com.EasyExam.Resources.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin("http://192.168.0.109:8081")
@RestController
@RequestMapping("user")
public class UserController {

    protected final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public UserResource[] getAll(){
        return Arrays.stream(userRepository.select())
                .map(user -> new UserResource(user))
                .toArray(UserResource[]::new);
    }

    @GetMapping("{id}")
    public UserResource get(@PathVariable Integer id){
        return new UserResource(userRepository.select(id));
    }

    @PostMapping("")
    public UserResource post(@RequestBody UserResource userResource){
        return new UserResource(userRepository.insert(userResource.toEntity()));
    }

    @PutMapping("{id}")
    public UserResource put(@PathVariable Integer id, @RequestBody UserResource userResource){
        return new UserResource(userRepository.update(id, userResource.toEntity()));
    }

    @DeleteMapping("{id}")
    public UserResource delete(@PathVariable Integer id){
        return new UserResource(userRepository.delete(id));
    }
}
