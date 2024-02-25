package edu.java.links;

import java.time.OffsetDateTime;

public record UpdatedGithubLink(String username, String repo, OffsetDateTime newDate, String comment) {
}
