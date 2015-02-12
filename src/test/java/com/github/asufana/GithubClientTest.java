package com.github.asufana;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import rx.observables.*;

import com.github.asufana.dtos.*;

public class GithubClientTest {
    
    private final String name = "asufana";
    
    @Test
    public void testUser() {
        final rx.Observable<UserDto> user = new GithubClient().user(name);
        assertThat(user, is(notNullValue()));
        user.forEach(u -> {
            final String loginName = u.login();
            assertThat(loginName, is(name));
            System.out.println("LoginName: " + loginName);
        });
    }
    
    @Test
    public void testRepository() throws Exception {
        final rx.Observable<RepositoryDto> repositories = new GithubClient().repositories(name);
        assertThat(repositories, is(notNullValue()));
        repositories.forEach(r -> {
            final String repoName = r.name();
            assertThat(repoName, is(notNullValue()));
            System.out.println("RepoName: " + repoName);
        });
    }
    
    @Test
    public void testUserAndReposSequential() throws Exception {
        final rx.Observable<UserDto> user = new GithubClient().user(name);
        assertThat(user, is(notNullValue()));
        
        final rx.Observable<RepositoryDto> repositories = user.flatMap(UserDto::fetchRepositories);
        assertThat(repositories, is(notNullValue()));
        repositories.forEach(r -> {
            final String repoName = r.name();
            assertThat(repoName, is(notNullValue()));
            System.out.println("RepoName: " + repoName);
        });
    }
    
    @Test
    public void testUserAndRepositoryEager() throws Exception {
        final BlockingObservable<UserDto> user = new GithubClient().userAndRepositories(name);
        assertThat(user, is(notNullValue()));
        user.forEach(u -> {
            System.out.println("User: " + u);
            final List<RepositoryDto> repositories = u.repositories();
            assertThat(repositories, is(notNullValue()));
            assertThat(repositories.size(), is(not(0)));
            System.out.println("Repo:" + repositories);
        });
    }
    
}
