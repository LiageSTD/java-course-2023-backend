package edu.java.client.stackoverflow.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuestionEventResponse {
    @JsonProperty("answer_count")
    String answerCount;
    @JsonProperty("last_activity_date")
    OffsetDateTime lastActivityDate;

}
