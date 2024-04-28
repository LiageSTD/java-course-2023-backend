package edu.java.dto.model;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Link {
    Long id = -1L;
    String url;
    OffsetDateTime updatedAt;
    boolean unableToUpdate;

}
