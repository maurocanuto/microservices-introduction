package com.dive.api.users;

import com.dive.api.users.model.User;
import com.dive.api.users.service.UserService;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/users")
public class UserStatusRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = GET)
    public List<User> findAll() {
        return userService.users();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody User user) {
        return userService.addUser(user);

    }
    @RequestMapping(method = GET, value = "/status")
    public InstanceStatus status() {
        return userService.usersStatus();
    }

    @RequestMapping(method = GET, value = "/statusPage")
    public DeferredResult<String> getStatusPageUrl() {
        DeferredResult<String> result = new DeferredResult<>();
        userService.statusPageUrl().single()
                .subscribe(
                        result::setResult,
                        result::setErrorResult
                );
        return result;
    }

    @RequestMapping("/version")
    public String version() {
        return userService.version();
    }


}
