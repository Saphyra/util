package com.github.saphyra.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ObjectMapperWrapper {
    private final ObjectMapper objectMapper;

    public <T> T readValue(InputStream in, TypeReference<T> type) {
        try {
            return objectMapper.readValue(in, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T readValue(String source, Class<T> type) {
        try {
            return objectMapper.readValue(source, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T readValue(String source, TypeReference<T> type) {
        try {
            return objectMapper.readValue(source, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> readArrayValue(String source, Class<T[]> type) {
        try {
            return new ArrayList<>(Arrays.asList(objectMapper.readValue(source, type)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String writeValueAsString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
