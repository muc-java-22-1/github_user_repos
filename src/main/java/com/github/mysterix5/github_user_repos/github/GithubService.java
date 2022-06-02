package com.github.mysterix5.github_user_repos.github;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubService {

    public GithubGitRepo[] getGithubRepoInfoByUsername(String username) {
        String api_url = "https://api.github.com/users/" + username + "/repos";
        RestTemplate template = new RestTemplate();
        ResponseEntity<GithubGitRepo[]> response = template.getForEntity(api_url, GithubGitRepo[].class);

        return response.getBody();
    }

}
