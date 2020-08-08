package com.kata.db.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class FileReaderUtility {
    private static final Logger LOG = Logger.getLogger(FileReaderUtility.class.getName());

    private FileReaderUtility() {
    }

    public static Set<String> getWords(String fileName) throws Exception {
        long now = System.currentTimeMillis();
        URL resource = FileReaderUtility.class.getClassLoader().getResource(fileName);
        Set<String> wordsSet = readAllLines(Paths.get(resource.toURI()), StandardCharsets.UTF_8);
        System.out.println(String.format("List of words (#{%s}) loaded from file. Time Taken::{%d}",
                wordsSet.size(), (System.currentTimeMillis() - now)));
        return wordsSet;
    }

    public static Set<String> getWords(String fileName, int wordSize) throws Exception {
        long now = System.currentTimeMillis();
        URL resource = FileReaderUtility.class.getClassLoader().getResource(fileName);
        Set<String> wordsSet = readAllLines(Paths.get(resource.toURI()), StandardCharsets.UTF_8, wordSize);
        System.out.println(String.format("List of words (#{%s}) loaded from file. Time Taken::{%d}",
                wordsSet.size(), (System.currentTimeMillis() - now)));
        return wordsSet;
    }

    public static Set<String> readAllLines(Path path, Charset cs) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path, cs)) {
            Set<String> result = new HashSet<>();
            for (; ; ) {
                String line = reader.readLine();
                if (line != null && line.isEmpty()) {
                    continue;
                }
                if (line == null) {
                    break;
                }
                result.add(line);
            }
            return result;
        }
    }

    public static Set<String> readAllLines(Path path, Charset cs, int size) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path, cs)) {
            Set<String> result = new HashSet<>();
            for (; ; ) {
                String line = reader.readLine();
                if (line != null && line.isEmpty()) {
                    continue;
                }
                if (line == null) {
                    break;
                }
                if(line.length() <= size) {
                    result.add(line);
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        try {
            FileReaderUtility.getWords("wordlist.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
