package com.example.userservice.controller;

import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/")
public class UserController {
    Environment env;
    UserService userService;
    Greeting greeting;

    @Autowired
    public UserController(UserService userService, Environment env, Greeting greeting) {
        this.env = env;
        this.userService = userService;
        this.greeting = greeting;
    }

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<RequestUser> createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        RequestUser map = mapper.map(userDto, RequestUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    @GetMapping("/email")
    public ResponseEntity<RequestUser> retrieveByEmail(@RequestParam String email) {
        UserDto userDto = userService.findByEmail(email);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        RequestUser map = mapper.map(userDto, RequestUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @GetMapping("users")
    public ResponseEntity<List<RequestUser>> retrieveAll() {
        List<UserDto> dtos = userService.findAll();
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<RequestUser> users = dtos.stream().map(p -> mapper.map(p, RequestUser.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


}
