package com.ymeng.monotonicStack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 单调栈解决的问题是，找到一个array中每一个位置，他左边最近的max/min pos/val，右边最近的max/min pos/val
 * 方法：维护一个单调栈，他是从 小->大/大->小 排列的，弹出时，谁让他弹出的就是右侧的min/max, 下面的就是左侧的min/max
 * O（N）
 */
public class Q3_MonotonicStack {
    public static int[][] getNearest(int[] arr) {
        int[][] res = new int[arr.length][2];
        Deque<List<Integer>> stack = new ArrayDeque<>(); //栈内存储index组成的list
        //i -> arr[i] 进栈
        for (int i = 0; i < arr.length; i++) {
            //底 ——> 顶， 小 ——> 大
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popList = stack.pop();
                int leftIndex = stack.isEmpty()? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer popi: popList) {
                    res[popi][0] = leftIndex;
                    res[popi][1] = i;
                }
            }
            //如果相等就进list，如果大于就起一个新的list
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(Integer.valueOf(i)) ;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        //最后结算栈内还剩下没弹出的元素
        while (!stack.isEmpty()) {
            List<Integer> popList = stack.pop();
            int leftIndex = stack.isEmpty()? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer popi: popList) {
                res[popi][0] = leftIndex;
                res[popi][1] = -1;
            }
        }
        return res;
    }
}
