package edu.java.client.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PushEvent extends EventsResponse {
    @JsonProperty("payload") private Payload payload;

    public class Payload {
        @JsonProperty("size") private int size;
    }

}
