package edu.java.service;

import edu.java.links.UpdatedGithubLink;
import edu.java.model.LinkIdentity;
import edu.java.model.links.GithubLink;
import java.util.List;

public interface Service {
    Enum<LinkIdentity> identity();

    List<UpdatedGithubLink> updateLinksInfo(List<GithubLink> links);

    default boolean supports(Enum<LinkIdentity> linkIdentity) {
        return identity() == linkIdentity;
    }

}
