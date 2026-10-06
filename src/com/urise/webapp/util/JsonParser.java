package com.urise.webapp.util; // Оставьте ваш реальный пакет!

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.urise.webapp.model.Section;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;

public class JsonParser {
    // На версии 2.10.1 этот код сработает идеально
    private static final Gson GSON = new GsonBuilder()
            // Вызываем ваш адаптер по его точному имени!
            .registerTypeAdapter(LocalDate.class, new JsonLocalDateAdapter())
            // Адаптер секций вызываем без угловых скобок <>
            .registerTypeAdapter(Section.class, new JsonSectionAdapter())
            .create();

    public static <T> T read(Reader reader, Class<T> clazz) {
        return GSON.fromJson(reader, clazz);
    }

    public static <T> void write(T object, Writer writer) {
        GSON.toJson(object, writer);
    }
}

