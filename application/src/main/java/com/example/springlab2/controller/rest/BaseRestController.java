package com.example.springlab2.controller.rest;

import com.example.springlab2.common.page.PageLink;
import com.example.springlab2.common.page.SortOrder;
import com.example.springlab2.exception.SwoqeErrorCode;
import com.example.springlab2.exception.SwoqeException;
import com.example.springlab2.service.GenreService;
import com.example.springlab2.service.BookService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public abstract class BaseRestController {

    @Autowired
    protected GenreService genreService;

    @Autowired
    protected BookService bookService;

    void checkNotBlank(String name, String param) throws SwoqeException {
        if (StringUtils.isBlank(param)) {
            throw new SwoqeException("Parameter '" + name + "' can't be empty!", SwoqeErrorCode.BAD_REQUEST_PARAMS);
        }
    }

    void checkArrayParameter(String name, String[] params) throws SwoqeException {
        if (params == null || params.length == 0) {
            throw new SwoqeException("Parameter '" + name + "' can't be empty!", SwoqeErrorCode.BAD_REQUEST_PARAMS);
        } else {
            for (String param : params) {
                checkNotBlank(name, param);
            }
        }
    }

    PageLink createPageLink(int pageSize, int page, String textSearch, String sortProperty, String sortOrder) throws SwoqeException {
        if (!StringUtils.isEmpty(sortProperty)) {
            SortOrder.Direction direction = SortOrder.Direction.ASC;
            if (!StringUtils.isEmpty(sortOrder)) {
                try {
                    direction = SortOrder.Direction.valueOf(sortOrder.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new SwoqeException("Unsupported sort order '" + sortOrder + "'! Only 'ASC' or 'DESC' types are allowed.", SwoqeErrorCode.BAD_REQUEST_PARAMS);
                }
            }
            SortOrder sort = new SortOrder(sortProperty, direction);
            return new PageLink(pageSize, page, textSearch, sort);
        } else {
            return new PageLink(pageSize, page, textSearch);
        }
    }

    UUID toUUID(String id) {
        return UUID.fromString(id);
    }

}
