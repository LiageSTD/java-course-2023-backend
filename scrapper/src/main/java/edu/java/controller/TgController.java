package edu.java.controller;

import edu.java.dto.model.User;
import edu.java.dto.scrapper.response.ApiErrorResponse;
import edu.java.service.databaseAccess.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/tg-chat")
@RestController
@Slf4j
@RequiredArgsConstructor
public class TgController {
    private final ChatService chatService;

    @Operation(summary = "Зарегистрировать чат")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Чат зарегистрирован",
                     content = @Content),
        @ApiResponse(responseCode = "400",
                     description = "Некорректные параметры запросы",
                     content = @Content(
                         schema = @Schema(implementation = ApiErrorResponse.class),
                         mediaType = MediaType.APPLICATION_JSON_VALUE
                     )),
        @ApiResponse(responseCode = "404",
                     description = "Чат не существует",
                     content = @Content(
                         schema = @Schema(implementation = ApiErrorResponse.class),
                         mediaType = MediaType.APPLICATION_JSON_VALUE
                     ))

    })
    @PostMapping("/{id}")
    public void registerChat(@PathVariable("id") long id) {
        log.info("New chat registered: " + id);
        chatService.add(new User(id));
    }

    @Operation(summary = "Удалить чат")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Чат успешно удален"),
        @ApiResponse(responseCode = "404",
                     description = "Чат не существует",
                     content = @Content(
                         schema = @Schema(implementation = ApiErrorResponse.class),
                         mediaType = MediaType.APPLICATION_JSON_VALUE
                     ))
    })
    @DeleteMapping("/{id}")
    public void deleteChat(@PathVariable("id") long id) {
        log.info("Removed chat: " + id);
        chatService.remove(new User(id));
    }

    @Operation(summary = "Узнать статус регистрации пользователя")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Запрос успешно обработан"),
        @ApiResponse(responseCode = "400",
                     description = "Некорректные параметры запроса",
                     content = @Content(
                         schema = @Schema(implementation = ApiErrorResponse.class),
                         mediaType = MediaType.APPLICATION_JSON_VALUE
                     ))
    })
    @GetMapping("/{id}")
    public boolean isChatRegistered(@PathVariable("id") long id) {
        log.info("Checking chat: " + id);
        return chatService.exists(id);
    }
}
