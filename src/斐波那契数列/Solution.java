package src.斐波那契数列;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    Map<Integer, Integer> result_map = new HashMap<>();

    /**
     * 递归的方式实现，注意全局遍历保存中间结果，空间换时间
     */
    public int Fibonacci1(int n) {
        if ( n == 0)
            return 0;

        if (n == 1)
            return 1;

        int sum_n_1;
        if (result_map.containsKey(n - 1)) {
            sum_n_1 = result_map.get(n - 1);
        } else {
            sum_n_1 = Fibonacci1(n - 1);
            result_map.put(n-1, sum_n_1);
        }

        int sum_n_2;
        if (result_map.containsKey(n - 2)) {
            sum_n_2 = result_map.get(n - 2);
        } else {
            sum_n_2 = Fibonacci1(n - 2);
            result_map.put(n-2, sum_n_2);
        }

        return sum_n_1 + sum_n_2;
    }

    /**
     * 更直接、实用高效的方法，利用斐波拉契数列的方法，依次自下往上计算，只保存 n-1 和 n-2 的值
     */
    public int Fibonacci(int n) {
        if ( n == 0)
            return 0;

        if (n == 1)
            return 1;

        int sum_n_2 = 0;
        int sum_n_1 = 1;
        int sum_n = 0;

        for (int i = 0; i < n - 1; i++) {
            sum_n = sum_n_2 + sum_n_1;
            sum_n_2 = sum_n_1;
            sum_n_1 = sum_n;
        }
        return sum_n;
    }
}