package edu.java.scrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PostgresTest extends IntegrationTest {
    @Test @SneakyThrows void checkLinksTable() {
        Connection tunnel = POSTGRES.createConnection("");
        PreparedStatement request = tunnel.prepareStatement("SELECT * FROM links");
        ResultSet response = request.executeQuery();
        Assertions.assertEquals("id", response.getMetaData().getColumnName(1));
        Assertions.assertEquals("url", response.getMetaData().getColumnName(2));
        Assertions.assertEquals("updated_at", response.getMetaData().getColumnName(3));
        Assertions.assertEquals("unable_to_update", response.getMetaData().getColumnName(4));
    }

    @Test @SneakyThrows void checkUsersTable() {
        Connection tunnel = POSTGRES.createConnection("");
        PreparedStatement request = tunnel.prepareStatement("SELECT * FROM users");
        ResultSet response = request.executeQuery();
        Assertions.assertEquals("id", response.getMetaData().getColumnName(1));
    }

    @Test @SneakyThrows void checkUsersLinksTable() {
        Connection tunnel = POSTGRES.createConnection("");
        PreparedStatement request = tunnel.prepareStatement("SELECT * FROM users_links");
        ResultSet response = request.executeQuery();
        Assertions.assertEquals("chat_id", response.getMetaData().getColumnName(1));
        Assertions.assertEquals("link_id", response.getMetaData().getColumnName(2));
    }
}
