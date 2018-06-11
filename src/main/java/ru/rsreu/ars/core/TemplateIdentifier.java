package ru.rsreu.ars.core;

import java.util.HashMap;
import java.util.Map;

/***
 * Метки отчета
 */
public class TemplateIdentifier {
    /***
     * Значение меток отчета
     */
    Map<String, String> templateIdentifierValue = new HashMap<>();

    public TemplateIdentifier() {
    }

    /***
     * Проверка на допустимое название меток
     * @param keyTag Тег метки
     * @return Допустимое название или нет
     */
    public static boolean checkKeyTagOnUnresolved(String keyTag) {
        if (!"code".equals(keyTag.toLowerCase()) && !"checkstyle".equals(keyTag.toLowerCase())) {
            return true;
        }
        return false;
    }

    /***
     * Добавить значени метка-текст
     * @param key тег метки
     * @param value текст
     */
    public void addValue(String key, String value) {
        templateIdentifierValue.put(key, value);
    }

    /***
     * Получить текст метки
     * @param key тег метки
     * @return Текст метки
     */
    public String getValue(Object key) {
        return templateIdentifierValue.get(key);
    }
}
