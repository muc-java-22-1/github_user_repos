package com.github.mysterix5.github_user_repos.github;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubServiceTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getReposByUser(){
        String username = "mysterix5";
        var responseEntity = restTemplate.getForEntity("/github/" + username, GithubGitRepo[].class);

        System.out.println(Arrays.stream(responseEntity.getBody()).map(GithubGitRepo::getName).toList());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).extracting(GithubGitRepo::getName).contains("katas", "PasswordValidator");
    }
    @Test
    public void getReposByUser_WrongUsername(){
        String username = "non existing user 1234";

        assertThatThrownBy(()->restTemplate.getForEntity("/github/" + username, GithubGitRepo[].class)).isInstanceOf(RestClientException.class);
    }
}