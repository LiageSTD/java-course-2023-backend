package edu.java.client.github.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = Void.class)
@JsonSubTypes({
    @JsonSubTypes.Type(value = CommitResponse.class, name = "CommitCommentEvent"),
    @JsonSubTypes.Type(value = CreateEventResponse.class, name = "CreateEvent"),
    @JsonSubTypes.Type(value = CommitResponse.class, name = "IssuesCommentEvent"),
    @JsonSubTypes.Type(value = PushEvent.class, name = "PushEvent"),
})
@RequiredArgsConstructor
@Getter
public abstract class EventsResponse implements Serializable {
    @JsonProperty("id")
    String id;
    @JsonProperty("type")
    String eventType;
    @JsonProperty("created_at")
    OffsetDateTime createdAt;
}


