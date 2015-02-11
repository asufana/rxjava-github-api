package com.github.asufana;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import rx.*;

import com.github.asufana.dtos.*;

public class GithubClientTest {
    
    private final String name = "asufana";
    
    @Test
    public void testUser() {
        final Observable<UserDto> user = new GithubClient().user(name);
        assertThat(user, is(notNullValue()));
        user.forEach(u -> {
            final String loginName = u.login();
            assertThat(loginName, is(name));
            System.out.println("LoginName: " + loginName);
        });
    }
}
