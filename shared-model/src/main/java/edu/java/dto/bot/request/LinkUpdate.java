package edu.java.dto.bot.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LinkUpdate {
    @JsonProperty("id")
    int id;
    @JsonProperty("url")
    String url;
    @JsonProperty("description")
    String description;
    @JsonProperty("tgChatIds")
    int[] tgChatIds;
}
