package com.scrabble.client.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CellContentTest {

    @Test
    void toCharacter() {
        //given
        CellContent cellContent = CellContent.EMPTY;
        CellContent cellContentA = CellContent.A;
        //when
        Character character = cellContent.toCharacter();
        Character characterA = cellContentA.toCharacter();
        //then
        Assertions.assertEquals(' ', character);
        Assertions.assertEquals('A', characterA);
    }
}
