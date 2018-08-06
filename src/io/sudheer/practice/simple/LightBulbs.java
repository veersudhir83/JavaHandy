package io.sudheer.practice.simple;

import java.util.Arrays;

/**
 * Given a linear arrangement of bulbs, a bulb will be ON only when all bulbs prior to it are ON
 * Program to identify how many times all bulbs that are ON will light up.
 */
class LightBulbs {

    public static void main (String[] args) {
        int[] lights = {3, 2, 1, 5, 4};
        System.out.println("Count = " + solution(lights));
    }

    public static int solution(int[] A) {
        int count = 0;

        try {
            boolean[] turnedOnArray = new boolean[A.length];
            Arrays.fill(turnedOnArray, false);
            for (int i = 0; i < A.length; i++) {
                turnedOnArray[A[i]-1] = true;
                if (checkCounter(turnedOnArray, i))
                    count++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Check if all lights are on for given index
     * @param turnedOnArray
     * @param i
     * @return
     */
    private static boolean checkCounter(boolean[] turnedOnArray, int i) {
        boolean allLightsOn = true;
        for (int j = 0; j <= i ; j++) {
            if (!turnedOnArray[j]) {
                allLightsOn = false;
                break;
            }
        }
        return allLightsOn;
    }
}