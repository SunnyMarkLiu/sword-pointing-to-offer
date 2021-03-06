package src.左旋转字符串;

/**
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
 * 就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，
 * 请你把其循环左移K位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
 */
public class Solution {

    public static void main(String[] args) {
        String s = new Solution().LeftRotateString("abcdwfg", 2);
        System.out.println(s);
    }

    public String LeftRotateString(String str,int n) {
        if (str == null || str.length() == 0 || str.length() == n)
            return str;

        // 降低复杂度，忽略重复移动的情况
        n = n % str.length();

        String left = str.substring(0, n);
        String right = str.substring(n);
        return right + left;
    }
}