#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/22 下午8:25
"""


class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:

    def deleteDuplication(self, pHead):
        """
        1->2->3->3->4->4->5 处理后为 1->2->5

        head ->1->2->3->3->4->4->5


        """
        if not pHead or not pHead.next:
            return pHead

        head = ListNode(-1)
        head.next = pHead

        preNode = head
        curNode = pHead
        nextNode = None

        while not curNode and not curNode.next:
            nextNode = curNode.next

            if curNode.val == nextNode.val:
                while not nextNode and curNode.val == nextNode.val:
                    nextNode = nextNode.next

                preNode.next = nextNode
                curNode = nextNode
            else:
                preNode = curNode
                curNode = curNode.next

        return head.next
