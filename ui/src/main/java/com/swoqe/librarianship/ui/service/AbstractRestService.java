package com.swoqe.librarianship.ui.service;

import com.swoqe.librarianship.common.page.PageData;
import com.swoqe.librarianship.dto.UUIDBased;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public abstract class AbstractRestService<T extends UUIDBased> implements RestService<T> {
    private final WebClient webClient;

    public final Mono<T> getByIdAsync(final String id) {
        return webClient
                .get()
                .uri(getUri() + "/" + id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<T>() {});
    }

    public final T getByIdSync(final String id) {
        return webClient
                .get()
                .uri(getUri() + "/" + id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<T>() {})
                .block();
    }

    public final PageData<T> getPageSync(int pageSize, int page) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(getUri())
                        .queryParam("pageSize", pageSize)
                        .queryParam("page", page)
                        .build()
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<PageData<T>>() {})
                .block();
    }

    public final Mono<PageData<T>> getPageAsync() {
        return webClient
                .get()
                .uri(getUri())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }

    public abstract String getUri();
}
