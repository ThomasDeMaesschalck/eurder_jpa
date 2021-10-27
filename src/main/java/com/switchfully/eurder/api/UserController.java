package com.switchfully.eurder.api;

import com.switchfully.eurder.api.dto.CreateUserDTO;
import com.switchfully.eurder.api.dto.UserDTO;
import com.switchfully.eurder.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestParam(value = "makeAdmin", required = false) boolean makeAdmin, @RequestBody CreateUserDTO createUserDTO) {
        if (makeAdmin) {
            logger.info("Creating new admin for email: " + createUserDTO.getEmail());
            return userService.saveAdmin(createUserDTO);
        } else {
            logger.info("Creating new user for email: " + createUserDTO.getEmail());
            return userService.saveUser(createUserDTO);
        }
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers(@RequestHeader("adminId") UUID adminId) {
        logger.info("User with id " + adminId + " getting all users");
        return userService.getAllUsers(adminId);
    }

}
