package com.ymeng.monotonicStack;

/**
 * 单调栈的应用
 * 给定一个positive int array，任何一个sub array，
 * 有值 SP =（sub累加和）* （sub min）
 * 求所有sub array中这个值的max
 */

public class Q4_getSubArrSPValue {
    public static int getSPValue(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = 0;
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }



        return res;
    }
}

/**
 * 1. 求数组中sub array L ... R 的累加和 -> 前缀和数组
 * 前缀和数组int[] sum, 每个位置求0 ... i 的和，求他的过程是O(N)的
 * 生成后 L ... R 的累加和 就是sum[R] - sum[L - 1]
 * 2. 找以每个位置 i 为最小值，范围越大的子数组
 * 那不就是找 i 最后离他最近的比他小的值 -> 单调栈
 */
