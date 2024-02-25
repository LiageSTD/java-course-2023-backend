package edu.java.client.github.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommitResponse extends EventsResponse {
    public Response response;

    @Getter
    public static class Response implements Serializable {
        @JsonProperty("comment")
        String body;

    }
}
