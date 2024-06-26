package edu.java.configuration;

import edu.java.client.github.GithubClient;
import edu.java.client.stackoverflow.StackOverFlowClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class UpdateScheduler {
    private final GithubClient githubClient;
    private final StackOverFlowClient stackOverFlowClient;
    private final ApplicationConfig applicationConfig;

    @Scheduled(fixedDelayString = "#{@scheduler.interval().toMillis()}")
    public void update() {
        log.info("Starting links update");
        //...
        log.info("Links was updated");
    }
}
