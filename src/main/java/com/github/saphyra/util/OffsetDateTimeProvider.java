package com.github.saphyra.util;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class OffsetDateTimeProvider {
    public OffsetDateTime getCurrentDate() {
        return OffsetDateTime.now(ZoneOffset.UTC);
    }
}
