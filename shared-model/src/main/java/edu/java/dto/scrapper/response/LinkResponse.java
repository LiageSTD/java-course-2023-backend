package edu.java.dto.scrapper.response;

import java.net.URI;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor public class LinkResponse {
    long id;
    URI url;
}
