//package edu.java.client.github.dto;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonSubTypes;
//import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import java.io.Serializable;
//import java.time.OffsetDateTime;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = Void.class)
//@JsonSubTypes({
//    @JsonSubTypes.Type(value = CommitResponse.class, name = "CommitCommentEvent"),
//    @JsonSubTypes.Type(value = CreateEventResponse.class, name = "CreateEvent"),
//    @JsonSubTypes.Type(value = CommitResponse.class, name = "IssuesCommentEvent"),
//    @JsonSubTypes.Type(value = PushEvent.class, name = "PushEvent"),
//})
//@Getter
//@Setter
//public abstract class EventsResponse implements Serializable {
//    @NotNull
//    @JsonProperty("id")
//    String id;
//    @NotNull
//    @JsonProperty("type")
//    String eventType;
//    @NotNull
//    @JsonProperty("created_at")
//    OffsetDateTime createdAt;
//}
//
//

package edu.java.client.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.Getter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
              include = JsonTypeInfo.As.EXISTING_PROPERTY,
              property = "type",
              defaultImpl = Void.class)
@JsonSubTypes({
    @JsonSubTypes.Type(value = PushEvent.class, name = "PushEvent"),
    @JsonSubTypes.Type(value = EventsResponse.PullRequest.class, name = "PullRequestEvent")
})
@Getter
public abstract class EventsResponse implements Serializable {

    @JsonProperty("id") protected String id;

    @JsonProperty("type") protected String eventType;

    @JsonProperty("created_at") protected OffsetDateTime createdAt;

    public static class Push extends EventsResponse {
        @JsonProperty("payload") private Payload payload;

        public class Payload {
            @JsonProperty("size") private int size;
        }

    }

    public static class PullRequest extends EventsResponse {
        @JsonProperty("payload") private Payload payload;

        public class Payload {
            @JsonProperty("action") private String action;
        }
    }

    public static class CreateEvent extends EventsResponse {
        @JsonProperty("payload") private Payload payload;

        public class Payload {
            @JsonProperty("ref") private String ref;
        }
    }

}
