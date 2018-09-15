package src.两个队列实现栈;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    public void push(int node) {
        // 两个队列都为空，任意选择一个 push 数据
        if (queue1.isEmpty() && queue2.isEmpty()) {
            queue1.add(node);
        }
        else if (!queue1.isEmpty()) { // queue1 不为空，push
            queue1.add(node);
        } else {    // queue2 不为空，push
            queue2.add(node);
        }
    }

    public int pop() {
        // 首先找到不为空的队列
        Queue<Integer> not_empty_queue = null;
        Queue<Integer> empty_queue = null;

        if (!queue1.isEmpty() && queue2.isEmpty()) {
            not_empty_queue = queue1;
            empty_queue = queue2;
        } else if (queue1.isEmpty() && !queue2.isEmpty()) {
            not_empty_queue = queue2;
            empty_queue = queue1;
        } else {  // 两个都为空，则 pop 异常
            throw new NullPointerException("no more element to pop");
        }

        // 将非空队列的元素 pop 并 push 到空队列中，留下最后一个元素直接 pop
        while (not_empty_queue.size() > 1) {
            Integer topValue = not_empty_queue.peek();
            empty_queue.add(topValue);
            not_empty_queue.remove(topValue);
        }
        // 还剩下一个元素直接 pop
        Integer topValue = not_empty_queue.peek();
        not_empty_queue.remove(topValue);
        return topValue;
    }
}
