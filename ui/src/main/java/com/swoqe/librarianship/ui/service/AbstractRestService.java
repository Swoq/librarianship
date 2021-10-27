package com.swoqe.librarianship.ui.service;

import com.swoqe.librarianship.common.page.PageData;
import com.swoqe.librarianship.dto.UUIDBased;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractRestService<T extends UUIDBased> implements RestService<T> {
    private WebClient webClient;

    public final Mono<T> getByIdAsync(ParameterizedTypeReference<T> typeReference, final String id) {
        return webClient
                .get()
                .uri(getUri() + "/find/" + id)
                .retrieve()
                .bodyToMono(typeReference);
    }

    public final T getByIdSync(ParameterizedTypeReference<T> typeReference, final String id) {
        return webClient
                .get()
                .uri(getUri() + "/find/" + id)
                .retrieve()
                .bodyToMono(typeReference)
                .block();
    }

    public final PageData<T> getPageSync(ParameterizedTypeReference<PageData<T>> typeReference, int pageSize, int page) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(getUri())
                        .queryParam("pageSize", pageSize)
                        .queryParam("page", page)
                        .build()
                )
                .retrieve()
                .bodyToMono(typeReference)
                .block();
    }

    public final Mono<PageData<T>> getPageAsync(ParameterizedTypeReference<PageData<T>> typeReference) {
        return webClient
                .get()
                .uri(getUri())
                .retrieve()
                .bodyToMono(typeReference);
    }

    public abstract String getUri();
}
