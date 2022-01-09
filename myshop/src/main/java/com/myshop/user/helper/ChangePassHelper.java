package com.myshop.user.helper;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangePassHelper {

    @NotNull(message = " Username is mandatory")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotNull(message = " Old password is mandatory")
    @Length(min = 8, message = "Password must be at least 8 symbols")
    private String oldPassword;
    @NotNull(message = "New password is mandatory")
    @Length(min = 8, message = "Password must be at least 8 symbols")
    private String newPassword;
    @NotNull(message = "Repeated new password is mandatory")
    @Length(min = 8, message = "Repeated password must be at least 8 symbols")
    private String newPasswordAgain;

    public ChangePassHelper(String username, String oldPassword, String newPassword, String newPasswordAgain) {
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPasswordAgain = newPasswordAgain;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordAgain() {
        return newPasswordAgain;
    }

    public void setNewPasswordAgain(String newPasswordAgain) {
        this.newPasswordAgain = newPasswordAgain;
    }
}
