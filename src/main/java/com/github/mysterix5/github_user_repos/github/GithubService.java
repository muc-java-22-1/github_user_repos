package com.github.mysterix5.github_user_repos.github;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class GithubService {



    public ResponseEntity<GithubGitRepo[]> getGithubRepoInfoByUsername(String username) {

        try {
            var githubResponse = fetchFromGithub(username);
            return githubResponse;
        } catch(HttpClientErrorException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<List<String>> getGithubRepoStringsByUsername(String username) {

        try {
            var githubResponse = fetchFromGithub(username);
            return new ResponseEntity<>(Arrays.stream(githubResponse.getBody()).map(GithubGitRepo::getName).toList(), HttpStatus.OK);
        } catch(HttpClientErrorException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    private ResponseEntity<GithubGitRepo[]> fetchFromGithub(String username) {

        String api_url = "https://api.github.com/users/" + username + "/repos";
        ResponseEntity<GithubGitRepo[]> response;
        return new RestTemplate().getForEntity(api_url, GithubGitRepo[].class);
    }

}
