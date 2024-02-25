package edu.java.configuration.apiConfs;

import org.springframework.http.MediaType;

public interface GithubApiConf {
    String API_BASE_URL = "https://api.github.com/";
    String API_VERSION_SPEC = "application/vnd.github.v3+json";
    String JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;
}
