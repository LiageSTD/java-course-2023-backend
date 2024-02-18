package edu.java.bot.service.linksHandler.parsers;

import edu.java.bot.service.linksHandler.links.GitHubLink;
import edu.java.bot.service.linksHandler.links.Link;
import org.springframework.stereotype.Component;

@Component
public class GitHubLinkParser implements LinkParser {

    @Override
    public Link giveNewLink(String host, String path) {
        return new GitHubLink(host, path);
    }

    @Override
    public String getHost() {
        return "github.com";
    }
}
