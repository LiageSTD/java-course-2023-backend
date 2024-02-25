package edu.java.client.stackoverflow;

import edu.java.client.stackoverflow.response.QuestionEventResponse;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/questions")
public interface StackOverFlowService {
    @GetExchange("/{id}")
    List<QuestionEventResponse> getQuestions(
        @PathVariable("id") String qId
    );
}
