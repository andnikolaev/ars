package ru.rsreu.ars.core;

import java.util.HashMap;
import java.util.Map;

public class TemplateIdentifier {
    Map<String, String> templateIdentifierValue = new HashMap<>();

    public TemplateIdentifier() {
    }

    public static boolean checkKeyTagOnUnresolved(String keyTag) {
        if (!"code".equals(keyTag.toLowerCase()) && !"checkstyle".equals(keyTag.toLowerCase())) {
            return true;
        }
        return false;
    }

    public void addValue(String key, String value) {
        templateIdentifierValue.put(key, value);
    }

    public String getValue(Object key) {
        return templateIdentifierValue.get(key);
    }
}
