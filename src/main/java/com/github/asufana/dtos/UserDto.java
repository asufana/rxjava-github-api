package com.github.asufana.dtos;

import java.util.*;

import lombok.*;
import lombok.experimental.*;

import com.github.asufana.*;

@Getter
@Accessors(fluent = true)
public class UserDto {
    
    private String login;
    
    @Setter
    private List<RepositoryDto> repositories;
    
    public rx.Observable<RepositoryDto> fetchRepositories() {
        return new GithubClient().repositories(login);
    }
}
