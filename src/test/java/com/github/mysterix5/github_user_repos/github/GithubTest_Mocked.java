package com.github.mysterix5.github_user_repos.github;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubTest_Mocked {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private GithubApiService githubApiService;

    @Test
    public void getReposByUser_MockedGithubApiCall(){
        Mockito.when(githubApiService.fetchFromGithub("mysterix5")).thenReturn(new ResponseEntity<>(new GithubGitRepo[]{new GithubGitRepo("katas", ""), new GithubGitRepo("PasswordValidator", "")}, HttpStatus.OK));

        String username = "mysterix5";
        var responseEntity = restTemplate.getForEntity("/github/" + username, GithubGitRepo[].class);

        System.out.println(Arrays.stream(responseEntity.getBody()).map(GithubGitRepo::getName).toList());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).extracting(GithubGitRepo::getName).contains("katas", "PasswordValidator");
    }
}