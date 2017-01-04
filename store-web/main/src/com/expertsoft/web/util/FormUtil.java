package com.expertsoft.web.util;

import java.util.Map;
import java.util.stream.Collectors;

import com.expertsoft.web.form.UpdateCartForm;

public final class FormUtil {

    private FormUtil() {}

    public static Map<Long, Integer> buildItemsMap(UpdateCartForm form) {
        return form.getItems().entrySet().stream().collect(Collectors.toMap(
                e -> Long.valueOf(e.getKey()),
                e -> e.getValue().getQuantity()));
    }

}
