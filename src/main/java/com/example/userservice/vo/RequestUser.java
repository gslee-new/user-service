package com.example.userservice.vo;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestUser {
    @NotNull(message = "email cannot be null")
    @Size(min= 2, message = "email not be less than two character")
    @Email
    private String email;

    @NotNull(message = "Name cannot be null")
    @Size(min= 2, message = "Name not be less than two character")
    private String name;

    @NotNull(message = "Password cannot be null")
    @Size(min= 8, message = "Password not be less than eight character")
    private String pwd;
}
