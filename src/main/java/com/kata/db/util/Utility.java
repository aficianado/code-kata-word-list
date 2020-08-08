package com.kata.db.util;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Set;

public class Utility {
    private static Gson gson = new Gson();

    private Utility() {
    }

    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    public static boolean isValid(String word, int wordCombinationSize) {
        if (word != null && !word.isEmpty() && word.length() <= wordCombinationSize) {
            return true;
        }
        return false;
    }

    public static boolean isPresent(Set<String> words, String prefix, String suffix) {
        return words.contains(prefix) && words.contains(suffix);
    }

    public static String getJsonString(List<?> data) {
        return gson.toJson(data);
    }

    public static boolean createFile(String fileName, String contents) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.write(contents.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean createFile(List<?> data, String fileName) {
        return createFile(fileName, getJsonString(data));
    }

}
