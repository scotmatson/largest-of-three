package com.scotmatson.largestofthree;

import java.util.Random;

public class NumberHelper {

    private int maxNum;

    public NumberHelper(int[] threeNum) {
        this.maxNum = calculateMax(threeNum);
    }

    public int calculateMax(int[] threeNum) {
        int max = 0;
        for (int num : threeNum) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    static public int randomizer() {
        Random rand = new Random();
        return rand.nextInt(10);
    }

    public int getMaxNum() {
        return maxNum;
    }
}
