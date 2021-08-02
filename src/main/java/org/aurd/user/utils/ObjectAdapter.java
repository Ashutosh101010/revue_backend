package org.aurd.user.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ObjectAdapter extends TypeAdapter<String> {
    @Override
    public void write(JsonWriter jsonWriter, String s) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("$oid");
        jsonWriter.value(s);
        jsonWriter.endObject();
    }

    @Override
    public String read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();

        String id=jsonReader.nextString();
        jsonReader.endObject();
        return id;
    }
}
