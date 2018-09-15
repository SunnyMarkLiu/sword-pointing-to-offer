package src.剪绳子;

/**
 * 给你一根长度为n的绳子，请把绳子剪成m段 (m和n都是整数，n>1并且m>1)每段绳子的长度
 * 记为k[0],k[1],...,k[m].请问k[0]*k[1]*...*k[m]可能的最大乘积是多少？
 * <p>
 * 思路：
 * 首先定义函数f(n)为把长度为n的绳子剪成若干段后各段长度乘积的最大值。在剪第一刀时，
 * 我们有n-1种选择，也就是说第一段绳子的可能长度分别为1,2,3.....，n-1。
 * 因此f(n)=max(f(i)*f(n-i))，其中0<i<n。
 * 这是一个自上而下的递归公式。由于递归会有大量的不必要的重复计算。一个更好的办法是按照
 * 从下而上的顺序计算，也就是说我们先得到f(2),f(3)，再得到f(4),f(5)，直到得到f(n)
 */
public class Solution {

    /**
     * 常规的需要O(n2)的时间复杂度和O(n)的空间复杂度的动态规划思路
     * 题目的意思是:绳子至少是2米，并且必须最少剪一刀。
     */
    public int maxProductAfterCutting(int length) {
        if (length < 2)
            return 0;

        if (length == 2)
            return 1;

        if (length == 3)
            return 2;

        // 子问题的最优解存储在f数组中，数组中的第i个元素表示把长度为i的绳子剪成若干段后各段长度乘积的最大值。
        int[] products = new int[length + 1];
        // 第二层循环从1开始，保证了至少剪一刀，此处的 producs 初始化的注意可以不剪情况下的最大值
        products[0] = 0;
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;

        for (int i = 4; i <= length; i++) {
            int max = 0;
            // j=1开始保证至少剪一刀，小于 i / 2，避免对称重复计算
            for (int j = 1; j <= i / 2; j++) {
                int product = products[j] * products[(i - j)];
                if (product > max)
                    max = product;
            }
            products[i] = max;
        }
        int result = products[length];
        return result;
    }

}
