package com.hanbaguni.project.global.utils;

import java.util.function.Consumer;

public class EntityUtils {
    public static <T> void updateIfNotNull(T value, Consumer<T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
    }
}
