package edu.java.dto.bot.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LinkUpdate {
    @JsonProperty("id")
    long id;
    @JsonProperty("url")
    String url;
    @JsonProperty("description")
    String description;
    @JsonProperty("tgChatIds")
    long[] tgChatIds;
}
