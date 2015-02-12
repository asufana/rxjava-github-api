package com.github.asufana;

import java.util.*;

import rx.Observable;

import com.github.asufana.dtos.*;
import com.google.gson.reflect.*;

public class GithubClient extends AbstractGithubClient {
    
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
        final Observable<List<RepositoryDto>> repositories = request(repoApiUrl(userName),
                                                                     new TypeToken<List<RepositoryDto>>() {});
        return flat(repositories);
    }
    
}
