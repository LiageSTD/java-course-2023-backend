package edu.java.dto.scrapper.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.net.URI;

@Getter
@AllArgsConstructor
public class LinkResponse {
    int id;
    URI uri;
}
