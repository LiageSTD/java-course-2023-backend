package edu.java.client.github.impl;

import edu.java.client.github.GithubClient;
import edu.java.client.github.GithubService;
import edu.java.client.github.response.EventsResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GitHubClientImpl implements GithubClient {
    private final GithubService githubService;

    @Override
    public List<EventsResponse> fetchEvents(String username, String repository) {
        return githubService.getEvents(repository, username);
    }
}
