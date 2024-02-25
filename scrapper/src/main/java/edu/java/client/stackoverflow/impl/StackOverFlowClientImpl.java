package edu.java.client.stackoverflow.impl;

import edu.java.client.stackoverflow.StackOverFlowClient;
import edu.java.client.stackoverflow.StackOverFlowService;
import edu.java.client.stackoverflow.response.QuestionEventResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StackOverFlowClientImpl implements StackOverFlowClient {
    private final StackOverFlowService stackOverFlowService;

    @Override
    public List<QuestionEventResponse> getQuestions(String qId) {
        return stackOverFlowService.getQuestions(qId);
    }
}
