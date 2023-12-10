package com.mygdx.game.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtil {
    public static <T> void shiftListLeft(List<T> list) {
        if (!list.isEmpty()) {
            T firstElement = list.get(0);
            list.remove(0);
            list.add(firstElement);
        }
    }

    public static <T> void shiftListRight(List<T> list) {
        if (!list.isEmpty()) {
            T lastElement = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            list.add(0, lastElement);
        }
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        shiftListLeft(arr);
        System.out.println(arr);
        shiftListLeft(arr);
        System.out.println(arr);
    }
}
