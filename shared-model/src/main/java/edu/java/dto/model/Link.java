package edu.java.dto.model;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    Long id = -1L;
    String url;
    OffsetDateTime updatedAt;
    boolean unableToUpdate;

}
