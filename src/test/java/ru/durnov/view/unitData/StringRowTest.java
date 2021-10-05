package ru.durnov.view.unitData;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class StringRowTest {

    @Test
    void simpleNumberTest(){
        StringRow stringRow = new StringRow("1");
        List<Integer> list = stringRow.listNumbers();
        Assertions.assertEquals(list.size(), 1);
        Assertions.assertEquals(list.get(0), 1);
    }

    @Test
    void testSimpleDiapason(){
        StringRow stringRow = new StringRow("1-5");
        List<Integer> list = stringRow.listNumbers();
        Assertions.assertEquals(list.size(), 5);
        Assertions.assertEquals(list.get(4), 5);
    }

    @Test
    void testComplexExpression(){
        StringRow stringRow = new StringRow("1,2-4,6,7,12-16");
        List<Integer> list = stringRow.listNumbers();
        Assertions.assertEquals(list.size(), 11);
        Assertions.assertEquals(list.get(0), 1);
        Assertions.assertEquals(list.get(1), 2);
        Assertions.assertEquals(list.get(4), 6);
        Assertions.assertEquals(list.get(5), 7);
        Assertions.assertEquals(list.get(6), 12);
        Assertions.assertEquals(list.get(7), 13);
        Assertions.assertEquals(list.get(8), 14);
        Assertions.assertEquals(list.get(9), 15);
        Assertions.assertEquals(list.get(10), 16);

    }

}