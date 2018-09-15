package src.连续子数组的最大和;

/**
 * 连续子数组的最大和
 * 例如:{6,-3,-2,7,-15,1,2,2},
 * 连续子向量的最大和为8(从第0个开始,到第3个为止)
 */
public class Solution {

    /**
     * 遍历，遇到负和抛弃之前的结果，重新积累，期间保留最大值
     */
    public int FindGreatestSumOfSubArray1(int[] array) {
        if (array == null || array.length == 0)
            return 0;

        // 遍历数组，curSum 记录某一起点开始到当前位置的序列的和
        // 如果 curSum < 0 说明当前节点之前的序列的和为负数，直接丢弃
        int curSum = 0;
        int maxSum = array[0];

        for (int i = 0; i < array.length; i++) {
            // i 之前的序列和为负数，直接丢弃，从当前的 i 开始累加
            if (curSum < 0)
                curSum = array[i];
            else
                curSum += array[i];

            if (curSum > maxSum)
                maxSum = curSum;
        }
        return maxSum;
    }

    /**
     * 动态规划：
     * <p>
     * dp[i]表示以元素array[i]结尾的最大连续子数组和.
     * 以[-2,-3,4,-1,-2,1,5,-3]为例
     * 可以发现,
     * dp[0] = -2
     * dp[1] = -3
     * dp[2] = 4
     * dp[3] = 3
     * 以此类推,会发现
     * dp[i] = max{dp[i-1]+array[i],array[i]}.
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0)
            return 0;

        int[] dp = new int[array.length];
        dp[0] = array[0];

        int maxSum = array[0];
        for (int i = 1; i < array.length; i++) {
            dp[i] = Math.max(dp[i - 1] + array[i], array[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }
}