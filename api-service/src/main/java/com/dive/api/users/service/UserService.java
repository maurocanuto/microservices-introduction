package com.dive.api.users.service;

import com.dive.api.infrastructure.Collaborators;
import com.dive.api.users.client.UserResource;
import com.dive.api.users.model.User;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.List;

@Service
public class UserService {

    private final Log LOG = LogFactory.getLog(this.getClass());

    @Autowired
    @Lazy
    private EurekaClient discoveryClient;

    @Autowired
    private UserResource userResource;


    @HystrixCommand(groupKey = "tp-user-service", fallbackMethod = "statusNotFound")
    public InstanceStatus usersStatus() {
        return discoveryClient.getNextServerFromEureka(Collaborators.USERS, false)
                .getStatus();
    }

    public InstanceStatus statusNotFound() {
        return InstanceStatus.DOWN;
    }

    @HystrixCommand(groupKey = "tp-user-service", fallbackMethod = "usersAreDown")
    public Observable<String> statusPageUrl() {
        return Observable.just(
                discoveryClient.getNextServerFromEureka(Collaborators.USERS, false)
                        .getStatusPageUrl()
        );
    }

    @HystrixCommand(groupKey = "tp-user-service", fallbackMethod = "usersAreDown")
    public String version() {
        return userResource.version();
    }

    public String usersAreDown() {
        return "notificaton service is down";
    }

    public List<User> users() {
        return userResource.findAll();
    }

    public ResponseEntity<String> addUser(User user) {
        return userResource.addUser(user);
    }
}
