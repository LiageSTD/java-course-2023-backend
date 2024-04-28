package edu.java.dto.scrapper.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
