package com.switchfully.eurder.api.dto.users;

import com.switchfully.eurder.domain.entities.User;

import java.util.UUID;

public class UserDTO {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;
    private final String phoneNumber;
    private final User.Role role;

    private UserDTO(UUID id, String firstName, String lastName, String email, String address, String phoneNumber, User.Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public UUID getId() {
        return id;
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

    public User.Role getRole() {
        return role;
    }

    public static class UserDTOBuilder {
        private UUID id;
        private String firstName;
        private String lastName;
        private String email;
        private String address;
        private String phoneNumber;
        private User.Role role;

        public static UserDTO.UserDTOBuilder item() {
            return new UserDTO.UserDTOBuilder();
        }

        public UserDTO build() {
            return new UserDTO(id, firstName, lastName, email, address, phoneNumber, role);
        }

        public UserDTO.UserDTOBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public UserDTO.UserDTOBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDTO.UserDTOBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDTO.UserDTOBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserDTO.UserDTOBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public UserDTO.UserDTOBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserDTO.UserDTOBuilder withRole(User.Role role) {
            this.role = role;
            return this;
        }

    }
}
