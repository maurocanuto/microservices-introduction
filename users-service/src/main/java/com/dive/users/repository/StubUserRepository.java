package com.dive.users.repository;

import com.dive.users.model.User;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Repository;
import rx.Observable;

import java.util.Set;

@Repository
public class StubUserRepository {

    private Set<User> userList = Sets.newConcurrentHashSet();

    public Observable<User> findAll() {
        return Observable.from(userList);
    }

    public void save(User user) {
        userList.add(user);
    }

}
