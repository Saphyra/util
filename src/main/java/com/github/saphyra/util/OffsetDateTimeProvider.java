package com.github.saphyra.util;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class OffsetDateTimeProvider {
    public OffsetDateTime getCurrentDate() {
        return OffsetDateTime.now(ZoneOffset.UTC);
    }
}
