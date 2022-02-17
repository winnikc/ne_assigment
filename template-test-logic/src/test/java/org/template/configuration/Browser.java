package org.template.configuration;

import java.util.Arrays;

public enum Browser {

    CHROME("chrome"),
    FIREFOX("firefox"),
    IE("ie"),
    SAFARI("safari");

    private final String name;

    Browser(String name) {
        this.name = name;
    }

    public static Browser fromText(String lookUpName) {
        return Arrays.stream(values())
                .filter(entry -> entry.name.equals(lookUpName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
