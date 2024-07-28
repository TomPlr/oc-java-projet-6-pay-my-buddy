package com.paymybuddy.controller;

import com.paymybuddy.dto.UserDto;
import com.paymybuddy.model.GenericResponseModel;
import com.paymybuddy.model.UserModel;
import com.paymybuddy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserModel> getUser(@PathVariable String username) {
        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<UserModel> updateUser(@RequestBody UserDto user, Principal principal) {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseModel> deleteUser() {
        return new ResponseEntity<>(userService.delete(), HttpStatus.OK);
    }

}
