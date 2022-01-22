package com.to.wms.controller;

import com.to.wms.controller.dto.GenericPutDto;
import com.to.wms.model.User;
import com.to.wms.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<?>> getAll() {
        List<?> roles = userService.getAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addUser(
            @Valid @RequestBody User user
    ) {
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{login}")
    public ResponseEntity<User> getUser(
        @PathVariable String login
    ) {
        User user = userService.getUserByName(login);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{login}")
    public ResponseEntity<User> updateUser(
            @PathVariable String login,
            @RequestParam("change") String changeType,
            @RequestBody @Valid GenericPutDto genericPutDto
    ) {
        User user = userService.updateUser(login, changeType, genericPutDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{login}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable String login
    ) {
        userService.deleteUser(login);
        return ResponseEntity.ok().build();
    }

}
