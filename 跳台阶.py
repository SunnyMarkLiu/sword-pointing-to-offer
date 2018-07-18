#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/18 下午8:56
"""

class Solution:

    def jumpFloor(self, n):
        """
        斐波那契数列的应用️

        跳上 n 阶台阶的跳法 = 第一次跳 1 阶剩下的 n-1 阶台阶的跳法 + 第一次跳 2 阶剩下的 n-2 阶台阶的跳法

        f(n) = f(n-1) + f(n-2),
        f(0) = 0
        f(1) = 1
        f(2) = 2
        """
        if n <= 0:
            return 0
        if n == 1:
            return 1

        if n == 2:
            return 2

        sum_n_2 = 1
        sum_n_1 = 2
        sum_n = 0

        for _ in range(n - 2):
            sum_n = sum_n_2 + sum_n_1
            sum_n_2 = sum_n_1
            sum_n_1 = sum_n

        return sum_n
