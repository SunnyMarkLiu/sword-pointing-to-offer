package 字符串的排列;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 输入一个字符串, 可能存在重复值，按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 */
public class Solution {

    /**
     * 回溯法
     */
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> result = new ArrayList<>();
        if (str == null || str.length() == 0)
            return result;
        char[] strs = str.toCharArray();
        Arrays.sort(strs);
        backtracking(strs, new boolean[strs.length], new StringBuilder(), result);
        return result;
    }

    private void backtracking(char[] strs,
                              boolean[] used,
                              StringBuilder curResult,
                              ArrayList<String> result) {
        if (curResult.length() == strs.length) {
            result.add(curResult.toString());
            return;
        }

        for (int i=0; i<strs.length; i++) {
            // 普通 permutations 的过滤，因为从 0 开始，需要判断是否使用过
            if (used[i])
                continue;

            // 如果未使用，但和前面的值有重复（重复值一定相邻的），只需要判断 i-1 和 i 的值是否相等，
            // 如果 i-1 和 i 的值相等，并且 i-1 的值也还没使用过，则当前的 i 可以跳过
            if (i > 0 && !used[i - 1] && strs[i - 1] == strs[i])
                continue;

            curResult.append(strs[i]);
            used[i] = true;
            backtracking(strs, used, curResult, result);
            used[i] = false;
            curResult.deleteCharAt(curResult.length() - 1);
        }
    }
}