package src.二进制中1的个数;

public class Solution {
    public int NumberOf1_slow(int n) {
        int count = 0;

        int flag = 1;

        while (flag != 0) {
            if ((n & flag) != 0)
                count++;

            flag = flag << 1;   // 向左检测 1 的个数
        }

        return count;
    }

    /**
     * 一个整数减去1，和原整数进行与操作，会把最右边的1变成0，
     * 那么一个整数的二进制中有多少个1，就可以进行多少次这样的操作
     */
    public int NumberOf1(int n) {
        int count = 0;

        while (n != 0) {
            count++;
            n = (n-1) & n;
        }

        return count;
    }
}