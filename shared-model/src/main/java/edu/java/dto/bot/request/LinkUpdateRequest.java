package edu.java.dto.bot.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LinkUpdateRequest {
    @JsonProperty("id")
    long id;
    @JsonProperty("url")
    String url;
    @JsonProperty("description")
    String description;
    @JsonProperty("tgChatIds")
    long[] tgChatIds;
}
