package com.github.asufana;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.*;

import org.apache.http.client.*;
import org.apache.http.client.fluent.*;
import org.junit.*;

public class HttpClientTest {
    
    private final String name = "asufana";
    
    @Test
    public void test() throws ClientProtocolException, IOException {
        final Content content = HttpClient.get(GithubClient.BASE_USER_URL
                + name);
        assertThat(content, is(notNullValue()));
        assertThat(content.asString(), is(notNullValue()));
        System.out.println(content.asString());
    }
    
}
