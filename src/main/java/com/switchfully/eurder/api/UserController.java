package com.switchfully.eurder.api;

import com.switchfully.eurder.api.dto.users.CreateUserDTO;
import com.switchfully.eurder.api.dto.users.UserDTO;
import com.switchfully.eurder.services.UserService;
import com.switchsecure.SecurityGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public UserDTO createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        logger.info("Creating new user for email: " + createUserDTO.getEmail());
        return userService.createUser(createUserDTO);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.ADMIN)
    public List<UserDTO> getAllUsers() {
        logger.info("Admin getting all users");
        return userService.getAllUsers();
    }

    @GetMapping(path = "{userId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.ADMIN)
    public UserDTO getUserById(@PathVariable Long userId) {
        logger.info("Admin getting user with id " + userId);
        return userService.getById(userId);
    }

}
