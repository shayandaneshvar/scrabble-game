package com.scrabble.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.*;

public enum WordUtil {
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

    public Set<List<Character>> generatePlayerPieces(int players) {
        TreeMap<Character, Integer> chars = new TreeMap<>();
        HashMap<Integer, List<Character>> result = new HashMap<>();
        for (int i = 0; i < players; i++) {
            result.put(i, new ArrayList<>());
        }
        try (Scanner scanner = new Scanner(new File(
                "scrabble-server/src/main/resources/char_dist.txt"))) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                Integer number = Integer.parseInt(str.substring(2).trim());
                chars.put(str.toCharArray()[0], number);
            }
            SecureRandom secureRandom = new SecureRandom();
            for (int i = 0; i < players * 15; i++) {
                double prob = secureRandom.nextDouble() * 98;
                int previous = -1;
                for (var character : chars.keySet()) {
                    if (previous <= prob && prob <= chars.get(character)) {
                        result.get(i % players).add(character);
                        break;
                    }
                    previous = chars.get(character);
                }
            }
            return new HashSet<>(result.values());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
