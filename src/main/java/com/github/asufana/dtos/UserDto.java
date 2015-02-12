package com.github.asufana.dtos;

import lombok.Value;
import lombok.experimental.*;
import rx.*;

import com.github.asufana.*;

@Value
@Accessors(fluent = true)
public class UserDto {
    
    private String login;
    
    public Observable<RepositoryDto> fetchRepositories() {
        return new GithubClient().repository(login);
    }
}
