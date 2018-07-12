package 数组中重复的数字;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 利用 HashMap 的 O(1) 查询的效率实现
     */
    public boolean duplicate1(int[] numbers, int length, int[] duplication) {

        if (numbers == null || length < 2) {
            duplication[0] = -1;
            return false;
        }

        Map<Integer, Integer> numberMap = new HashMap<>();

        for (int i = 0; i < length; i++) {
            if (numberMap.containsKey(numbers[i])) {
                duplication[0] = numbers[i];
                return true;
            } else {
                numberMap.put(numbers[i], 1);
            }
        }
        return false;
    }

    /***
     *  不需要额外的数组或者hash table来保存，题目里写了数组里数字的范围保证在0 ~ n-1 之间，
     *  所以可以利用现有数组设置标志，当一个数字被访问过后，可以设置对应位上的数 + n，之后再遇到相同的数时，
     *  会发现对应位上的数已经大于等于n了，那么直接返回这个数即可。
     *
     *  ==> 检测下表是否都指向同一个数，如果不存在重复，0 ~ n-1则能保证扫描到了所有数
     *  时间复杂度 O(n)，空间复杂度 O(1)
     */
    public boolean duplicate(int[] numbers, int length, int[] duplication) {
        if (numbers == null || length < 2) {
            duplication[0] = -1;
            return false;
        }

        for (int i = 0; i < length; i++) {
            int index = numbers[i];
            if (index >= length) {
                index -= length;
            }

            if (numbers[index] >= length) { // 说明有两次加了 length，即此下表对应的数为重复数字
                duplication[0] = index;
                return true;
            }
            numbers[index] = numbers[index] + length;
        }
        duplication[0] = -1;
        return false;
    }
}
