package src.整数中1出现的次数;

/**
 * 从 1 到 n 中 1 出现的次数
 *
 * 如 n = 13
 * 包含1的数字有1、10、11、12、13因此共出现6次
 */
public class Solution {
    public int NumberOf1Between1AndN_Solution(int n) {
        if (n <= 0) return 0;

        int count = 0;
        for (int i=1; i <= n; i++) {
            String num = i + "";
            for (int j = 0; j < num.length(); j++) {
                if (num.charAt(j) == '1')
                    count++;
            }
        }
        return count;
    }
}