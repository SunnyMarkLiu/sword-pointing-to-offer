package src.丑数;

import java.util.ArrayList;

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
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);

        int i2=0,i3=0,i5=0;
        while (list.size() < index) {
            int m2 = list.get(i2) * 2;
            int m3 = list.get(i3) * 3;
            int m5 = list.get(i5) * 5;
            // 获取最小值，作为当前的丑数
            int min = Math.min(m2, Math.min(m3, m5));
            list.add(min);

            // 去除重复值
            if (m2 == min) i2++;
            if (m3 == min) i3++;
            if (m5 == min) i5++;
        }
        return list.get(index - 1);
    }

    public static void main(String[] args) {
        int result = new Solution().GetUglyNumber_Solution(9);
        System.out.println(result);
    }
}
