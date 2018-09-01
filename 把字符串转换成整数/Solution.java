package 把字符串转换成整数;

/**
 * 字符串转 Int
 *
 * 数据溢出
 * 空字符串
 * 只有正负号
 * 有无正负号
 * 错误标志输出
 */
public class Solution {

    // 转发发生错误的标记
    private boolean error = false;

    public int StrToInt(String str) {

        // 非法输入
        if (str == null) {
            error = true;
            return 0;
        }
        str = str.trim();
        if (str.length() == 0) {
            error = true;
            return 0;
        }

        // 判断第一位是否有符号位
        int symbol = 1; // 1：正数，-1：负数
        int start = 0;  // 比较的数开始标记
        char[] chars = str.toCharArray();
        if (chars[0] == '+') {
            start = 1;
        } else if (chars[0] == '-') {
            symbol = -1;
            start = 1;
        }

        // 只包含+-号
        if (start == chars.length) {
            error = true;
            return 0;
        }

        // 逐个字符遍历计算
        int value = 0;
        for (int i = start; i < chars.length; i++) {
            // 非法字符
            if (chars[i] > '9' || chars[i] < '0') {
                error = true;
                return 0;
            }

            int tmp = 10 * value + (chars[i] - '0');
            // 判断是否溢出，判断标准是进行一次逆运算，然后和之前的 value 比较是否相等
            if ((tmp - (chars[i] - '0')) / 10 != value) {
                error = true;
                return 0;
            }

            value = tmp;
        }

        return symbol * value;
    }
}