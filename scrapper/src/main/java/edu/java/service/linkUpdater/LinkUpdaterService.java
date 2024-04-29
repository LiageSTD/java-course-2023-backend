package edu.java.service.linkUpdater;

import edu.java.client.bot.BotClient;
import edu.java.client.github.GithubClient;
import edu.java.client.github.dto.EventsResponse;
import edu.java.client.stackoverflow.StackOverFlowClient;
import edu.java.client.stackoverflow.dto.QuestionEventResponse;
import edu.java.configuration.ApplicationConfig;
import edu.java.service.databaseAccess.ChatService;
import edu.java.service.databaseAccess.LinkService;
import edu.java.dto.bot.request.LinkUpdateRequest;
import edu.java.dto.model.Link;
import edu.java.dto.model.User;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("MultipleStringLiterals")
public class LinkUpdaterService implements LinkUpdater {
    private final ApplicationConfig applicationConfig;
    private final LinkService linkService;
    private final GithubClient githubClient;
    private final StackOverFlowClient stackOverFlowClient;
    private final BotClient botClient;
    private final ChatService chatService;

    @Override
    public int update() {
        log.info("Updating links in updater");
        Collection<Link> unUpdatedLinks =
            linkService.listAllByTime(OffsetDateTime.now().minus(applicationConfig.getUpdateInterval()));
        long amountOfUpdatedLinks = 0;

        for (Link link : unUpdatedLinks) {
            boolean result;

            try {
                URI url = new URI(link.getUrl());
                switch (url.getHost()) {
                    case "github.com":
                        result = updateGihubLink(link);
                        break;
                    case "stackoverflow.com":
                        result = updateStackOverFlowLink(link);
                        break;
                    default:
                        result = false;
                        log.error("Unsupported host: {}", url.getHost());
                        processUnupdatableLink(link);
                }
            } catch (URISyntaxException e) {
                log.error("Malformed URL: {}", link.getUrl());
                processUnupdatableLink(link);
                continue;
            }
            if (result) {
                link.setUpdatedAt(OffsetDateTime.now());
                linkService.update(new User(0L), link);
                amountOfUpdatedLinks++;
            }
        }
        return (int) amountOfUpdatedLinks;
    }

    private boolean updateStackOverFlowLink(Link link) {
        String qID;
        try {
            URI uri = new URI(link.getUrl());
            String path = uri.getPath();
            String[] parts = path.split("/");
            qID = parts[2];
        } catch (URISyntaxException e) {
            log.error("Malformed URL: {}", link.getUrl());
            processUnupdatableLink(link);
            return false;
        }
        QuestionEventResponse events = stackOverFlowClient.getQuestions(qID);
        if (events != null) {
            OffsetDateTime recentUpdate = events.getRepliesList().get(0).getLastActivityDate();
            if (recentUpdate.isAfter(link.getUpdatedAt())) {
                sendNotification(link, "New activity");
                return true;
            }
        }

        return false;
    }

    private boolean updateGihubLink(Link link) {
        String repo;
        String owner;
        try {
            URI uri = new URI(link.getUrl());
            String path = uri.getPath();
            String[] parts = path.split("/");
            owner = parts[1];
            repo = parts[2];
        } catch (URISyntaxException e) {
            log.error("Malformed URL: {}", link.getUrl());
            processUnupdatableLink(link);
            return false;
        }
        List<EventsResponse> events = githubClient.getEvents(repo, owner);
        if (events.isEmpty()) {
            return false;
        }
        for (EventsResponse event : events) {
            if (event != null) {
                if (event.getCreatedAt().isAfter(link.getUpdatedAt())) {
                    String description = "New activity: " + event.getEventType();
                    sendNotification(
                        link,
                        description
                    );
                    return true;
                }
            }
        }
        return false;
    }

    private void processUnupdatableLink(Link link) {
        if (link.isUnableToUpdate()) {
            linkService.remove(new User(0L), link);
            sendNotification(
                link,
                "Sorry, we have untracked your link because we can't get info about it. \n" + link.getUrl()
            );
        } else {
            link.setUnableToUpdate(true);
            linkService.update(new User(0L), link);
        }
    }

    private void sendNotification(Link link, String message) {
        LinkUpdateRequest linkUpdateRequest = new LinkUpdateRequest(
            link.getId(),
            link.getUrl(),
            message,
            chatService.getUsersByLink(link.getId())
        );
        botClient.sendUpdate(linkUpdateRequest);
    }
}
