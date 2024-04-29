package edu.java.domain.jpa;

import edu.java.domain.jpa.model.Chat;
import edu.java.dto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaChatDao extends JpaRepository<Chat, Long> {

}
