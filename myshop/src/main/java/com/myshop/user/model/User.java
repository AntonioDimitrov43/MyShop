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

    /**
     * Returns user's username
     * @return found user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets user's username
     * @param username valid username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets user ID
     * @return user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets user ID
     * @param id valid ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns user's name
     * @return found user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets user's name
     * @param name not null or blank
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns password
     * @return found valid password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets user's password
     * @param password valid password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns user status
     * @return found user status
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Sets user status
     * @param status found valid status
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * Returns user's email
     * @return found user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets user's email
     * @param email valid email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns user's phone number
     * @return found user's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets user's phone number
     * @param phoneNumber valid phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns user's address
     * @return found user's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets user's address
     * @param address valid address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns user's delivery preferences
     * @return found user's delivery preferences
     */
    public String getDeliveryPreferences() {
        return deliveryPreferences;
    }

    /**
     * Sets user's delivery preferences
     * @param deliveryPreferences valid delivery preferences
     */
    public void setDeliveryPreferences(String deliveryPreferences) {
        this.deliveryPreferences = deliveryPreferences;
    }
}
