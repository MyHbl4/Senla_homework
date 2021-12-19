package com.moon.senla.controller;

import com.moon.senla.dto.AdminUserDto;
import com.moon.senla.entity.User;
import com.moon.senla.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<AdminUserDto>> getAllUsers() {
        List<User> users = userService.getAll();
        List<AdminUserDto> adminUserDtoList = new ArrayList<>();

        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (User user:users) {
            adminUserDtoList.add(AdminUserDto.fromUser(user));
        }



        return new ResponseEntity<>(adminUserDtoList, HttpStatus.OK);
    }
}
