package com.ymeng.monotonicStack;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{4,3,5,4,3,3,6,7};
        int w = 3;
        Q1_SildingWindowMaxArray q1 = new Q1_SildingWindowMaxArray();
        int[] res_q1 = q1.getMaxWindow(arr, w);
        System.out.println("Input array: " + Arrays.toString(arr));
        System.out.println("Window size: " + w);
        System.out.println("Result: " + Arrays.toString(res_q1));
    }
}
