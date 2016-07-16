package com.dive.users.service;

import com.dive.users.model.User;
import com.dive.users.repository.StubUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private StubUserRepository stubUserRepository;

    public Observable<User> findAll() {
        return Observable.defer(() -> stubUserRepository.findAll());
    }

    @Async
    public void create(String name, String lastName, String fullName, String city) {
        stubUserRepository
                .save(
                        new User()
                                .setUserId(UUID.randomUUID().toString())
                                .setName(name)
                                .setLastName(lastName)
                                .setFullName(fullName)
                                .setCity(city)
                );
    }

    @Async
    public void create(User user) {
        stubUserRepository.save(user);
    }


}
