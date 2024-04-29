package edu.java.domain.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Chat {
    @Id
    private Long id;

    @ManyToMany
    @JoinTable(name = "chat_link",
               joinColumns = @JoinColumn(name = "chat_id"),
               inverseJoinColumns = @JoinColumn(name = "link_id"))
    private Set<Link> links;
}
