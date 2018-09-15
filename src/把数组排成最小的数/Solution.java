package src.把数组排成最小的数;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 */
public class Solution {
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0)
            return "";

        // 将数组排序，不过排序的目标和传统的数据排序有区别，可使用 Arrays.sort 和 Comparator 接口
        // 此处使用最基本的冒泡排序
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                String s1 = numbers[j] + "" + numbers[j+1];
                String s2 = numbers[j+1] + "" + numbers[j];
                // 如果 s1 > s2
                if (s1.compareTo(s2) > 0) {
                    int tmp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = tmp;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int num : numbers) {
            sb.append(num);
        }
        return sb.toString();
    }
}