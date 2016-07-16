package com.dive.api.users.service;

import com.dive.api.users.client.UserResource;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.dive.api.infrastructure.Collaborators.USERS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    public static final String VERSION = "1.0.0";
    @InjectMocks
    private UserService userService;

    @Mock
    private EurekaClient discoveryClient;
    @Mock
    private UserResource userResource;

    @Test
    public void userStatusCallsEurekaClient() {
        InstanceInfo instanceInfo = InstanceInfo.Builder.newBuilder()
                .setAppName(USERS)
                .setStatus(InstanceInfo.InstanceStatus.STARTING)
                .build();
        when(discoveryClient.getNextServerFromEureka(USERS, false))
                .thenReturn(instanceInfo);

        assertThat(userService.usersStatus())
                .isEqualTo(instanceInfo.getStatus());
    }

}