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
    public UserDTO createUser(@RequestParam(value = "makeAdmin", required = false) boolean makeAdmin,
                              @RequestBody CreateUserDTO createUserDTO) {
        return userService.decideWhichTypeOfUserToCreate(createUserDTO, makeAdmin);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers(@RequestHeader(value = "adminId") UUID adminId) {
        logger.info("Admin with id " + adminId + " getting all users");
        return userService.getAllUsers(adminId);
    }

    @GetMapping(path = "{userId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@RequestHeader(value = "adminId") UUID adminId, @PathVariable UUID userId) {
        logger.info("Admin with id " + adminId + " getting user with id " + userId);
        return userService.getById(adminId, userId);
    }

}
