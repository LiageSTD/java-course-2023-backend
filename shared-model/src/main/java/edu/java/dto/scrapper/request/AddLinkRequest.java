package edu.java.dto.scrapper.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AddLinkRequest {
    @JsonProperty("link")
    String link;
}
