package com.expertsoft.web.util;

import com.expertsoft.web.form.UpdateCartForm;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public final class FormUtil {

    private FormUtil() {}

    public static Map<Long, Integer> buildItemsMap(UpdateCartForm form) {
        return form.getItems().entrySet()
                .stream()
                .collect(toMap(
                        e -> Long.valueOf(e.getKey()),
                        e -> e.getValue().getQuantity()));
    }

}
