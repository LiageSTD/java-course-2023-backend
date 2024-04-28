package edu.java.dto.scrapper.response;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListLinksResponse {
    Collection<LinkResponse> linkResponse;
    int size;
}
