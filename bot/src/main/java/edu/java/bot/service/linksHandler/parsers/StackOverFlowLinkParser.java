package edu.java.bot.service.linksHandler.parsers;

import edu.java.bot.service.linksHandler.links.Link;
import edu.java.bot.service.linksHandler.links.StackOverFlowLink;
import org.springframework.stereotype.Component;

@Component
public class StackOverFlowLinkParser implements LinkParser {
    @Override
    public Link giveNewLink(String host, String path) {
        return new StackOverFlowLink(host, path);
    }

    @Override
    public String getHost() {
        return "stackoverflow.com";
    }
}
