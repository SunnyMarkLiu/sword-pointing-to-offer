#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/18 下午4:29
"""

class Solution:

    def __init__(self):
        self.queue1 = []
        self.queue2 = []

    def push(self, node):
        # 两个队列都为空，任意选择一个 push 数据
        if not self.queue1 and not self.queue2:
            self.queue1.append(node)

        elif self.queue1:   # queue1 不为空，push
            self.queue1.append(node)

        else:
            self.queue2.append(node)

    def pop(self):
        # 找到不为空的队列
        if self.queue1 and not self.queue2: # queue1 不为空
            not_empty_queue = self.queue1
            empty_queue = self.queue2
        elif self.queue2 and not self.queue1: # queue2 不为空
            not_empty_queue = self.queue2
            empty_queue = self.queue1
        else:  # queue1, queue2 都为空，注意不存在queue1，queue2都不为空的情况
            return None

        # 将非空队列的元素 pop 并 push 到空队列中，留下最后一个元素直接 pop
        while len(not_empty_queue) > 1:
            empty_queue.append(not_empty_queue.pop(0))

        return not_empty_queue.pop(0)


stack = Solution()
stack.push(1)
stack.push(2)
stack.push(3)

print(stack.pop())
stack.push(4)
print(stack.pop())
print(stack.pop())
print(stack.pop())
print(stack.pop())
stack.push(3)
print(stack.pop())
