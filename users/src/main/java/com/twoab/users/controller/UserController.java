package com.twoab.users.controller;

import com.twoab.users.dto.UserDTO;
import com.twoab.users.model.User;
import com.twoab.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> listAll() {
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable UUID id) {
        return this.userService.findById(id);
    }

    @PostMapping
    public void create(@RequestBody UserDTO userDTO) {
        this.userService.create(userDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        this.userService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable UUID id, @RequestBody UserDTO userDTO) {
        this.userService.update(id, userDTO);
    }
}
