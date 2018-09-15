package src.删除链表中重复的节点;


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