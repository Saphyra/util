package com.github.saphyra.util;

import java.util.UUID;

public class IdGenerator {
    public String generateRandomId() {
        return randomUUID().toString();
    }

    public UUID randomUUID() {
        return UUID.randomUUID();
    }
}
