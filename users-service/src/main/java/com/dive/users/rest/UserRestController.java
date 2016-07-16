package com.dive.users.rest;

import com.dive.users.model.User;
import com.dive.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserRestController {

    @Value("${spring.application.version:0.0.0}")
    private String version;
    @Autowired
    private UserService userService;

    @RequestMapping("/version")
    public ResponseEntity<String> version() {
        return ResponseEntity.ok(version);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.create(user);
        return ResponseEntity.ok("User created");
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public DeferredResult<List<User>> users() {
        DeferredResult<List<User>> userDeferredResult = new DeferredResult<>();
        userService
                .findAll()
                .toList()
                .subscribe(userDeferredResult::setResult);
        return userDeferredResult;
    }
}
