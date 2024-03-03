package edu.java.client.github;

import com.github.tomakehurst.wiremock.WireMockServer;
import edu.java.client.github.dto.EventsResponse;
import edu.java.client.github.dto.PushEvent;
import edu.java.configuration.ClientConf;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

class GithubClientTest {
    private GithubClient client;
    private WireMockServer server;

    @BeforeEach
    void init() {
        server = new WireMockServer();
        server.start();
        configureFor("localhost", 8080);
        client = ClientConf.githubClient(server.baseUrl());
    }

    @AfterEach
    void close() {
        server.stop();
    }

    @Test
    public void testRepoCheck() {
        String repo = "Lol_a_repo";
        String user = "Focus_Glo";
        stubFor(get("/repos/%s/%s/events".formatted(user, repo))
            .willReturn(aResponse()
                .withStatus(200)
                .withBody("[\n" +
                    "  {\n" +
                    "    \"id\": \"22249084964\",\n" +
                    "    \"type\": \"PushEvent\",\n" +
                    "    \"actor\": {\n" +
                    "      \"id\": 583231,\n" +
                    "      \"login\": \"octocat\",\n" +
                    "      \"display_login\": \"octocat\",\n" +
                    "      \"gravatar_id\": \"\",\n" +
                    "      \"url\": \"https://api.github.com/users/octocat\",\n" +
                    "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/583231?v=4\"\n" +
                    "    },\n" +
                    "    \"repo\": {\n" +
                    "      \"id\": 1296269,\n" +
                    "      \"name\": \"octocat/Hello-World\",\n" +
                    "      \"url\": \"https://api.github.com/repos/octocat/Hello-World\"\n" +
                    "    },\n" +
                    "    \"payload\": {\n" +
                    "      \"push_id\": 10115855396,\n" +
                    "      \"size\": 1,\n" +
                    "      \"distinct_size\": 1,\n" +
                    "      \"ref\": \"refs/heads/master\",\n" +
                    "      \"head\": \"7a8f3ac80e2ad2f6842cb86f576d4bfe2c03e300\",\n" +
                    "      \"before\": \"883efe034920928c47fe18598c01249d1a9fdabd\",\n" +
                    "      \"commits\": [\n" +
                    "        {\n" +
                    "          \"sha\": \"7a8f3ac80e2ad2f6842cb86f576d4bfe2c03e300\",\n" +
                    "          \"author\": {\n" +
                    "            \"email\": \"octocat@github.com\",\n" +
                    "            \"name\": \"Monalisa Octocat\"\n" +
                    "          },\n" +
                    "          \"message\": \"commit\",\n" +
                    "          \"distinct\": true,\n" +
                    "          \"url\": \"https://api.github.com/repos/octocat/Hello-World/commits/7a8f3ac80e2ad2f6842cb86f576d4bfe2c03e300\"\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    \"public\": true,\n" +
                    "    \"created_at\": \"2022-06-09T12:47:28Z\"\n" +
                    "  }" +
                    "]\n")
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));
        List<EventsResponse> responses = client.getEvents(repo, user);
        EventsResponse event = responses.get(0);
        Assertions.assertTrue(event instanceof PushEvent);
        Assertions.assertEquals(OffsetDateTime.parse("2022-06-09T12:47:28Z"), event.getCreatedAt());
    }
}
