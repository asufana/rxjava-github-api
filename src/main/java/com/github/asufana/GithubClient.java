package com.github.asufana;

import lombok.*;

import org.apache.http.client.fluent.*;

import rx.*;

import com.github.asufana.dtos.*;
import com.google.gson.reflect.*;

public class GithubClient {
    
    public static final String BASE_URL = "https://api.github.com";
    public static final String BASE_USER_URL = BASE_URL + "/users/";
    
    public Observable<UserDto> user(final String userName) {
        return Observable.create(new RequestSubscriber(BASE_USER_URL + userName))
                         .map(content -> Response.parse(content))
                         .map(response -> response.toObject(new TypeToken<UserDto>() {}));
    }
    
    @Value
    private static class RequestSubscriber implements Observable.OnSubscribe<Content> {
        private final String url;
        
        @Override
        public void call(final Subscriber<? super Content> subscriber) {
            try {
                final Content content = HttpClient.get(url);
                subscriber.onNext(content);
            }
            catch (final Exception e) {
                subscriber.onError(e);
            }
            subscriber.onCompleted();
        }
    }
}
