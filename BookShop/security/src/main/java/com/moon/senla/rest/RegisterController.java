package com.moon.senla.rest;

import com.moon.senla.dto.UserDto;
import com.moon.senla.entity.User;
import com.moon.senla.services.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/")
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid User user){
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        userService.register(newUser);
        UserDto result = UserDto.fromUser(userService.findByUsername(newUser.getUsername()));
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute("user") @Valid User user,
//        BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//        {
//            return "/register";
//        }
//
//        userService.register(user);
//        return "/api/v1/auth/login";
//    }
}
