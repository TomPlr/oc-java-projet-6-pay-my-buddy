package com.paymybuddy.controller;

import com.paymybuddy.dto.UserDto;
import com.paymybuddy.model.GenericResponseModel;
import com.paymybuddy.dto.RegisterFormDto;
import com.paymybuddy.model.UserModel;
import com.paymybuddy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<UserModel> create(@RequestBody RegisterFormDto registerFormDto) {
        UserDto user = new UserDto( registerFormDto.getUsername(), registerFormDto.getEmail(), registerFormDto.getPassword(), null);

        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<UserModel> getUser() {
        return new ResponseEntity<>(userService.findUser(), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<UserModel> updateUser(@RequestBody UserDto user) {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseModel> deleteUser() {
        return new ResponseEntity<>(userService.delete(), HttpStatus.OK);
    }

    @PostMapping("/add-connection")
    public ResponseEntity<UserModel> addConnection(@RequestParam String email) {
        return new ResponseEntity<>(userService.addConnection(email), HttpStatus.CREATED);
    }

}
