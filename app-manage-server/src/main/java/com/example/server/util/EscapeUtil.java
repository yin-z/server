package com.example.server.util;

public class EscapeUtil {

    public static String escapeChar(String content){
        if (content != null && !content.trim().isEmpty()) {
            content = content.replaceAll("\\\\", "\\\\\\\\");
            content = content.replaceAll("_", "\\\\_");
            content = content.replaceAll("%", "\\\\%");
        }
        return content;
    }
}
