package com.urise.webapp.util;



import com.google.gson.*;
import java.lang.reflect.Type;

public class JsonSectionAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE = "INSTANCE";

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(CLASSNAME, src.getClass().getName());
        jsonObject.add(INSTANCE, context.serialize(src));
        return jsonObject;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = prim.getAsString();
        try {
            Class<?> clazz = Class.forName(className);
            return context.deserialize(jsonObject.get(INSTANCE), clazz);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }
}

