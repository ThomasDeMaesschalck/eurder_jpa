package com.switchfully.eurder.api;

import com.switchfully.eurder.api.dto.CreateUserDTO;
import com.switchfully.eurder.api.dto.UserDTO;
import com.switchfully.eurder.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/admins")
public class AdminController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createAdmin(@RequestBody CreateUserDTO createUserDTO) {
        logger.info("Creating new admin for email: " + createUserDTO.getEmail());
        return userService.createAdmin(createUserDTO);
    }
}
