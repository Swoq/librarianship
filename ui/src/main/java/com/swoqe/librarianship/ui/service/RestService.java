package com.swoqe.librarianship.ui.service;

import com.swoqe.librarianship.common.page.PageData;
import org.springframework.core.ParameterizedTypeReference;
import reactor.core.publisher.Mono;

public interface RestService<T> {
    Mono<T> getByIdAsync(ParameterizedTypeReference<T> typeReference, final String id);
    T getByIdSync(ParameterizedTypeReference<T> typeReference, final String id);
    PageData<T> getPageSync(ParameterizedTypeReference<PageData<T>> typeReference, int pageSize, int page);
    Mono<PageData<T>> getPageAsync(ParameterizedTypeReference<PageData<T>> typeReference);
    String getUri();
}
