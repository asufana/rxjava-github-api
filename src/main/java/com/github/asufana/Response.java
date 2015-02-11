package com.github.asufana;

import org.apache.http.client.fluent.*;

import com.google.gson.*;
import com.google.gson.reflect.*;

public class Response {
    
    private final String json;
    
    private Response(final String json) {
        this.json = json;
    }
    
    public static Response parse(final Content content) {
        return new Response(content.asString());
    }
    
    public <T> T toObject(final TypeToken<T> token) {
        return new Gson().fromJson(json, token.getType());
    }
    
}
