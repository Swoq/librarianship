package com.swoqe.librarianship.ui.service;

import com.swoqe.librarianship.common.page.PageData;
import reactor.core.publisher.Mono;

public interface RestService<T> {
    Mono<T> getByIdAsync(final String id);
    T getByIdSync(final String id);
    PageData<T> getPageSync(int pageSize, int page);
    Mono<PageData<T>> getPageAsync();
    String getUri();
}
