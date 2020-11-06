package com.scrabble.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public enum DictionaryUtil {
    DICTIONARY;
    private Set<String> cachedWords = new HashSet<>();

    public boolean checkWord(String word) {
        if (cachedWords.contains(word)) {
            return true;
        }
        try (Scanner scanner = new Scanner(new File("scrabble-server/src/main/resources/dic.txt"))) {
            String previous = "a";
            while (scanner.hasNextLine() && previous.toCharArray()[0] <= word.toCharArray()[0]) {
                previous = scanner.nextLine();
                if (previous.equals(word)) {
                    cachedWords.add(previous);
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
