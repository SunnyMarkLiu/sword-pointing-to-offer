#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/4 下午10:59
"""


class Solution:

    def __init__(self):
        self.stack1 = []
        self.stack2 = []

    def push(self, node):
        self.stack1.append(node)

    def pop(self):
        #  stack2 不为空，从尾部 pop
        if self.stack2:
            return self.stack2.pop()

        if not self.stack1:
            return None

        # stack2 为空，将栈 stack1 中所有元素pop，并push进栈 stack2
        while self.stack1:
            self.stack2.append(self.stack1.pop())

        return self.stack2.pop()
