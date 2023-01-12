package com.hdsaison.common.services.implementations;

import com.hdsaison.common.services.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class HttpImpl implements Http {
    @Autowired
    private WebClient webClient;

    @Override
    public WebClient instance() {
        return this.webClient;
    }

    @Override
    public Mono<Object> requestAsync(HttpMethod method, String url) {
        return webClient.method(method).uri(url).retrieve().bodyToMono(Object.class);
    }

    @Override
    public Mono<Object> request(HttpMethod method, String url) {
        //        return webClient.method(method).uri(url).retrieve().bodyToMono(Object.class);
        return webClient.method(method).uri(uriBuilder -> {
            return uriBuilder.build();
        }).retrieve().bodyToMono(Object.class);
    }

    @Override
    public Mono<Object> request(HttpMethod method, String url, Map<String, ?> config) {
        System.out.println("config => " + url);
        System.out.println("config" + config);
        Map<String, ?> paramsQuery = config.containsKey("paramsQuery")
                ? (Map<String, ?>) config.get("paramsQuery")
                : new HashMap<>();

        System.out.println("paramsQuery => " + paramsQuery);
        var client = webClient
                .method(method)
                .uri(url, (uriBuilder) -> {
                    // Query String
                    paramsQuery.forEach((key, val) -> uriBuilder.queryParam(key, val));
                    return uriBuilder.build();
                });
        // Headers
        if (config.containsKey("headers")) {
            Map<String, String> headers = (Map<String, String>) config.get("headers");
            client.headers(httpHeaders -> {
                headers.forEach((k, v) -> httpHeaders.set(k, v));
            });
        }
        if (method.equals(HttpMethod.POST)) {
            if (config.containsKey("params")) {
                var paramsBody = config.get("params");
                //                client.body(paramsBody);
            }
        }
        return client.retrieve().bodyToMono(Object.class);
    }

    @Override
    public Mono<Object> get(String url) {
        return this.request(HttpMethod.GET, url);
    }

    @Override
    public Mono<Object> get(String url, Map<String, ?> config) {
        return this.request(HttpMethod.GET, url, config);
    }
}
