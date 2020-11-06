package com.scrabble.server;

import org.junit.jupiter.api.Test;

public class MiscTests {
    @Test
    public void test() {
        WordUtil.DICTIONARY.generatePlayerPieces(4).stream().forEach(System.out::println);
    }
}
