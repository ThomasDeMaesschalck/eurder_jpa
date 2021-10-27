package com.switchfully.eurder.domain;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;
import java.util.UUID;

public class User {

    private final UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private Role role;

    public static User createUser(String firstName, String lastName, String email, String address, String phoneNumber) {
        return new User(firstName, lastName, email, address, phoneNumber, Role.REGISTERED);
    }

    public static User createAdmin(String firstName, String lastName, String email, String address, String phoneNumber) {
        return new User(firstName, lastName, email, address, phoneNumber, Role.ADMIN);
    }

    private User(String firstName, String lastName, String email, String address, String phoneNumber, Role role) {
        this.id = UUID.randomUUID();
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setRole(role);
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("User first name required");
        }

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("User last name required");
        }

        this.lastName = lastName;
    }

    public void setEmail(String email) {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException("Invalid e-mail for user");
        }

        this.email = email;
    }

    public void setAddress(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address required");
        }

        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone number required");
        }

        this.phoneNumber = phoneNumber;
    }

    public void setRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role can't be null");
        }

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

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public enum Role {
        REGISTERED,
        ADMIN
    }
}
