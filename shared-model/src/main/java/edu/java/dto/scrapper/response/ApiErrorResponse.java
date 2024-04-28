package edu.java.dto.scrapper.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter public class ApiErrorResponse {

    String description;
    String code;
    String exceptionName;
    String exceptionMessage;
    List<String> stackTrace;

}
