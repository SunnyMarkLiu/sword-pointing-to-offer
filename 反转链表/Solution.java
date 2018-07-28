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