package com.example.sanchuang.util;

public class CamelCaseUtil {
    public static String parseUnderlineToCamel(String string) {
        return parseStringToCamel(string.replaceAll("_", ""));
    }

    public static String parseStringToCamel(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static String parseCamelToUnderline(String string) {
        if (string == null || string.isEmpty()) {
            return string;
        }

        StringBuilder underlineString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char currentChar = string.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                underlineString.append("_");
            }
            underlineString.append(Character.toLowerCase(currentChar));
        }
        return underlineString.toString();
    }
}
