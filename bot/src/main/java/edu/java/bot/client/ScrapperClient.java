package edu.java.bot.client;

import edu.java.dto.scrapper.response.LinkResponse;
import edu.java.dto.scrapper.response.ListLinksResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface ScrapperClient {
    @PostExchange("tg-chat/{id}")
    void registerChat(@PathVariable("id") int id);

    @DeleteExchange("tg-chat/{id}")
    void deleteChat(@PathVariable("id") int id);

    @GetExchange("/links")
    ResponseEntity<ListLinksResponse> getAllLinks();

    @PostExchange("/links")
    ResponseEntity<LinkResponse> trackLink();

    @DeleteExchange("/links")
    ResponseEntity<LinkResponse> deleteLink();

}
