package src.链表中环的入口结点;


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