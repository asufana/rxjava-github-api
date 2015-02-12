package com.github.asufana;

import java.util.*;

import lombok.*;

import org.apache.http.client.fluent.*;

import rx.*;
import rx.Observable;
import rx.functions.*;

import com.github.asufana.dtos.*;
import com.google.gson.reflect.*;

public class GithubClient {
    
    public static final String BASE_URL = "https://api.github.com";
    
    public static String userApiUrl(final String userName) {
        return BASE_URL + "/users/" + userName;
    }
    
    public static String repoApiUrl(final String userName) {
        return userApiUrl(userName) + "/repos";
    }
    
    public Observable<UserDto> user(final String userName) {
        return request(userApiUrl(userName), new TypeToken<UserDto>() {});
    }
    
    public Observable<RepositoryDto> repository(final String userName) {
        final Observable<List<RepositoryDto>> request = request(repoApiUrl(userName),
                                                                new TypeToken<List<RepositoryDto>>() {});
        return request.flatMap(new Func1<List<RepositoryDto>, rx.Observable<RepositoryDto>>() {
            @Override
            public rx.Observable<RepositoryDto> call(final List<RepositoryDto> repositories) {
                return rx.Observable.from(repositories);
            }
        });
    }
    
    private <T> Observable<T> request(final String url, final TypeToken<T> token) {
        return Observable.create(new RequestSubscriber(url))
                         .map(content -> Response.parse(content))
                         .map(response -> response.toObject(token));
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
