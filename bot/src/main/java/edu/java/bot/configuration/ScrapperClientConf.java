package edu.java.bot.configuration;

import edu.java.bot.client.scrapper.ScrapperClient;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ScrapperClientConf {
    public static <S> S createClient(Class<S> clientClass, Map<String, String> headers, String baseUrl) {
        WebClient.Builder webclientBuilder = WebClient.builder().baseUrl(baseUrl);
        headers.forEach(webclientBuilder::defaultHeader);
        WebClient webClient = webclientBuilder.build();

        HttpServiceProxyFactory clientFactory =
            HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();
        return clientFactory.createClient(clientClass);
    }

    public ScrapperClient scrapperClient(String botUrl) {
        return createClient(ScrapperClient.class, Map.of(
            "Content-Type", edu.java.bot.configuration.apiConf.ScrapperClientConf.CONTENT_TYPE,
            "Accept", edu.java.bot.configuration.apiConf.ScrapperClientConf.ACCEPT
        ), botUrl);
    }

    @Bean
    public ScrapperClient scrapperClient() {
        return scrapperClient(edu.java.bot.configuration.apiConf.ScrapperClientConf.API_URL);
    }
}
