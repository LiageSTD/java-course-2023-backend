package edu.java.client.stackoverflow;

import edu.java.client.stackoverflow.dto.QuestionEventResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface StackOverFlowClient {
    @GetExchange("questions/{id}")
    QuestionEventResponse getQuestions(
        @PathVariable("id") String qId
    );
}
