#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/4 下午10:26
"""
# -*- coding:utf-8 -*-
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    # 返回从尾部到头部的列表值序列，例如[1,2,3]
    def printListFromTailToHead(self, listNode):
        """
        从尾部到头部打印，联想到递归的方式
        """
        result = []
        self.help(result, listNode)
        return result

    def help(self, result, node):
        if not node:
            return

        self.help(result, node.next)

        if node:
            result.append(node.val)
