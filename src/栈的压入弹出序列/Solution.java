package src.栈的压入弹出序列;

import java.util.Stack;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 */
public class Solution {


    public boolean IsPopOrder(int[] pushA, int[] popA) {
        // 两个序列至少有一个为 null，false
        if (pushA == null || popA == null)
            return false;
        // 两个序列都为空，true
        if (pushA.length == 0 && popA.length == 0)
            return true;
        // 两个序列长度不一致，false
        if (pushA.length != popA.length)
            return false;

        Stack<Integer> stack = new Stack<>();

        // 如果下一个弹出的数字刚好是栈顶，那么直接出栈；
        // 如果下一个弹出的数字不在栈顶，则把压栈序列中还没有入栈的数字入栈，直到找到下一个出栈的数字
        // 如果入栈序列结束了，还没找到下一个出栈的数字，则 false

        int popIndex = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (popIndex < popA.length && stack.peek() == popA[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }
}