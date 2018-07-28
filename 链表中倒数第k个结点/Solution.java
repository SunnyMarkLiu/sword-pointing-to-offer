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