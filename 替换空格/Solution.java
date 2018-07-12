package 替换空格;

public class Solution {
    /***
     * 先计算需要多少空间，然后从后往前移动，则每个字符只为移动一次
     */
    public String replaceSpace(StringBuffer str) {
        if (null == str)
            return null;

        int space_count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (' ' == str.charAt(i))
                space_count += 1;
        }

        // 设置原始字符串待移动的下表
        int ori_index = str.length() - 1;

        // 设置替换之后的字符的长度
        int new_length = str.length() + 2 * space_count;
        str.setLength(new_length);

        // 待插入的新字符串下表
        int new_index = new_length - 1;

        while (ori_index >= 0) {
            if (' ' != str.charAt(ori_index)) {
                // 不为空格直接移动
                str.setCharAt(new_index--, str.charAt(ori_index));
            } else {
                str.setCharAt(new_index--, '0');
                str.setCharAt(new_index--, '2');
                str.setCharAt(new_index--, '%');
            }
            ori_index -= 1;
        }
        return str.toString();
    }
}