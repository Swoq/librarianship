package com.swoqe.librarianship.common.page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

public class DaoUtil {

    public static Pageable toPageable(PageLink pageLink, Map<String,String> columnMap) {
        return PageRequest.of(pageLink.getPage(), pageLink.getPageSize(), toSort(pageLink.getSortOrder(), columnMap));
    }

    public static Sort toSort(SortOrder sortOrder, Map<String,String> columnMap) {
        if (sortOrder == null) {
            return Sort.unsorted();
        } else {
            String property = sortOrder.getProperty();
            if (columnMap.containsKey(property)) {
                property = columnMap.get(property);
            }
            return Sort.by(Sort.Direction.fromString(sortOrder.getDirection().name()), property);
        }
    }

}
