package com.hdsaison.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
//    @Value("${baseurl}")
//    private String defaultBaseUrl;
//    @Value("${http.baseurl}")
//    private String host;

    @Bean
    public WebClient webClient() {
        var client = WebClient.builder();
//        System.out.println("autoload => " + this.host);
        client.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        if (this.defaultBaseUrl != null && !this.defaultBaseUrl.trim().equals("")) {
//            client.baseUrl(this.defaultBaseUrl);
//        }
        return client.build();
    }
}
