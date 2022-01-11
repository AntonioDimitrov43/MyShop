package com.myshop.user.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is mandatory and cannot be blank!")
    @Column(name = "username", nullable = false)
    private String username;

    @NotBlank(message = "Name is mandatory and must not be blank!")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Password is mandatory and cannot be blank!")
    @Length(min = 8, message = "Password length must be at least 8 symbols")
    @Column(name = "password", nullable = false)
    private String password;


    @NotNull(message = "User must have status (ONLINE|OFFLINE)!")
    @Column(name = "status", nullable = false)
    private UserStatus status;

    @Email(message = "User email have to be valid!")
    @NotBlank(message = "Email is mandatory and cannot be blank!")
    @Column(name = "email", nullable = false)
    private String email;

    @Digits(integer = 10, fraction = 0, message = "Valid bulgarian number have to contain 10 digits!")
    @NotBlank(message = "Phone number is mandatory and cannot be blank!")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank(message = "Address is mandatory and cannot be blank!")
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "delivery_preferences")
    private String deliveryPreferences;

    public User() {
    }

    public User(String username, String name, String password, UserStatus status, String email, String phoneNumber, String address, String deliveryPreferences) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.status = status;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.deliveryPreferences = deliveryPreferences;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliveryPreferences() {
        return deliveryPreferences;
    }

    public void setDeliveryPreferences(String deliveryPreferences) {
        this.deliveryPreferences = deliveryPreferences;
    }
}
