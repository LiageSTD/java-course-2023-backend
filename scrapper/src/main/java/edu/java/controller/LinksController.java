package edu.java.controller;

import edu.java.dto.model.Link;
import edu.java.dto.model.User;
import edu.java.dto.scrapper.request.AddLinkRequest;
import edu.java.dto.scrapper.request.RemoveLinkRequest;
import edu.java.dto.scrapper.response.ApiErrorResponse;
import edu.java.dto.scrapper.response.LinkResponse;
import edu.java.dto.scrapper.response.ListLinksResponse;
import edu.java.service.databaseAccess.LinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("MultipleStringLiterals")
@Slf4j @RequiredArgsConstructor @RestController @RequestMapping("/links") public class LinksController {
    private final LinkService linkService;

    @Operation(summary = "Получить отслеживаемые ссылки", description = "", tags = {}) @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Ссылки успешно выданы",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = LinkResponse.class))),

        @ApiResponse(responseCode = "400",
                     description = "Некорректные параметры запроса",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = ApiErrorResponse.class)))}) @GetMapping
    public ListLinksResponse getLinks(@RequestHeader("Tg-Chat-Id") Long tgChatId) {
        log.info("Getting links for chat {}", tgChatId);

        List<Link> rawLinks = linkService.findAllByUserId(new User(tgChatId));
        List<LinkResponse> links = new ArrayList<>();
        for (Link link : rawLinks) {
            URI uri;
            try {
                uri = URI.create(link.getUrl());
            } catch (final IllegalArgumentException | NullPointerException e) {
                log.error("Unable to get URI {}", link.getUrl());
                uri = null;
            }
            links.add(new LinkResponse(link.getId(), uri));
        }
        return new ListLinksResponse(links, links.size());
    }

    @Operation(summary = "Добавить ссылку для отслеживания", description = "", tags = {}) @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Ссылка успешно получена",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = ListLinksResponse.class))),

        @ApiResponse(responseCode = "400",
                     description = "Некорректные параметры запроса",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = ApiErrorResponse.class)))}) @PostMapping
    public LinkResponse addLink(
        @RequestHeader("Tg-Chat-Id") Long id, @Valid @RequestBody AddLinkRequest addLinkRequest
    ) {
        log.info("Adding link {} for chat {}", addLinkRequest.getLink(), id);
        linkService.add(new User(id), new Link(0L, addLinkRequest.getLink(), OffsetDateTime.now(), false));
        URI uri;
        try {
            uri = URI.create(addLinkRequest.getLink());
        } catch (final IllegalArgumentException | NullPointerException e) {
            log.error("Unable to get URI {}", addLinkRequest.getLink());
            uri = null;
        }
        return new LinkResponse(id, uri);
    }

    @Operation(summary = "Убрать отслеживание ссылки", description = "", tags = {}) @ApiResponses(value = {@ApiResponse(
        responseCode = "200",
        description = "Ссылка успешно убрана",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = LinkResponse.class))),

        @ApiResponse(responseCode = "400",
                     description = "Некорректные параметры запроса",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = ApiErrorResponse.class))),

        @ApiResponse(responseCode = "404",
                     description = "Ссылка не найдена",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = ApiErrorResponse.class)))}) @DeleteMapping
    public LinkResponse removeLink(
        @RequestHeader("Tg-Chat-Id") Long id, @Valid @RequestBody RemoveLinkRequest removeLinkRequest
    ) {
        log.info("Removing link {} for chat {}", removeLinkRequest.getLink(), id);
        linkService.remove(new User(id), new Link(0L, removeLinkRequest.getLink(), null, false));
        URI uri;
        try {
            uri = URI.create(removeLinkRequest.getLink());
        } catch (final IllegalArgumentException | NullPointerException e) {
            log.error("Unable to get URI {}", removeLinkRequest.getLink());
            uri = null;
        }
        return new LinkResponse(id, uri);

    }

}
