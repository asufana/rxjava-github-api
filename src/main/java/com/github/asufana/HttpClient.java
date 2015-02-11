package com.github.asufana;

import java.io.*;

import org.apache.http.client.*;
import org.apache.http.client.fluent.*;

public class HttpClient {
    
    public static Content get(final String url) throws ClientProtocolException, IOException {
        return Request.Get(url).execute().returnContent();
    }
}
