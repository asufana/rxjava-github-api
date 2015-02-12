package com.github.asufana;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.*;

import org.apache.http.client.*;
import org.apache.http.client.fluent.*;
import org.junit.*;

public class HttpClientTest {
    
    private final String userName = "asufana";
    
    @Test
    public void testUser() throws ClientProtocolException, IOException {
        final Content content = HttpClient.get(GithubClient.userApiUrl(userName));
        assertThat(content, is(notNullValue()));
        assertThat(content.asString(), is(notNullValue()));
        System.out.println(content.asString());
    }
    
    @Test
    public void testRepository() throws ClientProtocolException, IOException {
        final Content content = HttpClient.get(GithubClient.userApiUrl(userName));
        assertThat(content, is(notNullValue()));
        assertThat(content.asString(), is(notNullValue()));
        System.out.println(content.asString());
    }
    
}
