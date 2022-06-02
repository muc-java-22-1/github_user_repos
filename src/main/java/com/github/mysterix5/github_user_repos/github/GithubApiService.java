package com.github.mysterix5.github_user_repos.github;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubApiService {

    public ResponseEntity<GithubGitRepo[]> fetchFromGithub(String username) {

        String api_url = "https://api.github.com/users/" + username + "/repos";
        return new RestTemplate().getForEntity(api_url, GithubGitRepo[].class);
    }

}
