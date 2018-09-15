package src.数组中出现次数超过一半的数字;

import java.util.HashMap;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
 * 超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class Solution {
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length == 0)
            return 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num: array) {
            int count = map.getOrDefault(num, 0) + 1;
            if (count > array.length / 2)
                return num;
            map.put(num, count);
        }
        return 0;
    }
}