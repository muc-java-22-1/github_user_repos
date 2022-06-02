package com.github.mysterix5.github_user_repos.github;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubService {

    private final GithubApiService githubApiService;


    public ResponseEntity<GithubGitRepo[]> getGithubRepoInfoByUsername(String username) {

        try {
            return githubApiService.fetchFromGithub(username);
        } catch(HttpClientErrorException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<List<String>> getGithubRepoStringsByUsername(String username) {

        try {
            var githubResponse = githubApiService.fetchFromGithub(username);
            return new ResponseEntity<>(Arrays.stream(githubResponse.getBody()).map(GithubGitRepo::getName).toList(), HttpStatus.OK);
        } catch(HttpClientErrorException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
