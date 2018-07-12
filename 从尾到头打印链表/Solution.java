package 从尾到头打印链表;

import java.util.ArrayList;
import java.util.Stack;


public class Solution {

    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 使用 stack 实现
     */
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {

        ArrayList<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        // 遍历 list，入栈
        while (null != listNode) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }

        // 遍历 stack，出栈
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    /**
     * 使用递归实现
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        ArrayList<Integer> result = new ArrayList<>();

        helper(result, listNode);

        return result;
    }

    private void helper(ArrayList<Integer> result, ListNode listNode) {
        if (null == listNode)
            return;

        helper(result, listNode.next);

        result.add(listNode.val);
    }
}