package com.demo.userapi.controllers;

import com.demo.userapi.entities.User;
import com.demo.userapi.entities.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("")
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping("")
    public User addNewUser(@RequestBody User newUSer){
        var user = new User();
        user.setFirstName(newUSer.getFirstName());
        user.setLastName(newUSer.getLastName());
        user.setPhone(newUSer.getPhone());
        userRepository.save(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
        return "Done";
    }

    @PutMapping("/{id}")
    public Optional<User> updateUser(@PathVariable Long id,@RequestBody User newUser){
        var updatedUser = userRepository.findById(id)
            .map(user -> {
                user.setFirstName(newUser.getFirstName());
                user.setLastName(newUser.getLastName());
                user.setPhone(newUser.getPhone());
                userRepository.save(user);
                return user;
            });

        return updatedUser;
    }
}
