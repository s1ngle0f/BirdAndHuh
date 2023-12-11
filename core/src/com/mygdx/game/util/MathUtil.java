package com.mygdx.game.util;

import com.badlogic.gdx.math.Vector2;

public class MathUtil {
    public static void main(String[] args) {
        Vector2 start = new Vector2(5, 5);
        Vector2 end = new Vector2(1, 1);
        System.out.println(start.sub(end));
        System.out.println(start);
    }

    public static double calculateDistance(Vector2 startPosition, Vector2 endPosition) {
        return Math.sqrt(Math.pow((endPosition.x - startPosition.x), 2) +
                Math.pow((endPosition.y - startPosition.y), 2));
    }
}
