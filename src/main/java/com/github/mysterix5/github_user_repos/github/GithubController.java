package com.github.mysterix5.github_user_repos.github;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/github")
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService){
        this.githubService = githubService;
    }

    @GetMapping("/{user}")
    public ResponseEntity<GithubGitRepo[]> getReposByUser(@PathVariable String user){
        return githubService.getGithubRepoInfoByUsername(user);
    }

    @GetMapping("/strings/{user}")
    public ResponseEntity<List<String>> getRepoNamesByUser(@PathVariable String user){
        return githubService.getGithubRepoStringsByUsername(user);
    }

}
