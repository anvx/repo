package com.jtmog.alukyanov;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Parser {
    private String path;
    private String content;
    private String[] array;
    private Map<String, Integer> wordMatches = new LinkedHashMap<>();
    private char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public Parser(String path) {
        this.path = path;
    }

    public Parser() {
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String read() {
        try {
            content = new String(Files.readAllBytes(Paths.get(path)));
            if (content.isEmpty()) {
                System.out.println(" File is empty");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public void print() {
        correct();
        splitToArray();
        Arrays.sort(array);
        createMap();

        boolean atLeastOneWord = false;
        for (int i = 0; i < alphabet.length; i++) {
            for (Map.Entry<String, Integer> word : wordMatches.entrySet()) {
                if (word.getKey().startsWith(String.valueOf(alphabet[i]))) {
                    if (!atLeastOneWord) {
                        System.out.print(alphabet[i] + ": ");
                        System.out.print(word + " ");
                    } else {
                        System.out.print("   " + word);
                    }
                    System.out.println();
                    atLeastOneWord = true;
                }
            }
            atLeastOneWord = false;
        }
    }

    protected String correct() {
        content = content.replace(",", "");
        content = content.replace(".", "");
        content = content.toLowerCase();
        return content;
    }

    private String[] splitToArray() {
        array = content.split("\\s");
        return array;
    }

    private void createMap() {
        for (String word : array) {
            Integer oldCount = wordMatches.get(word);
            if (oldCount == null) {
                oldCount = 0;
            }
            wordMatches.put(word, oldCount + 1);
        }
    }
}
