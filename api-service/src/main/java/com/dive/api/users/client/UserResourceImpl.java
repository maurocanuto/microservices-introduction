package com.dive.api.users.client;

import com.dive.api.users.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserResourceImpl implements UserResource {
    
    @Override
    public List<User> findAll() {
        return new ArrayList<>();
    }

    @Override
    public String version() {
        return "0.0.0-error";
    }

    @Override
    public ResponseEntity<String> addUser(User user) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user " + user.getFullName() + "\n");
    }

}
