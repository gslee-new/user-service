package com.example.userservice.vo;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class RequestUser {
    private String email;
    private String pwd;
    private String name;
}
