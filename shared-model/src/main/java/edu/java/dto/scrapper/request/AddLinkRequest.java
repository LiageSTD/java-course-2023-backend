package edu.java.dto.scrapper.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddLinkRequest implements Serializable {
    @JsonProperty("link")
    private String link;
    public void setLink(String link) {
        this.link = link;
    }
}
