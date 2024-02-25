package edu.java.scrapper.client.github;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import edu.java.client.github.GithubClient;
import edu.java.client.github.impl.GitHubClientImpl;
import edu.java.client.github.response.EventsResponse;
import edu.java.client.github.response.PushEvent;
import edu.java.configuration.ClientConf;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@SpringBootTest
class ClientIntegrationTest {
    public WireMockServer github = new WireMockServer();
    //    @Autowired
//    GithubClient githubClient;
    @Autowired ClientConf clientConf;

    @Test
    void githubTest() throws IOException {
        github.start();
        GithubClient githubClient = new GitHubClientImpl(clientConf.githubService(github.baseUrl()));
        File file = ResourceUtils.getFile("classpath:GithubResponse");
        String expectedJson = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        WireMock.stubFor(WireMock.any(WireMock.urlEqualTo("/repos/Foo/Bar/events")).willReturn(aResponse()
            .withStatus(200)
            .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
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
                "  },\n" +
                "  {\n" +
                "    \"id\": \"22237752260\",\n" +
                "    \"type\": \"WatchEvent\",\n" +
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
                "      \"action\": \"started\"\n" +
                "    },\n" +
                "    \"public\": true,\n" +
                "    \"created_at\": \"2022-06-08T23:29:25Z\"\n" +
                "  }\n" +
                "]\n")));
        List<EventsResponse> reList = githubClient.fetchEvents("Foo", "Bar");
        PushEvent curr = (PushEvent) reList.get(0);
        Assertions.assertEquals(curr.getCreatedAt(), OffsetDateTime.parse("2022-06-09T12:47:28Z"));
        github.stop();
    }

}
