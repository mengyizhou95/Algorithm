package com.ymeng.monotonicStack;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个int arr，和一个int num
 * 某个arr中的子数组sub，必须满足：
 * sub中的最大值 - sub中的最小值 <= num
 * 求这样的子数组的数量
 */

public class Q2_NumOfSubArr {
    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qmin = new LinkedList<>();
        LinkedList<Integer> qmax = new LinkedList<>();
        int l = 0, r = 0;
        // [L ... R ) -> [0, 0) 无元素 [0, 1) -> 0
        int res = 0;
        while (l < arr.length) { //从0，1，2.....开始尝试每一个开头
            //如果此时window开头l，下面的while工作直到r扩张到违规
            while (r < arr.length) {
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[r]) {
                    qmin.pollLast();
                }
                qmin.addLast(r);
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[r]) {
                    qmax.pollLast();
                }
                qmax.addLast(r);
                //当break发生时，r就来到了第一个非法的位置
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                r++;
            }
            //既然得到了这个非法位置，就可以收集结果了
            res += r - l;
            //之后L要向左扩张了，那么要考察他影不影响qmax/qmin
            if (qmin.peekFirst() == l) {
                qmin.pollFirst();
            }
            if (qmax.peekFirst() == l) {
                qmax.pollFirst();
            }
            l++;
        }
        return res;
    }
}
/**
 * 注意两个结论：
 * 1 如果L —— R已经达标，那么在此范围内的任意子数组均达标
 * 2 如果L —— R已经不达标，那么R往右扩大，L往左扩大，必定也不达标
 */
