package src.数值的整数次方;

import java.math.BigDecimal;

public class Solution {

    public double Power(double base, int exponent) {

        // 判断底数是否等于 0.0
        if (new BigDecimal(base).compareTo(new BigDecimal(0.0)) == 0) {
            // 底数为0，指数小于等于零，数学无意义
            if (exponent <= 0)
                throw new RuntimeException("无效输入，无数学意义");
            // 底数为0，指数大于零，结果为 0
            return 0;
        }

        int absexponent = exponent;
        if (exponent < 0)
            absexponent = -exponent;

        double result = getPower(base, absexponent);

        if (exponent < 0)
            result = 1 / result;

        return result;
    }

    /**
     * 优化求幂函数，递归方式实现
     * 当n为偶数，a^n =（a^n/2）*（a^n/2）
     * 当n为奇数，a^n = a^[(n-1)/2] * a^[(n-1)/2] * a
     * 时间复杂度O(logn)
     */
    private double getPower(double base, int absexponent) {

        if (absexponent == 0)
            return 1;
        if (absexponent == 1)
            return base;

        double result = getPower(base, absexponent >> 1);
        result *= result;

        // 如果 absexponent 是奇数，位操作判断效率更高
        if ((absexponent & 1) == 1)
            result *= base;

        return result;
    }
}