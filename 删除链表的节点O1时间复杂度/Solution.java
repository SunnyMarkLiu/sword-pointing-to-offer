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
