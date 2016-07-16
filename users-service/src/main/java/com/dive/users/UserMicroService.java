package com.dive.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableEurekaClient //or @EnableDiscoveryClient
public class UserMicroService {

    public static void main(String[] args) {
        SpringApplication userMicroService = new SpringApplication(UserMicroService.class);
        userMicroService.addListeners(new ApplicationPidFileWriter("user-micro-service.pid"));
        userMicroService.run(args);
    }
}
