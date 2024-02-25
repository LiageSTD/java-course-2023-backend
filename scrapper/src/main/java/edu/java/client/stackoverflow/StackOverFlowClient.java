package edu.java.client.stackoverflow;

import edu.java.client.stackoverflow.response.QuestionEventResponse;
import java.util.List;

public interface StackOverFlowClient {
    List<QuestionEventResponse> getQuestions(String qId);
}
