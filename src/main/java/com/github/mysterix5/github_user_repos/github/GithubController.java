package com.github.mysterix5.github_user_repos.github;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/github")
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService){
        this.githubService = githubService;
    }

    @GetMapping("/{user}")
    public GithubGitRepo[] getReposByUser(@PathVariable String user){

        return githubService.getGithubRepoInfoByUsername(user);
    }

    @GetMapping("/strings/{user}")
    public List<String> getRepoNamesByUser(@PathVariable String user){

        return Arrays.stream(githubService.getGithubRepoInfoByUsername(user)).map(GithubGitRepo::getName).toList();
    }

}
