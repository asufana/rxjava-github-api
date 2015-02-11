package com.github.asufana;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.*;

import org.apache.http.client.*;
import org.apache.http.client.fluent.*;
import org.junit.*;

import com.github.asufana.dtos.*;
import com.google.gson.reflect.*;

public class ResponseTest {
    
    private final String name = "asufana";
    
    @Test
    public void test() throws ClientProtocolException, IOException {
        final Content content = HttpClient.get(GithubClient.BASE_USER_URL
                + name);
        assertThat(content, is(notNullValue()));
        
        final UserDto user = Response.parse(content)
                                     .toObject(new TypeToken<UserDto>() {});
        assertThat(user, is(notNullValue()));
        assertThat(user.login(), is(name));
    }
}
