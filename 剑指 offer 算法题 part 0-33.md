## 1. 数组中重复的数字
```java
package 数组中重复的数字;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 利用 HashMap 的 O(1) 查询的效率实现
     */
    public boolean duplicate1(int[] numbers, int length, int[] duplication) {

        if (numbers == null || length < 2) {
            duplication[0] = -1;
            return false;
        }

        Map<Integer, Integer> numberMap = new HashMap<>();

        for (int i = 0; i < length; i++) {
            if (numberMap.containsKey(numbers[i])) {
                duplication[0] = numbers[i];
                return true;
            } else {
                numberMap.put(numbers[i], 1);
            }
        }
        return false;
    }

    /***
     *  不需要额外的数组或者hash table来保存，题目里写了数组里数字的范围保证在0 ~ n-1 之间，
     *  所以可以利用现有数组设置标志，当一个数字被访问过后，可以设置对应位上的数 + n，之后再遇到相同的数时，
     *  会发现对应位上的数已经大于等于n了，那么直接返回这个数即可。
     *
     *  ==> 检测下表是否都指向同一个数，如果不存在重复，0 ~ n-1则能保证扫描到了所有数
     *  时间复杂度 O(n)，空间复杂度 O(1)
     */
    public boolean duplicate(int[] numbers, int length, int[] duplication) {
        if (numbers == null || length < 2) {
            duplication[0] = -1;
            return false;
        }

        for (int i = 0; i < length; i++) {
            int index = numbers[i];
            if (index >= length) {
                index -= length;
            }

            if (numbers[index] >= length) { // 说明有两次加了 length，即此下表对应的数为重复数字
                duplication[0] = index;
                return true;
            }
            numbers[index] = numbers[index] + length;
        }
        duplication[0] = -1;
        return false;
    }
}

```

## 2. 二维数组中的查找
```java
package 二维数组中的查找;

public class Solution {

    /***
     * 从左下角元素往上查找，右边元素是比这个元素大，上边是的元素比这个元素小
     * 比 target 大，则右移，比target小，则上移.
     */
    public boolean Find(int target, int [][] array) {
        if (null == array || array.length == 0)
            return false;

        int row = array.length - 1;     // 行
        int col = 0;  // 列

        while (row >= 0 && col < array[0].length) {
            if (array[row][col] == target)
                return true;

            // 没找到，则从左下角元素往上查找
            if (array[row][col] > target)
                row -= 1;
            else
                col += 1;
        }
        return false;
    }
}

```

## 3. 替换空格
```java
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
```

## 4. 从尾到头打印链表
```java
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
```

## 5. 二叉树的下一个结点
```java
package 二叉树的下一个结点;

public class Solution {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null)
            return null;

        // 1. 如果该节点有右子树，则下一个节点是这个右子树的最左节点
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null)
                pNode = pNode.left;
            return pNode;
        }

        // 2. 如果该节点没有右子树，两种情况：
        //      如果这个节点处于左边的位置则下一个节点是其父节点；
        //      如果这个节点处于右边的位置则需要向上寻找父节点的父节点，找到的父节点使得节点是这个父节点的左节点

        TreeLinkNode parent = pNode.next;
        if (parent != null && parent.left == pNode) // pNode 是 parent 的左节点
            return parent;

        while (parent != null && parent.right == pNode) { // 直到找到 pNode 是 parent 的左节点
            pNode = parent;
            parent = parent.next; // 向上找父节点
        }

        return parent;
    }
}
```

## 6. 根据前序和后续遍历重建二叉树
```java
package 根据前序和后续遍历重建二叉树;

import java.util.Arrays;

public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode reConstructBinaryTree(int[] pre_order, int[] in_order) {
        if (null == pre_order || null == in_order)
            return null;

        if (pre_order.length == 0 || in_order.length == 0)
            return null;

        // 根据 pre_order 的第一个节点创建头节点
        TreeNode head = new TreeNode(pre_order[0]);

        // 找到 head 在 in_order 的下标
        for (int index = 0; index < in_order.length; index++)
            if (pre_order[0] == in_order[index]) {
                // 创建左子树
                head.left = reConstructBinaryTree(
                        Arrays.copyOfRange(pre_order, 1, index + 1),    // index 指定了左子树的长度
                        Arrays.copyOfRange(in_order, 0, index)
                );
                // 创建右子树
                head.right = reConstructBinaryTree(
                        Arrays.copyOfRange(pre_order, index + 1, pre_order.length),
                        Arrays.copyOfRange(in_order, index + 1, in_order.length)
                );
            }
        return head;
    }
}
```

## 7. 两个栈实现队列
```java
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
```

## 8. 两个队列实现栈
```java
package 两个队列实现栈;

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

```

## 9. 斐波那契数列
```java
package 斐波那契数列;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    Map<Integer, Integer> result_map = new HashMap<>();

    /**
     * 递归的方式实现，注意全局遍历保存中间结果，空间换时间
     */
    public int Fibonacci1(int n) {
        if ( n == 0)
            return 0;

        if (n == 1)
            return 1;

        int sum_n_1;
        if (result_map.containsKey(n - 1)) {
            sum_n_1 = result_map.get(n - 1);
        } else {
            sum_n_1 = Fibonacci1(n - 1);
            result_map.put(n-1, sum_n_1);
        }

        int sum_n_2;
        if (result_map.containsKey(n - 2)) {
            sum_n_2 = result_map.get(n - 2);
        } else {
            sum_n_2 = Fibonacci1(n - 2);
            result_map.put(n-2, sum_n_2);
        }

        return sum_n_1 + sum_n_2;
    }

    /**
     * 更直接、实用高效的方法，利用斐波拉契数列的方法，依次自下往上计算，只保存 n-1 和 n-2 的值
     */
    public int Fibonacci(int n) {
        if ( n == 0)
            return 0;

        if (n == 1)
            return 1;

        int sum_n_2 = 0;
        int sum_n_1 = 1;
        int sum_n = 0;

        for (int i = 0; i < n - 1; i++) {
            sum_n = sum_n_2 + sum_n_1;
            sum_n_2 = sum_n_1;
            sum_n_1 = sum_n;
        }
        return sum_n;
    }
}
```

## 10. 旋转数组的最小数字
```java
package 旋转数组的最小数字;

import java.util.ArrayList;

public class Solution {
    public int minNumberInRotateArray(int [] array) {
        if (null == array || array.length == 0)
            throw new NullPointerException("empty array error");

        int left = 0;
        int right = array.length - 1;
        int mid = 0;

        while (left < right) {
            mid = left + (right - left) / 2;

            // 如果中间的值大于最右边的值，则左边是有序的
            if (array[mid] > array[right]) {
                left = mid + 1;  // 中间值不可能为最小值
            } else {    // mid <= right，则右边是有序的，最小值在左边
                right = mid;
            }
        }

        return array[right];
    }
}

```

## 11. 跳台阶
```java
package 跳台阶;

public class Solution {
    /***
     * 斐波那契数列的应用️
     *
     * 跳上 n 阶台阶的跳法 = 第一次跳 1 阶剩下的 n-1 阶台阶的跳法 + 第一次跳 2 阶剩下的 n-2 阶台阶的跳法
     *
     * f(n) = f(n-1) + f(n-2),
     * f(0) = 0
     * f(1) = 1
     * f(2) = 2
     */
    public int JumpFloor(int target) {
        if (target <= 0)
            return 0;

        if (target == 1)
            return 1;

        if (target == 2)
            return 2;

        int sum_n_2 = 1;
        int sum_n_1 = 2;
        int sum_n = 0;

        for (int i=0; i < target - 2; i++) {
            sum_n = sum_n_2 + sum_n_1;
            sum_n_2 = sum_n_1;
            sum_n_1 = sum_n;
        }

        return sum_n;
    }
}
```

## 12. 变态跳台阶
```java
package 变态跳台阶;

public class Solution {

    public int JumpFloorII(int target) {
        if (target <= 0)
            return 0;

        if (target == 1)
            return 1;

        return 2 << (target - 2);
    }
}
```

## 13. 矩阵中的路径
```java
package 矩阵中的路径;

public class Solution {
    /**
     * 回溯法
     */
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || str == null || rows == 0 || cols == 0)
            return false;

        // 定义一个布尔类型的矩阵用于标示是否被访问过
        boolean[] visited = new boolean[rows * cols];

        // 遍历 matrix 的所有元素
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // 从矩阵的当前元素开始遍历
                if (hasPathHelper(matrix, rows, cols, str, row, col, visited, 0))
                    return true;
            }
        }
        return false;
    }

    /**
     * 递归从四个方向进行检测
     *
     * next_match_length 下一个待匹配成功的长度
     */
    private boolean hasPathHelper(char[] matrix, int rows, int cols, char[] str,
                                  int row, int col, boolean[] visited, int next_match_length) {

        // 匹配完最后一个字符
        if (next_match_length == str.length)
            return true;

        // 当前节点（row, col）是否匹配到
        boolean hasPath = false;

        // 匹配到
        if (row >= 0 && row < rows && col >= 0 && col < cols && // 边界检查
            matrix[row * cols + col] == str[next_match_length] &&  // 匹配成功
            !visited[row * cols + col]) {  // 匹配的节点没有被访问过

            // 设置标志位
            visited[row * cols + col] = true;

            // 递归上下左右检测，匹配成功长度+1
            hasPath = hasPathHelper(matrix, rows, cols, str, row - 1, col, visited, next_match_length + 1) ||
                      hasPathHelper(matrix, rows, cols, str, row + 1, col, visited, next_match_length + 1) ||
                      hasPathHelper(matrix, rows, cols, str, row, col - 1, visited, next_match_length + 1) ||
                      hasPathHelper(matrix, rows, cols, str, row, col + 1, visited, next_match_length + 1);

            // 如果当前节点（row, col）的四周都没匹配到，则回溯
            if (!hasPath)
                visited[row * cols + col] = false;  // 取消标志位
        }
        return hasPath;
    }
}
```

## 14. 机器人的运动范围
```java
package 机器人的运动范围;


public class Solution {

    /**
     * 回溯法
     */
    public int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows <= 0 || cols <= 0)
            return 0;

        // 定义一个布尔类型的矩阵用于标示是否被访问过
        boolean[] visited = new boolean[rows * cols];
        return movingCountHelper(threshold, rows, cols, 0, 0, visited);
    }

    private int movingCountHelper(int threshold, int rows, int cols, int row, int col, boolean[] visited) {

        int count = 0;

        if (row >=0 && row < rows && col >=0 && col < cols &&   // 边界检查
                sumUnderThreshold(threshold, row, col) &&
                !visited[row * cols + col]) {   // 匹配的节点没有被访问过
            // 设置标志位
            visited[row * cols + col] = true;

            // 递归遍历上下左右节点
            count = 1 + movingCountHelper(threshold, rows, cols, row - 1, col, visited) +
                        movingCountHelper(threshold, rows, cols, row + 1, col, visited) +
                        movingCountHelper(threshold, rows, cols, row, col - 1, visited) +
                        movingCountHelper(threshold, rows, cols, row, col + 1, visited);
        }
        return count;
    }

    private boolean sumUnderThreshold(int threshold, int row, int col) {

        int sum = 0;
        while (row > 0) {
            sum += row % 10;
            row = row / 10;
        }

        while (col > 0) {
            sum += col % 10;
            col = col / 10;
        }

        return sum <= threshold;
    }
}
```

## 15. 二进制中1的个数
```java
package 二进制中1的个数;

public class Solution {
    public int NumberOf1_slow(int n) {
        int count = 0;

        int flag = 1;

        while (flag != 0) {
            if ((n & flag) != 0)
                count++;

            flag = flag << 1;   // 向左检测 1 的个数
        }

        return count;
    }

    /**
     * 一个整数减去1，和原整数进行与操作，会把最右边的1变成0，
     * 那么一个整数的二进制中有多少个1，就可以进行多少次这样的操作
     */
    public int NumberOf1(int n) {
        int count = 0;

        while (n != 0) {
            count++;
            n = (n-1) & n;
        }

        return count;
    }
}
```

## 16. 剪绳子
```java
package 剪绳子;

/**
 * 给你一根长度为n的绳子，请把绳子剪成m段 (m和n都是整数，n>1并且m>1)每段绳子的长度
 * 记为k[0],k[1],...,k[m].请问k[0]*k[1]*...*k[m]可能的最大乘积是多少？
 * <p>
 * 思路：
 * 首先定义函数f(n)为把长度为n的绳子剪成若干段后各段长度乘积的最大值。在剪第一刀时，
 * 我们有n-1种选择，也就是说第一段绳子的可能长度分别为1,2,3.....，n-1。
 * 因此f(n)=max(f(i)*f(n-i))，其中0<i<n。
 * 这是一个自上而下的递归公式。由于递归会有大量的不必要的重复计算。一个更好的办法是按照
 * 从下而上的顺序计算，也就是说我们先得到f(2),f(3)，再得到f(4),f(5)，直到得到f(n)
 */
public class Solution {

    /**
     * 常规的需要O(n2)的时间复杂度和O(n)的空间复杂度的动态规划思路
     * 题目的意思是:绳子至少是2米，并且必须最少剪一刀。
     */
    public int maxProductAfterCutting(int length) {
        if (length < 2)
            return 0;

        if (length == 2)
            return 1;

        if (length == 3)
            return 2;

        // 子问题的最优解存储在f数组中，数组中的第i个元素表示把长度为i的绳子剪成若干段后各段长度乘积的最大值。
        int[] products = new int[length + 1];
        // 第二层循环从1开始，保证了至少剪一刀，此处的 producs 初始化的注意可以不剪情况下的最大值
        products[0] = 0;
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;

        for (int i = 4; i <= length; i++) {
            int max = 0;
            // j=1开始保证至少剪一刀，小于 i / 2，避免对称重复计算
            for (int j = 1; j <= i / 2; j++) {
                int product = products[j] * products[(i - j)];
                if (product > max)
                    max = product;
            }
            products[i] = max;
        }
        int result = products[length];
        return result;
    }

}

```

## 17. 数值的整数次方
```java
package 数值的整数次方;

import java.math.BigDecimal;

public class Solution {

    public double Power(double base, int exponent) {

        // 判断底数是否等于 0.0
        if (new BigDecimal(base).compareTo(new BigDecimal(0.0)) == 0) {
            // 底数为0，指数小于等于零，数学无意义
            if (exponent <= 0)
                throw new RuntimeException("无效输入，无数学意义");
            // 底数为0，指数大于零，结果为 0
            return 0;
        }

        int absexponent = exponent;
        if (exponent < 0)
            absexponent = -exponent;

        double result = getPower(base, absexponent);

        if (exponent < 0)
            result = 1 / result;

        return result;
    }

    /**
     * 优化求幂函数，递归方式实现
     * 当n为偶数，a^n =（a^n/2）*（a^n/2）
     * 当n为奇数，a^n = a^[(n-1)/2] * a^[(n-1)/2] * a
     * 时间复杂度O(logn)
     */
    private double getPower(double base, int absexponent) {

        if (absexponent == 0)
            return 1;
        if (absexponent == 1)
            return base;

        double result = getPower(base, absexponent >> 1);
        result *= result;

        // 如果 absexponent 是奇数，位操作判断效率更高
        if ((absexponent & 1) == 1)
            result *= base;

        return result;
    }
}
```

## 18. 删除链表的节点O1时间复杂度
```java
package 删除链表的节点O1时间复杂度;


public class Solution {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * O(1)时间复杂度内删除链表中的节点
     * 1. 异常检查：指针为空直接返回
     * 2. 待删除的节点不在链表尾部，用下一个节点的值覆盖待删除的节点，再删除下一个节点。
     * 3. 待删除的节点为尾节点，此时还需要从头遍历，获取待删除节点的上一个节点。
     */
    public ListNode deleteListNode(ListNode pHead, ListNode pDeleted) {
        if (pHead == null || pDeleted == null)
            return null;

        // 如果删除的节点为 head
        if (pDeleted == pHead) {
            pHead = pHead.next;
            return pHead;
        }

        // 待删除的节点不在链表尾部
        if (pDeleted.next != null) {
            ListNode pDeletedNext = pDeleted.next;
            pDeleted.val = pDeletedNext.val;
            pDeleted.next = pDeletedNext.next;
        } else {
            ListNode node = pHead;
            while (node.next != pDeleted) {
                node = node.next;
            }
            node.next = null;
        }
        return pHead;
    }
}

```

## 19. 删除链表中重复的节点
```java
package 删除链表中重复的节点;


public class Solution {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 1->2->3->3->4->4->5 处理后为 1->2->5
     *
     * head ->1->2->3->3->4->4->5
     */
    public ListNode deleteDuplication(ListNode pHead) {
        // 头节点为空，或者只有一个头节点，直接返回
        if (pHead == null || pHead.next == null)
            return pHead;

        // 头节点的头节点，防止头节点被删除
        ListNode head = new ListNode(-1);
        head.next = pHead;

        // 前一个已处理的节点
        ListNode preNode = head;
        // 当前处理的节点
        ListNode curNode = pHead;
        // 下一个处理的节点
        ListNode nextNode;

        while (curNode != null && curNode.next != null) {
            nextNode = curNode.next;
            // 发现了重复的
            if (curNode.val == nextNode.val) {
                // 直到找到不重复的
                while (nextNode != null && curNode.val == nextNode.val) {
                    nextNode = nextNode.next;
                }
                // 删除重复节点
                preNode.next = nextNode;
                curNode = nextNode;
            } else {
                preNode = curNode;
                curNode = curNode.next;
            }
        }

        return head.next;
    }
}
```

## 20. 正则表达式匹配
```java
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
```

## 21. 表示数值的字符串
```java
package 表示数值的字符串;

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

```

## 22. 调整数组顺序使奇数位于偶数前面
```java
package 调整数组顺序使奇数位于偶数前面;

public class Solution {
    /**
     * 思路正确，注意改变了奇数之间和偶数之间的相对顺序
     */
    public void reOrderArray1(int[] array) {
        if (array == null || array.length == 0)
            return;

        // oddIndex 用于定位奇数，evenIndex 用于定位偶数
        int oddIndex = 0, evenIndex = array.length - 1;
        while (oddIndex < evenIndex) {
            // oddIndex 直到找到偶数用于下次的交换
            while (oddIndex < evenIndex && !isEven(array[oddIndex]))
                oddIndex++;

            // evenIndex 直到找到奇数用于下次的交换
            while (oddIndex < evenIndex && isEven(array[evenIndex]))
                evenIndex--;

            // oddIndex 指向了偶数，evenIndex 指向了奇数，交换
            if (oddIndex < evenIndex) {
                int tmp = array[oddIndex];
                array[oddIndex] = array[evenIndex];
                array[evenIndex] = tmp;
            }
        }
    }

    /**
     * 检查是否是偶数
     */
    private boolean isEven(int number) {
        return (number & 1) == 0;
    }

    /**
     * 类似冒泡排序的非法，相邻两个数之间比较
     */
    public void reOrderArray(int[] array) {
        if (array == null || array.length == 0)
            return;

        for (int i = 0; i < array.length - 1; i++) // 最多比较 n-1 趟
            for (int j = 0; j < array.length - i - 1; j++) {
                // 如果偶数在前面奇数在后面，交换
                if (isEven(array[j]) && !isEven(array[j + 1])) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
    }
}
```

## 23. 链表中倒数第k个结点
```java
package 链表中倒数第k个结点;


public class Solution {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 快慢指针法
     */
    public ListNode FindKthToTail(ListNode head, int k) {

        if (head == null || k <= 0)
            return null;

        ListNode fast = head;

        // fast 先走 k 步
        for (int i = 0; i < k; i++) {
            if (fast == null)   // 链表的长度小于 k
                return null;
            fast = fast.next;
        }

        ListNode slow = head;
        // fast 和 slow 同时出发
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
```

## 24. 链表中环的入口结点
```java
package 链表中环的入口结点;


public class Solution {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 检测链表是否存在环，如果存在则返回环中的一个节点
     */
    private ListNode findMeetLoopNode(ListNode pHead) {
        ListNode slow = pHead, fast = pHead.next;
        while (slow != null && fast != null && fast.next != null) {
            if (fast == slow) {
                return fast;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return null;
    }

    /**
     * 快慢指针方法
     * 1、先利用快慢指针判断是否存在环，如果不存在，则直接返回 null
     * 2、如果链表中环有n个结点，指针 P1 在链表上向前移动n步，然后两个指针以相同的速度向前移动。
     *  当第二个指针指向环的入口结点时，第一个指针已经围绕着环走了一圈又回到了入口结点。
     *  所以首先要得到环中结点的数目。
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null)
            return null;

        ListNode loopNode = findMeetLoopNode(pHead);
        if (loopNode == null)   // 不存在环
            return null;

        // 统计环的长度
        ListNode node1 = loopNode;
        int loopCount = 1;
        while (node1.next != loopNode) {
            node1 = node1.next;
            loopCount++;
        }

        // 两个指针从头开始，node1先走 loopCount，再同时走，直到相遇
        node1 = pHead;
        ListNode node2 = pHead;
        for (int i = 0; i < loopCount; i++)
            node1 = node1.next;

        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }

        // 此时的相遇点即为环入口点
        return node1;
    }
}
```

## 25. 反转链表
```java
package 反转链表;


public class Solution {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 使用三个指针，分别 指向当前处理的即诶单，前一个节点和后一个节点，防止链表断裂
     */
    public ListNode ReverseList(ListNode head) {

        ListNode reversedHead = null;

        ListNode preNode = null;
        ListNode curNode = head;
        ListNode nextNode = null;

        while (curNode != null) {
            nextNode = curNode.next;

            if (nextNode == null)
                reversedHead = curNode;

            // 转换next地址，注意链表会断开
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return reversedHead;
    }
}
```

## 26. 合并两个排序的链表
```java
package 合并两个排序的链表;


public class Solution {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 递归方法
     */
    public ListNode Merge1(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val <= l2.val) {
            // l1 的第一个节点小，则头节点为 l1，递归遍历 l1.next 和 l2
            l1.next = Merge1(l1.next, l2);
            return l1;
        } else {
            l2.next = Merge1(l1, l2.next);
            return l2;
        }
    }

    /**
     * 非递归法，逐一比较就好
     */
    public ListNode Merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 添加临时结点，方便处理头结点
        ListNode head = new ListNode(-1);
        ListNode cur = head;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        // 如果 l1 剩余，直接拼接
        if (l1 != null)
            cur.next = l1;

        if (l2 != null)
            cur.next = l2;

        // 返回头节点
        return head.next;
    }
}
```

## 27. 树的子结构
```java
package 树的子结构;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class Solution {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /**
     * 递归法
     * 第一步：在树 A 中找到与 B 的根节点一样的节点 R （递归过程查找根节点，左右子树）
     * 第二步：从树 A 的 R 节点开始依次比较对应的节点是否都相等，直到树 B 为 null
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null)
            return false;

        return doesTree1HasTree2(root1, root2) ||   // 从根节点比较
                HasSubtree(root1.left, root2) ||    // 根节点不相等，递归检测左子树
                HasSubtree(root1.right, root2);     // 根节点和左子树不相等，递归检测右子树
    }

    /**
     * 递归遍历比较从树 A 的 R 节点开始依次比较对应的节点是否都相等，直到树 B 为 null
     */
    private boolean doesTree1HasTree2(TreeNode root1, TreeNode root2) {
        if (root2 == null)  // 如果Tree2已经遍历完了都能对应的上，返回true
            return true;

        if (root1 == null)  // 如果Tree2还没有遍历完，Tree1却遍历完了。返回false
            return false;

        // 如果其中有一个点没有对应上，返回false
        if (root1.val != root2.val)
            return false;

        // 根节点的 val 相等，则比较左右节点
        return doesTree1HasTree2(root1.left, root2.left) && doesTree1HasTree2(root1.right, root2.right);
    }
}
```

## 28. 二叉树的镜像
```java
package 二叉树的镜像;

import java.util.Stack;

/**
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 */
public class Solution {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /**
     * 递归方式实现
     */
    public void Mirror1(TreeNode root) {
        if (root == null)
            return;
        // 如果 root 节点没有左右子树，直接返回
        if (root.left == null && root.right == null)
            return;

        // 有左子树或右子树，进行交换
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        // 递归交换左右子树
        if (root.left != null)
            Mirror1(root.left);

        if (root.right != null)
            Mirror1(root.right);

    }

    /**
     * 非递归的方式实现，用栈模拟递归
     */
    public void Mirror(TreeNode root) {
        if (root == null)
            return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (stack.size() > 0) {
            TreeNode node = stack.pop();
            // 存在左节点或右节点，则交换
            if (node.left != null || node.right != null) {
                TreeNode tmp = node.left;
                node.left = node.right;
                node.right = tmp;
            }

            if (node.left != null)
                stack.push(node.left);

            if (node.right != null)
                stack.push(node.right);
        }
    }
}
```

## 29. 对称的二叉树
```java
package 对称的二叉树;

/**
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class Solution {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null)
            return true;

        return helper(pRoot.left, pRoot.right);
    }

    private boolean helper(TreeNode node1, TreeNode node2) {
        // 两边都为 null，则比较完成，对称
        if (node1 == null && node2 == null)
            return true;

        // node1 和 node2 又一个不为 null，不对称
        if (node1 == null || node2 == null)
            return false;

        // 都不为 null，比较 val 是否相等
        if (node1.val == node2.val)
            // 值相等，比较对于的左右子树是否对称
            return helper(node1.left, node2.right) && helper(node1.right, node2.left);
        return false;
    }
}
```

## 30. 顺时针打印矩阵
```java
package 顺时针打印矩阵;

import java.util.ArrayList;

public class Solution {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();

        if (matrix == null)
            return result;

        int rows = matrix.length;
        int cols = matrix[0].length;

        // 计算打印的圈数
        int circles = ((rows > cols ? cols : rows) - 1) / 2 + 1;

        for (int i = 0; i < circles; i++) {
            // 从左往右的每一行，行为 i，变化的是列，从 i 开始
            for (int j = i; j < cols - i; j++)
                result.add(matrix[i][j]);

            // 从上往下的每一列，列为 cols-i-1，变化的是行，行从 i+1 开始
            for (int j = i + 1; j < rows - i; j++)
                result.add(matrix[j][cols - i - 1]);

            // 从右往左的每一行，行为 rows-i-1，变化的是列，从 cols-i-2开始，到 i 列
            // 注意此时的行为 rows-i-1，要不等于已经处理过的行 i（从左往右）
            if (rows - i - 1 != i)
                for (int j = cols - i - 2; j >= i; j--)
                    result.add(matrix[rows - i - 1][j]);

            // 从下往上的每一列，列为 i，变化的是行，从 rows-i-2 开始，到i+1列
            // 注意此时的列为 i，要不等于已经处理过的 cols-i-1（从上往下）
            if (i != cols-i-1)
                for (int j = rows - i - 2; j >= i + 1; j--)
                    result.add(matrix[j][i]);
        }
        return result;
    }
}
```

## 31. 包含min函数的栈
```java
package 包含min函数的栈;

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
```

## 32. 栈的压入弹出序列
```java
package 栈的压入弹出序列;

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
```

## 33. 从上往下打印二叉树
```java
package 从上往下打印二叉树;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 二叉树的层次遍历
 */
public class Solution {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 队列实现，最直接的方式
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();

        if (root == null)
            return result;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.val);
            if (node.left != null)
                queue.push(node.left);

            if (node.right != null)
                queue.push(node.right);
        }
        return result;
    }

    /**
     * 栈模拟递归实现层次遍历
     */
    public ArrayList<Integer> PrintFromTopToBottom1(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();

        if (root == null)
            return result;

        ArrayList<TreeNode> cur_level = new ArrayList<>();
        cur_level.add(root);

        while (!cur_level.isEmpty()) {
            // 当前层的结果
            ArrayList<Integer> cur_result = new ArrayList<>();
            // 下一层的节点
            ArrayList<TreeNode> next_level = new ArrayList<>();

            for (TreeNode node : cur_level) {
                cur_result.add(node.val);
                if (node.left != null)
                    next_level.add(node.left);
                if (node.right != null)
                    next_level.add(node.right);
            }
            cur_level = next_level;
            result.addAll(cur_result);
        }
        return result;
    }

    /**
     * 递归的方式实现
     */
    public ArrayList<Integer> PrintFromTopToBottom2(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null)
            return new ArrayList<Integer>();

        bfs(result, root, 0);

        ArrayList<Integer> results = new ArrayList<>();

        for (ArrayList<Integer> level_results : result)
            results.addAll(level_results);
        return results;
    }

    private void bfs(ArrayList<ArrayList<Integer>> result, TreeNode root, int level) {
        if (root == null) return;

        // 遍历新的层
        if (level == result.size()) {
            ArrayList<Integer> cur_result = new ArrayList<>();
            cur_result.add(root.val);
            result.add(cur_result);
        } else {    // 遍历已经遍历过的层
            ArrayList<Integer> level_result = result.get(level);
            level_result.add(root.val);
            result.set(level, level_result);
        }
        // 遍历下一层
        bfs(result, root.left, level + 1);
        bfs(result, root.right, level + 1);
    }
}
```

