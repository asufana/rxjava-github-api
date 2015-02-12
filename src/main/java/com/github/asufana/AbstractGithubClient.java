package com.github.asufana;

import java.util.*;

import lombok.*;

import org.apache.http.client.fluent.*;

import rx.*;
import rx.Observable;
import rx.functions.*;

import com.google.gson.reflect.*;

public abstract class AbstractGithubClient {
    
    protected <T> Observable<T> flat(final Observable<List<T>> entities) {
        return entities.flatMap(new Func1<List<T>, rx.Observable<T>>() {
            @Override
            public rx.Observable<T> call(final List<T> entities) {
                return rx.Observable.from(entities);
            }
        });
    }
    
    protected <T> Observable<T> request(final String url,
                                        final TypeToken<T> token) {
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
