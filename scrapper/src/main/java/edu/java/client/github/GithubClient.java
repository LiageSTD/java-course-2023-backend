package edu.java.client.github;

import edu.java.client.github.response.EventsResponse;
import java.util.List;

public interface GithubClient {
    List<EventsResponse> fetchEvents(String username, String repository);
}
