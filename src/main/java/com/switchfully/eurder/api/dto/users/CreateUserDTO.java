package com.switchfully.eurder.api.dto.users;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateUserDTO {

    @NotBlank(message = "First name can not be empty")
    @NotNull
    private final String firstName;

    @NotBlank(message = "Last name can not be empty")
    @NotNull
    private final String lastName;

    @NotBlank(message = "Email can not be empty")
    @Email(message = "Valid email needed")
    @NotNull
    private final String email;

    @NotBlank(message = "Address can not be empty")
    @NotNull
    private final String address;

    @NotBlank(message = "Phone can not be empty")
    @NotNull
    private final String phoneNumber;

    public CreateUserDTO(String firstName, String lastName, String email, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
