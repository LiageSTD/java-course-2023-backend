package edu.java.client.bot;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

public class BotClientImpl {
    private BotClientImpl() {

    }

    public static WebClient makeClient(String url) {
        return WebClient.builder().baseUrl(url).build();
    }

    @Bean
    public static BotClient scrapperClient(String githubUrl) {
        HttpServiceProxyFactory clientFactory =
            HttpServiceProxyFactory.builderFor(WebClientAdapter.create(makeClient(githubUrl))).build();
        return clientFactory.createClient(BotClient.class);
    }
}
