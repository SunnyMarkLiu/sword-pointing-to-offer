package src.复杂链表的复制;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
 * 另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class Solution {

    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    /**
     * 复制的时候用 Map 保存原始 node 和新复制 node 的映射关系
     */
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null)
            return null;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();

        RandomListNode newHead = new RandomListNode(pHead.label);
        map.put(pHead, newHead);

        RandomListNode oldTmp = pHead.next;
        RandomListNode newTmp = newHead;

        while (oldTmp != null) {
            RandomListNode newNode = new RandomListNode(oldTmp.label);
            map.put(oldTmp, newNode);
            newTmp.next = newNode;
            newTmp = newTmp.next;
            oldTmp = oldTmp.next;
        }

        // 更新 random 指针
        oldTmp = pHead;
        newTmp = newHead;
        while (oldTmp != null) {
            newTmp.random = map.get(oldTmp.random);
            newTmp = newTmp.next;
            oldTmp = oldTmp.next;
        }
        return newHead;
    }
}