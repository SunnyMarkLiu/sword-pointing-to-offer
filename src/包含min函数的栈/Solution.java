package src.包含min函数的栈;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 * <p>
 * 思路：用一个栈 data 保存数据，用另外一个栈 minStack 保存依次入栈最小的数
 */
public class Solution {

    private Stack<Integer> data = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        data.push(node);
        if (minStack.isEmpty())
            minStack.push(node);
        else if (node <= minStack.peek())   // 注意 <=
            minStack.push(node);
    }

    public void pop() {
        // 如果 data pop出来的是当前的最小值，则需要将 minStack pop
        if (data.size() == 0)
            throw new EmptyStackException();

        if (data.peek() == (int) minStack.peek())
            minStack.pop();

        data.pop();
    }

    public int top() {
        return data.peek();
    }

    public int min() {
        return minStack.peek();
    }
}