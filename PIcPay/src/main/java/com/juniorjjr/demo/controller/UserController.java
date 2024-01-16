package com.juniorjjr.demo.controller;

import com.juniorjjr.demo.domain.User;
import com.juniorjjr.demo.domain.dto.UserDTO;
import com.juniorjjr.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User>createUser(@RequestBody UserDTO userDTO){
        User newUser = service.createUser(userDTO);
        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>>getALLUser(){
        var users = service.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public User findUserById(Long id) throws Exception {
        return this.service.findByid(id);
    }
}
