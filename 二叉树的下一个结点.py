#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
"""

class TreeLinkNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
        self.next = None

class Solution:

    def GetNext(self, pNode):
        if not pNode:
            return None

        # 如果该节点有右子树, 遍历获取右子树的最左节点
        if pNode.right:
            pNode = pNode.right
            while pNode.left:
                pNode = pNode.left
            return pNode

        # 如果该节点没有右子树，两种情况：
        #    该节点处于左边，则父节点即为下一个节点
        #    该节点处于右边，注意没有右子树了，则向上找父节点直到找到 pNode 是 parent 的左节点
        parent = pNode.next
        if parent and parent.left == pNode:
            return parent

        while parent and parent.right == pNode:
            pNode = parent
            parent = parent.next

        return parent
