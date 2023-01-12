package com.hdsaison.common.services;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface Http {
    public WebClient instance();
    public Mono<Object> requestAsync(HttpMethod method, String url);
    public Mono<Object> request(HttpMethod method, String url);
    public Mono<Object> request(HttpMethod method, String url, Map<String, ?> config);
    public Mono<Object> get(String url);
    public Mono<Object> get(String url, Map<String, ?> config);
}
