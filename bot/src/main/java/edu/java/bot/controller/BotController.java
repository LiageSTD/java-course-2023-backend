package edu.java.bot.controller;

import edu.java.bot.service.UpdateService;
import edu.java.dto.bot.request.LinkUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BotController {

    private final UpdateService updateService;

    @Operation(summary = "Отправить обновления")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Обновление обработано",
                     content = @Content)
    })

    @PostMapping("/updates")
    public void getUpdates(@Valid @RequestBody LinkUpdate linkUpdate) {
        updateService.sendUpdate(linkUpdate);
        log.info("Update received id: " + linkUpdate.getId());
    }
}

