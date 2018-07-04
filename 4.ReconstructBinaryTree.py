#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/4 下午10:43
"""


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    # 返回构造的TreeNode根节点
    def reConstructBinaryTree(self, pre_order, in_order):

        if not pre_order or not in_order:
            return None

        # 根据 pre 创建根节点，注意从头部开始 pop
        root = TreeNode(pre_order.pop(0))

        # 根节点在 in_order 的位置
        root_in_order_index = in_order.index(root.val)

        # 递归创建左子树和右子树
        root.left = self.reConstructBinaryTree(pre_order, in_order[0:root_in_order_index])
        root.right = self.reConstructBinaryTree(pre_order, in_order[root_in_order_index + 1:])

        return root
