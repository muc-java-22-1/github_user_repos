package com.github.mysterix5.github_user_repos.github;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GithubGitRepo {

    private String name;
    private String ssh_url;

}
