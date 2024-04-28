package edu.java.controller;

import edu.java.dto.bot.response.ApiErrorResponse;
import java.util.Arrays;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BotExceptionController {
    @ExceptionHandler(value = {IllegalArgumentException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ApiErrorResponse> returnException(Exception e) {
        String description = e instanceof IllegalArgumentException ? "Exception occurred due to your args"
            :
            "Exception occurred due to your body style";
        return ResponseEntity.badRequest().body(new ApiErrorResponse(
            description,
            "1",
            e.getClass().getName(),
            e.getMessage(),
            Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).toList()
        ));
    }
}
