package com.github.asufana;

import java.util.*;

import rx.Observable;
import rx.functions.*;
import rx.observables.*;
import rx.schedulers.*;

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
    
    public Observable<RepositoryDto> repositories(final String userName) {
        final Observable<List<RepositoryDto>> repositories = repositoryList(userName);
        return flat(repositories);
    }
    
    private Observable<List<RepositoryDto>> repositoryList(final String userName) {
        return request(repoApiUrl(userName),
                       new TypeToken<List<RepositoryDto>>() {});
    }
    
    public BlockingObservable<UserDto> userAndRepositories(final String userName) {
        final Observable<UserDto> user = user(userName).subscribeOn(Schedulers.newThread());
        final Observable<List<RepositoryDto>> repositories = repositoryList(userName).subscribeOn(Schedulers.newThread());
        return Observable.zip(user,
                              repositories,
                              new Func2<UserDto, List<RepositoryDto>, UserDto>() {
                                  @Override
                                  public UserDto call(final UserDto user,
                                                      final List<RepositoryDto> repositoryList) {
                                      return user.repositories(repositoryList);
                                  }
                              })
                         .toBlocking();
    }
    
}
