package src.丑数;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 把只包含质因子 2、3和5 的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把 1 当做是第一个丑数。求按从小到大的顺序的第 N 个丑数。
 */
public class Solution {

    /**
     * 暴力求解，超时
     */
    public int GetUglyNumber_Solution1(int index) {

        int number = 1;
        int count = 0;
        while (count < index) {
            if (isUglyNumber(number))
                count++;
            number++;
        }
        return number;
    }

    /**
     * 一个数只包含 2，3，5 的因子，可将这个数一直除以2，3，5看最后的结果是否为1
     */
    private boolean isUglyNumber(int number) {
        while (number % 2 == 0)
            number /= 2;

        while (number % 3 == 0)
            number /= 3;

        while (number % 5 == 0)
            number /= 5;

        return number == 1;
    }

    /**
     * 一个丑数的因子只有2,3,5，那么丑数 p = 2 ^ x * 3 ^ y * 5 ^ z，
     * 换句话说一个丑数一定由另一个丑数乘以2或者乘以3或者乘以5得到，
     * 那么我们从1开始乘以2,3,5，就得到2,3,5三个丑数，在从这三个丑数出发乘以2,3,5。
     * 注意此方法得到的丑数存在重复值并且没有排序。
     *
     * 维护乘以2，3，5 的三个队列，每次从三个队列里取最小值拼接到丑数的数组里
     */
    public int GetUglyNumber_Solution(int index) {
        // 如果 index < 7，则本身即为丑数: 1, 2, 3, 5
        if (index < 7) return index;

        Queue<Integer> q2 = new LinkedList<>();
        Queue<Integer> q3 = new LinkedList<>();
        Queue<Integer> q5 = new LinkedList<>();

        int curUglyNumber = 1;
        q2.offer(2);
        q2.offer(3);
        q5.offer(5);

        int count = 1;
        while (count < index) {
            // 3个队列中选择最小的
            int min2 = q2.peek();
            int min3 = q3.peek();
            int min5 = q5.peek();

            int curMin = 0;
            Queue<Integer> curMinQueue = null;
            if (min2 < min3 && min2 < min5) {
                curMin = min2;
                curMinQueue = q2;
            } else if (min2 < min3) {
                curMin = min5;
                curMinQueue = q5;
            } else {
                curMin = min3;
                curMinQueue = q3;
            }

            // 当前最小值分别乘以2，3，5，加入到对应的队列，同时丑数记录为 curMin
            curUglyNumber = curMin;
            count++;
            q2.offer(curMin * 2);
            q3.offer(curMin * 3);
            q5.offer(curMin * 5);
            curMinQueue.poll();
        }
        return curUglyNumber;
    }

    public static void main(String[] args) {
        int result = new Solution().GetUglyNumber_Solution(7);
        System.out.println(result);
    }
}
