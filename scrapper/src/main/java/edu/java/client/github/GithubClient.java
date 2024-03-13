package edu.java.client.github;

import edu.java.client.github.dto.EventsResponse;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface GithubClient {
    @GetExchange("/repos/{owner}/{repo}/events")
    List<EventsResponse> getEvents(@PathVariable("repo") String repo, @PathVariable("owner") String owner);
}
