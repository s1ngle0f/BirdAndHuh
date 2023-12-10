package com.mygdx.game.util;

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
}
