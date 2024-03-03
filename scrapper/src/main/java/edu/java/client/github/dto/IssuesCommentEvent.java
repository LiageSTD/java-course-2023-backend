package edu.java.client.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IssuesCommentEvent extends EventsResponse {
    @JsonProperty("payload")
    public Payload payload;

    @Getter
    public static class Payload implements Serializable {
        @JsonProperty("issue")
        private String issue;
    }
}
