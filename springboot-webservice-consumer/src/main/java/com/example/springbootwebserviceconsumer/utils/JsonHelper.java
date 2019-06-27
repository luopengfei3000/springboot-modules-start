package com.example.springbootwebserviceconsumer.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.text.DateFormat;

public class JsonHelper extends ObjectMapper implements Serializable {
    private static final long serialVersionUID = 1L;

    private JsonHelper() {
    }

    public static JsonHelper getInstance() {
        _class.instance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return _class.instance;
    }

    public static JsonHelper getBaseInstance() {
        _class.baseInstance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return _class.baseInstance;
    }

    public String writeValueAsString(Object value) {
        try {
            return super.writeValueAsString(value);
        } catch (Exception var3) {
            var3.printStackTrace();
            throw new RuntimeException(var3);
        }
    }

    public <T> T readValue(String content, TypeReference valueTypeRef) {
        try {
            return super.readValue(content, valueTypeRef);
        } catch (Exception var4) {
            var4.printStackTrace();
            throw new RuntimeException(var4);
        }
    }

    public <T> T readValue(String content, Class<T> calss) {
        try {
            return super.readValue(content, calss);
        } catch (Exception var4) {
            var4.printStackTrace();
            throw new RuntimeException(var4);
        }
    }

    public <T> T readValue(String content, DateFormat dateFormat, TypeReference valueTypeRef) {
        try {
            super.setDateFormat(dateFormat);
            return super.readValue(content, valueTypeRef);
        } catch (Exception var5) {
            var5.printStackTrace();
            throw new RuntimeException(var5);
        }
    }

    public <T> T transformDto(Object object, TypeReference<?> typeReference) {
        String json = this.writeValueAsString(object);
        return this.readValue(json, typeReference);
    }

    private static class _class {
        private static JsonHelper instance = new JsonHelper();
        private static JsonHelper baseInstance = new JsonHelper();

        private _class() {
        }
    }
}