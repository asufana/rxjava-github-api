
# rxjava-GitHub API

GitHub API orchestration with RxJava

## Install

```
$ git clone https://github.com/asufana/rxjava-githubapi.git
$ cd rxjava-github
$ mvn install
```

## How to use

### User

```java
rx.Observable<UserDto> user = new GithubClient().user("asufana");
user.forEach(u -> System.out.println("LoginName: " + u.login()));
```

### Repositories

```java
rx.Observable<RepositoryDto> repositories = new GithubClient().repositories(name);
repositories.forEach(r -> System.out.println("RepoName: " + r.name()));
```

### User and Repositories sequential

```java
rx.Observable<UserDto> user = new GithubClient().user(name);
rx.Observable<RepositoryDto> repositories = user.flatMap(UserDto::fetchRepositories);
repositories.forEach(r -> System.out.println("RepoName: " + r.name()));
```

### User and Repositories eager

```java
BlockingObservable<UserDto> user = new GithubClient().userAndRepositories(name);
user.forEach(u -> {
    System.out.println("User: " + u);
    System.out.println("Repo:" + u.repositories());
});
```
