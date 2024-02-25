package edu.java.configuration;

import edu.java.client.github.GithubClient;
import edu.java.client.github.GithubService;
import edu.java.client.github.impl.GitHubClientImpl;
import edu.java.client.stackoverflow.StackOverFlowClient;
import edu.java.client.stackoverflow.StackOverFlowService;
import edu.java.client.stackoverflow.impl.StackOverFlowClientImpl;
import edu.java.configuration.apiConfs.GithubApiConf;
import edu.java.configuration.apiConfs.StackOverFlowApiConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConf {
    public <T> T buildClient(Class<T> clientClass, String apiUrl, String contentType, String apiVersion) {
        WebClient webClient = WebClient.builder()
            .baseUrl(apiUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, contentType)
            .defaultHeader(HttpHeaders.ACCEPT, apiVersion)
            .build();
        HttpServiceProxyFactory proxyFactory =
            HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();
        return proxyFactory.createClient(clientClass);
    }

    private GithubService githubService() {
        return buildClient(
            GithubService.class,
            GithubApiConf.API_BASE_URL,
            GithubApiConf.JSON_CONTENT_TYPE,
            GithubApiConf.API_VERSION_SPEC
        );
    }

    public GithubService githubService(String u) {
        return buildClient(
            GithubService.class,
            u,
            GithubApiConf.JSON_CONTENT_TYPE,
            GithubApiConf.API_VERSION_SPEC
        );
    }

    private StackOverFlowService stackOverFlowService() {
        return buildClient(
            StackOverFlowService.class,
            StackOverFlowApiConf.API_BASE_URL,
            StackOverFlowApiConf.JSON_CONTENT_TYPE,
            StackOverFlowApiConf.API_VERSION_SPEC
        );
    }

    @Bean
    StackOverFlowClient stackOverFlowClient() {
        return new StackOverFlowClientImpl(stackOverFlowService());
    }

    @Bean
    GithubClient githubClient() {
        return new GitHubClientImpl(githubService());
    }
}
