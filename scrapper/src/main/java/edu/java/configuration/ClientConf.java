package edu.java.configuration;

import edu.java.client.github.GithubClient;
import edu.java.client.stackoverflow.StackOverFlowClient;
import edu.java.configuration.apiConfs.GithubApiConf;
import edu.java.configuration.apiConfs.StackOverFlowApiConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@EnableScheduling
public class ClientConf {
    public static WebClient makeClient(String url, String jsonCT, String apiVer) {
        return WebClient.builder()
            .baseUrl(url)
            .defaultHeader("Content-Type", jsonCT)
            .defaultHeader("Accept", apiVer)
            .build();
    }

    public static GithubClient githubClient(String githubUrl) {
        HttpServiceProxyFactory clientFactory =
            HttpServiceProxyFactory.builderFor(WebClientAdapter.create(makeClient(
                githubUrl,
                GithubApiConf.JSON_CONTENT_TYPE,
                GithubApiConf.API_VERSION_SPEC
            ))).build();
        return clientFactory.createClient(GithubClient.class);
    }

    public static StackOverFlowClient stackoverflowClient(String stackoverflowUrl) {
        HttpServiceProxyFactory clientFactory =
            HttpServiceProxyFactory.builderFor(WebClientAdapter.create(makeClient(
                stackoverflowUrl,
                StackOverFlowApiConf.JSON_CONTENT_TYPE,
                StackOverFlowApiConf.API_VERSION_SPEC
            ))).build();
        return clientFactory.createClient(StackOverFlowClient.class);
    }

    @Bean
    StackOverFlowClient stackOverFlowClient() {
        return stackoverflowClient(StackOverFlowApiConf.API_BASE_URL);
    }

    @Bean
    GithubClient githubClient() {
        return githubClient(GithubApiConf.API_BASE_URL);
    }

}
