package src.合并两个排序的链表;


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