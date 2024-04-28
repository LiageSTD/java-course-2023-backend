package edu.java.controller;

import edu.java.dto.scrapper.request.AddLinkRequest;
import edu.java.dto.scrapper.request.RemoveLinkRequest;
import edu.java.dto.scrapper.response.ApiErrorResponse;
import edu.java.dto.scrapper.response.LinkResponse;
import edu.java.dto.scrapper.response.ListLinksResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/links")
@RestController
public class LinksController {
    @Operation(summary = "Получить все отслеживаемые ссылки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Ссылки успешно получены",
                     content = @Content(
                         schema = @Schema(implementation = ListLinksResponse.class),
                         mediaType = MediaType.APPLICATION_JSON_VALUE
                     )
        ),
        @ApiResponse(responseCode = "400",
                     description = "Некорректные параметры запроса",
                     content = @Content(
                         schema = @Schema(implementation = ApiErrorResponse.class),
                         mediaType = MediaType.APPLICATION_JSON_VALUE
                     )
        )
    })
    @GetMapping
    public ResponseEntity<ListLinksResponse> getTrackedLinks(@RequestHeader("Tg-Chat-Id") int id) {
        log.info("Sent all links of " + id);
        return null;
    }

    @Operation(summary = "Добавить отслеживание ссылки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Ссылка успешно добавлена",
                     content = @Content(
                         schema = @Schema(implementation = LinkResponse.class),
                         mediaType = MediaType.APPLICATION_JSON_VALUE
                     )
        ),
        @ApiResponse(responseCode = "400",
                     description = "Некорректные параметры запроса",
                     content = @Content(
                         schema = @Schema(implementation = ApiErrorResponse.class),
                         mediaType = MediaType.APPLICATION_JSON_VALUE
                     )
        )
    })
    @PostMapping
    public ResponseEntity<LinkResponse> addLinkToTrack(
        @RequestHeader("Tg_Chat_id") int id,
        @Valid @RequestBody AddLinkRequest addLinkRequest
    ) {
        log.info("New link was added: " + addLinkRequest.getLink());
        return null;
    }

    @Operation(summary = "Убрать отслеживание ссылки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Ссылка успешно убрана",
                     content = @Content(
                         schema = @Schema(implementation = RemoveLinkRequest.class),
                         mediaType = MediaType.APPLICATION_JSON_VALUE
                     )
        ),
        @ApiResponse(responseCode = "400",
                     description = "\t\n"
                         +
                         "Некорректные параметры запроса",
                     content = @Content(
                         schema = @Schema(implementation = ApiErrorResponse.class),
                         mediaType = MediaType.APPLICATION_JSON_VALUE
                     )
        ),
        @ApiResponse(responseCode = "404",
                     description = "Ссылка не найдена",
                     content = @Content(
                         schema = @Schema(implementation = ApiErrorResponse.class),
                         mediaType = MediaType.APPLICATION_JSON_VALUE
                     )
        )
    })
    @DeleteMapping
    public ResponseEntity<LinkResponse> deleteLink(
        @RequestHeader("Tg_Chat_Id") int id,
        @Valid @RequestBody RemoveLinkRequest removeLinkRequest
    ) {
        log.info("Link was removed: " + removeLinkRequest.getLink());
        return null;
    }
}
