package edu.java.bot.service.linksHandler;

import edu.java.bot.service.linksHandler.links.GitHubLink;
import edu.java.bot.service.linksHandler.links.StackOverFlowLink;
import edu.java.bot.service.linksHandler.parsers.GitHubLinkParser;
import edu.java.bot.service.linksHandler.parsers.StackOverFlowLinkParser;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinksHandlerTest {
    LinksHandler linksHandler = new LinksHandler(List.of(new GitHubLinkParser(), new StackOverFlowLinkParser()));

    @Test
    void parseGithubLinkTest() {
        String link = "/track https://github.com/pengrad/java-telegram-bot-api";
        Assertions.assertEquals(
            linksHandler.checkLink(link),
            new GitHubLink("github.com", "/pengrad/java-telegram-bot-api")
        );
    }

    @Test
    void parseGithubLinkIncorrectTest() {
        String link = "/track https://gethub.com/pengrad/java-telegram-bot-api";
        Assertions.assertNull(linksHandler.checkLink(link));
    }

    @Test
    void parseStackOverFlowLinkTest() {
        String link = "/track https://stackoverflow.com/questions/2230676/how-to-check-for-a-valid-url-in-java";
        Assertions.assertEquals(
            linksHandler.checkLink(link),
            new StackOverFlowLink("stackoverflow.com", "/questions/2230676/how-to-check-for-a-valid-url-in-java")
        );
    }

    @Test
    void parseStackOverFlowLinkIncorrectTest() {
        String link = "/track https://STEPIK.com/questions/2230676/how-to-check-for-a-valid-url-in-java";
        Assertions.assertNull(linksHandler.checkLink(link));
    }

    @Test
    void parseNoLinkTest() {
        String link = "/track ";
        Assertions.assertNull(linksHandler.checkLink(link));
    }

    @Test
    void parseNoSpacesAfterLinkTest() {
        String link = "/track";
        Assertions.assertNull(linksHandler.checkLink(link));
    }

    @Test
    void parseUnsupportedResourceLink() {
        String link = "/track https://www.youtube.com/watch?v=dQw4w9WgXcQ";
        Assertions.assertNull(linksHandler.checkLink(link));
    }
}
