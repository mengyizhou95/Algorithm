package com.ymeng.monotonicStack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * com.ymeng.monotonicStack.Q1
 * 假设一个固定大小的窗口，依次划过arr，返回每一次状况下的最大值
 * arr = [4,3,5,4,3,3,6,7] W = 3
 * result = [5,5,5,4,6,7]
 */
public class Q1_SildingWindowMaxArray {
    public int[] getMaxWindow(int[] arr, int w) {
        if(arr == null || w < 1 || arr.length < w) {
            return null;
        }

        int[] res = new int[arr.length - w + 1];
        LinkedList<Integer> q = new LinkedList<>(); //放置index而非值
        int index = 0;
        for(int i = 0; i < arr.length; i++) { //让i ->[i] 进入window，i就是r
            //R放在哪里？要么比他大的数后，要么放在空q里。这里描述了什么时候R代表的数进window
            while(!q.isEmpty() && arr[q.peekLast()] <= arr[i]) {
                q.pollLast();
            }
            q.addLast(i);
            //现在数进来了，那么如果窗口没有形成W的长度之前，窗口是不弹出数字的
            //我来到i了，那么i-w就是过期下标
            if(q.peekFirst() == i - w) {
                q.pollFirst();
            }
            //以上就结束了window更新
            //以下开始生成结果
            //窗口没有形成w的长度之前，不生成答案，达到w长度了才收集
            if(i >= w - 1) {
                res[index++] = arr[q.peekFirst()];
            }
        }
        return res;
    }
}
