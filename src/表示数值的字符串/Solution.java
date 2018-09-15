package src.表示数值的字符串;

public class Solution {
    /**
     * 表示数值的字符串满足： A[.[B][e|EC] 或者 .B[e|EC]
     * 注意 AC 可能有 +-
     * 从头开始扫描，如果遇到小数点，开始扫描小数部分的 B，如果遇到 e或E，则扫描 C
     */
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0)
            return false;
        // 正负号、小数点、e是否出现过
        boolean plusSign = false, decimal = false, hasE=false;

        for (int i = 0; i < str.length; i++) {
            if (str[i] == '+' || str[i] == '-') {
                // 第一次出现正负号，必须出现在第一个位置或在e|E的后面，反之如果不出现在第一个位置或不是在e|E的后面，false
                if (!plusSign && !(i == 0 || str[i-1] == 'e' || str[i-1] == 'E'))
                    return false;
                // 第二次出现正负号，必须出现在 e|E 的后面，否则 false
                if (plusSign && str[i-1] != 'e' && str[i-1] != 'E')
                    return false;
                plusSign = true;
            } else if (str[i] == 'e' || str[i] == 'E') {
                // 第二次出现e，直接false
                if (hasE) return false;
                // e后面必须接数字
                if (i == str.length - 1) return false;
                hasE = true;
            } else if (str[i] == '.') {  // 出现小数点
                // 第二次出现小数点，并且 e 后面不能有小数点
                if (hasE || decimal) return false;
                decimal = true;
            } else if (str[i] < '0' || str[i] > '9') // 非法数字
                return false;
        }
        return true;
    }
}
