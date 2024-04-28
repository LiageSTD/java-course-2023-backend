package edu.java.configuration;

import edu.java.client.bot.BotClient;
import edu.java.client.github.GithubClient;
import edu.java.client.stackoverflow.StackOverFlowClient;
import edu.java.configuration.apiConfs.BotClientConf;
import edu.java.configuration.apiConfs.GithubApiConf;
import edu.java.configuration.apiConfs.StackOverFlowApiConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@EnableScheduling
public class ClientConf {
    private static ApplicationConfig applicationConfig;
    @Autowired
    public ClientConf(ApplicationConfig applicationConfig) {
        ClientConf.applicationConfig = applicationConfig;
    }

    public static WebClient makeClient(String url, String jsonCT, String apiVer) {
        return WebClient.builder()
            .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(applicationConfig.webClientMaxInMemorySize()))
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

    public static BotClient botClient(String botUrl) {
        HttpServiceProxyFactory clientFactory =
            HttpServiceProxyFactory.builderFor(WebClientAdapter.create(makeClient(
                botUrl,
                BotClientConf.JSON_CONTENT_TYPE,
                BotClientConf.API_VERSION_SPEC
            ))).build();
        return clientFactory.createClient(BotClient.class);
    }

    @Bean
    StackOverFlowClient stackOverFlowClient() {
        return stackoverflowClient(StackOverFlowApiConf.API_BASE_URL);
    }

    @Bean
    GithubClient githubClient() {
        return githubClient(GithubApiConf.API_BASE_URL);
    }

    @Bean
    public BotClient botClient() {
        return botClient(BotClientConf.API_BASE_URL);
    }

}
