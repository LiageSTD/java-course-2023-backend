package edu.java.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.OffsetDateTime;
@Getter
@AllArgsConstructor
public class Link {
    Long id = -1L;
    String url;
    OffsetDateTime updatedAt;
    boolean unableToUpdate;

}
