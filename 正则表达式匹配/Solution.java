package 正则表达式匹配;

public class Solution {
    public boolean match(char[] str, char[] pattern) {
        if ((str == null) || (pattern == null))
            return false;

        int strIndex = 0;
        int patternIndex = 0;
        return matchHelper(str, strIndex, pattern, patternIndex);
    }

    /**
     * 四种情况：
     * <p>
     * 1、str 到尾，pattern 到尾，匹配成功
     * 2、str 未到尾，pattern 到尾，匹配失败
     * 3、str 到尾，pattern 未到尾(不一定匹配失败，因为a*可以匹配0个字符)
     * 4、str 未到尾，pattern 未到尾
     */
    private boolean matchHelper(char[] str, int strIndex, char[] pattern, int patternIndex) {
        // str到尾，pattern到尾，匹配成功
        if (strIndex == str.length && patternIndex == pattern.length)
            return true;

        // str未到尾，pattern到尾，匹配失败
        if (strIndex < str.length && patternIndex == pattern.length)
            return false;

        // str到尾，pattern未到尾(不一定匹配失败，因为a*可以匹配0个字符)
        if (strIndex == str.length && patternIndex < pattern.length) {
            // 只有pattern剩下的部分类似a*b*c*的形式，才匹配成功
            if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
                return matchHelper(str, strIndex, pattern, patternIndex + 2);   // 跳过 a*
            }
            return false;
        }

        // str 未到尾，pattern 未到尾
        // pattern 的第二个字符是 *
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            // 匹配上，当前str和pattern的字符匹配上
            if ((strIndex < str.length && str[strIndex] == pattern[patternIndex]) ||
                    (pattern[patternIndex] == '.' && strIndex < str.length)) {
                // str 后移1位，pattern 有两种选择，后移两位或者保持不动(用于匹配是否出现多次)
                // 还有一种可能是第一个字符匹配上，或者pattern第一个为 . pattern 可以跳过两个
                return matchHelper(str, strIndex + 1, pattern, patternIndex + 2) ||
                        matchHelper(str, strIndex + 1, pattern, patternIndex) ||
                        matchHelper(str, strIndex, pattern, patternIndex + 2);
            } else {    // pattern 第二个为 *，且未匹配上，直接 pattern 跳过两个
                return matchHelper(str, strIndex, pattern, patternIndex + 2);
            }
        }
        // pattern 的第二个字符不是 *
        if ((strIndex < str.length && str[strIndex] == pattern[patternIndex]) ||
                (pattern[patternIndex] == '.' && strIndex < str.length)) {
            return matchHelper(str, strIndex + 1, pattern, patternIndex + 1);   // 后移下一位
        }
        return false;
    }
}