package 跳台阶;

public class Solution {
    /***
     * 斐波那契数列的应用️
     *
     * 跳上 n 阶台阶的跳法 = 第一次跳 1 阶剩下的 n-1 阶台阶的跳法 + 第一次跳 2 阶剩下的 n-2 阶台阶的跳法
     *
     * f(n) = f(n-1) + f(n-2),
     * f(0) = 0
     * f(1) = 1
     * f(2) = 2
     */
    public int JumpFloor(int target) {
        if (target <= 0)
            return 0;

        if (target == 1)
            return 1;

        if (target == 2)
            return 2;

        int sum_n_2 = 1;
        int sum_n_1 = 2;
        int sum_n = 0;

        for (int i=0; i < target - 2; i++) {
            sum_n = sum_n_2 + sum_n_1;
            sum_n_2 = sum_n_1;
            sum_n_1 = sum_n;
        }

        return sum_n;
    }
}