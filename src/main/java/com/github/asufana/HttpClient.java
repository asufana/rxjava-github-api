package com.github.asufana;

import java.io.*;

import org.apache.http.client.fluent.*;

public class HttpClient {
    
    public static Content get(final String url) {
        try {
            return Request.Get(url).execute().returnContent();
        }
        catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
