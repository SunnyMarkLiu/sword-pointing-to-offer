package 两个栈实现队列;

import java.util.Stack;

public class Solution {
    // 用于 push 操作
    Stack<Integer> stack1 = new Stack<Integer>();
    // 用户 pop 操作
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
        // 如果 stack2 不为空，则直接 pop
        if (!stack2.isEmpty())
            return stack2.pop();

        // stack2 为空，将 stack1 中的数全部复制过来，注意判断 stack1 中是否有数字
        while (!stack1.isEmpty())
            stack2.push(stack1.pop());

        if (stack2.isEmpty())
            throw new NullPointerException("no more element to pop");

        return stack2.pop();
    }
}