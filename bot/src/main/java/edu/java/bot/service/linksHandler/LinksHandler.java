package edu.java.bot.service.linksHandler;

import edu.java.bot.service.linksHandler.links.Link;
import edu.java.bot.service.linksHandler.parsers.LinkParser;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class LinksHandler {
    List<? extends LinkParser> linkParsers;

    public Link checkLink(String linkToSolve) {
        String link = parseLink(linkToSolve);
        for (LinkParser parser : linkParsers) {
            if (parser.supports(link)) {
                return parser.parseLink(link);
            }
        }
        return null;
    }

    private String parseLink(String link) {
        int pos = 1;
        while (pos < link.length() - 1) {
            if (Character.isWhitespace(link.charAt(pos))) {
                break;
            }
            pos++;
        }
        while (pos < link.length() - 1) {
            if (!Character.isWhitespace(link.charAt(pos))) {
                break;
            }
            pos++;
        }
        return link.substring(pos).trim();
    }
}
