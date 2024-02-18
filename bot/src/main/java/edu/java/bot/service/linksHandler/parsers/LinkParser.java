package edu.java.bot.service.linksHandler.parsers;

import edu.java.bot.service.linksHandler.links.Link;
import edu.java.bot.utils.SolvedLink;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public interface LinkParser {
    Link giveNewLink(String host, String path);

    String getHost();

    default Link parseLink(String link) {
        try {
            URL linkToParse = new URL(link);
            linkToParse.toURI();
            return giveNewLink(linkToParse.getHost(), linkToParse.getPath());
        } catch (URISyntaxException | MalformedURLException e) {
            return null;
        }
    }

    default public boolean supports(String host) {
        return host.contains(getHost());
    }
}
