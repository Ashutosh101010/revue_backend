package org.aurd.user.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class LongAdapter  extends TypeAdapter<Long> {
    @Override
    public void write(JsonWriter jsonWriter, Long l) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("$numberLong");
        jsonWriter.value(l.toString());
        jsonWriter.endObject();
    }


    @Override
    public Long read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        Long id=jsonReader.nextLong();
        jsonReader.endObject();
        return id;
    }
}
