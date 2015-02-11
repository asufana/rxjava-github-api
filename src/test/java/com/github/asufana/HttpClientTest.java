package com.github.asufana;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.apache.http.client.fluent.*;
import org.junit.*;

public class HttpClientTest {
    
    @Test
    public void test() {
        final Content content = HttpClient.get("http://www.google.co.jp");
        assertThat(content, is(notNullValue()));
        assertThat(content.asString(), is(notNullValue()));
        System.out.println(content.asString());
    }
    
}
