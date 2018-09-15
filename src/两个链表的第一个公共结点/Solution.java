package src.两个链表的第一个公共结点;


public class Solution {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 找出2个链表的长度，然后让长的先走两个链表的长度差，然后再一起走
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null)
            return null;

        // 获取链表的长度
        int pLen1 = 0;
        int pLen2 = 0;
        ListNode tmp1 = pHead1;
        ListNode tmp2 = pHead2;

        while (tmp1 != null) {
            pLen1++;
            tmp1 = tmp1.next;
        }

        while (tmp2 != null) {
            pLen2++;
            tmp2 = tmp2.next;
        }

        // 比较那个链表长，tmp1 指向较长的链表
        if (pLen1 > pLen2) {
            tmp1 = pHead1;
            tmp2 = pHead2;
        } else {
            tmp1 = pHead2;
            tmp2 = pHead1;
        }

        // 较长的链表先走链表长度的差的步数
        int deltaLen = Math.abs(pLen1 - pLen2);
        for (int i=0; i < deltaLen; i++)
            tmp1 = tmp1.next;

        // 两者在同时走，直到相遇
        while (tmp1 != tmp2) {
            tmp1 = tmp1.next;
            tmp2 = tmp2.next;
        }
        return tmp1;
    }
}