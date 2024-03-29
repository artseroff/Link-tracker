package edu.java.scrapper.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

@SpringBootTest
public class LiquibaseIntegrationTest extends IntegrationTest {

    private final JdbcClient jdbcClient;

    @Autowired
    public LiquibaseIntegrationTest(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Test
    public void addChatTest() {
        // Assert
        int expectedChatId = 12345;

        // Act
        jdbcClient.sql("INSERT INTO chats (id) VALUES (?)")
            .param(expectedChatId)
            .update();

        Long actualChatId = jdbcClient
            .sql("SELECT id FROM chats WHERE id=?")
            .param(expectedChatId)
            .query(Long.class)
            .single();

        // Arrange
        Assertions.assertEquals(expectedChatId, actualChatId);
        jdbcClient
            .sql("truncate table chats restart identity cascade")
            .update();
    }
}
