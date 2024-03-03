package edu.java.client.stackoverflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class QuestionEventResponse {
    @JsonProperty("items")
    List<Replies> repliesList;

    @Getter
    public static class Replies implements Serializable {
        @JsonProperty("answer_count")
        String answerCount;
        @JsonProperty("last_activity_date")
        OffsetDateTime lastActivityDate;
    }

}
