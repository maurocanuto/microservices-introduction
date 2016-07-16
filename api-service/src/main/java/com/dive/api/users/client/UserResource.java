package com.dive.api.users.client;

import com.dive.api.users.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(value = "http://user-service", fallback = UserResourceImpl.class)
public interface UserResource {

    @RequestMapping(value = "/getUsers", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<User> findAll();

    @RequestMapping(value = "/version", method = GET)
    String version();

    @RequestMapping(value = "/addUser", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> addUser(@RequestBody User user);
}
