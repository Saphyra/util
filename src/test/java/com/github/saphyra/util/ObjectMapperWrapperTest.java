package com.github.saphyra.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ObjectMapperWrapperTest {
    private static final String KEY = "key";
    private static final String VALUE = "value";

    private ObjectMapper objectMapper = new ObjectMapper();

    private ObjectMapperWrapper underTest = new ObjectMapperWrapper(objectMapper);

    @Test
    public void readValue_map() throws JsonProcessingException {
        //GIVEN
        Map<String, HelperDomain> source = new HelperMap();
        source.put(KEY, new HelperDomain(VALUE));
        String in = objectMapper.writeValueAsString(source);
        //WHEN
        Map<String, HelperDomain> result = underTest.readValue(in, HelperMap.class);
        //THEN
        assertThat(result).isEqualTo(source);
    }

    @Test
    public void readValue_list() throws JsonProcessingException {
        //GIVEN
        List<HelperDomain> source = new HelperList();
        source.add(new HelperDomain(VALUE));
        String in = objectMapper.writeValueAsString(source);
        //WHEN
        List<HelperDomain> result = underTest.readValue(in, HelperList.class);
        //THEN
        assertThat(result).isEqualTo(source);
    }

    @Test
    public void readValue_Object() throws JsonProcessingException {
        //GIVEN
        HelperDomain source = new HelperDomain(VALUE);
        String in = objectMapper.writeValueAsString(source);
        //WHEN
        HelperDomain result = underTest.readValue(in, HelperDomain.class);
        //THEN
        assertThat(result).isEqualTo(source);
    }

    @Test
    public void readValue_mapTypeRef() throws JsonProcessingException {
        //GIVEN
        Map<String, HelperDomain> source = new HashMap<>();
        source.put(KEY, new HelperDomain(VALUE));
        String in = objectMapper.writeValueAsString(source);

        TypeReference<Map<String, HelperDomain>> ref = new TypeReference<Map<String, HelperDomain>>() {
        };
        //WHEN
        Map<String, HelperDomain> result = underTest.readValue(in, ref);
        //THEN
        assertThat(result).isEqualTo(source);
    }

    @Test
    public void readArrayValue() throws JsonProcessingException {
        //GIVEN
        HelperDomain[] source = new HelperDomain[]{new HelperDomain(VALUE)};
        String in = objectMapper.writeValueAsString(source);
        //WHEN
        List<HelperDomain> result = underTest.readArrayValue(in, HelperDomain[].class);
        //THEN
        assertThat(result).containsExactly(source[0]);
        result.add(new HelperDomain(KEY));
        assertThat(result).hasSize(2);
    }

    @Test
    public void writeValueAsString() throws JsonProcessingException {
        //GIVEN
        HelperDomain in = new HelperDomain(VALUE);
        //WHEN
        String result = underTest.writeValueAsString(in);
        ///THEN
        assertThat(result).isEqualTo(objectMapper.writeValueAsString(in));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class HelperDomain {
        private String value;
    }

    private static class HelperMap extends HashMap<String, HelperDomain> {
    }

    private static class HelperList extends ArrayList<HelperDomain> {
    }
}