#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/18 下午5:31
"""


class Solution:

    def __init__(self):
        self.result_tmp = {}    # 缓存重复计算的中间结果

    def Fibonacci1(self, n):
        """
        递归的方式实现，注意全局遍历保存中间结果，空间换时间
        """
        if n <= 0:
            return 0
        if n == 1:
            return 1

        if (n-1) in self.result_tmp:
            n_1 = self.result_tmp[n-1]
        else:
            n_1 = self.Fibonacci1(n-1)
            self.result_tmp[n-1] = n_1

        if (n-2) in self.result_tmp:
            n_2 = self.result_tmp[n-2]
        else:
            n_2 = self.Fibonacci1(n-2)
            self.result_tmp[n-2] = n_2

        return n_1 + n_2

    def Fibonacci(self, n):
        """
        更直接、实用高效的方法，利用斐波拉契数列的方法，依次自下往上计算，只保存 n-1 和 n-2 的值
        """
        if n <= 0:
            return 0

        if n == 1:
            return 1

        sum_n_2 = 0
        sum_n_1 = 1
        sum_n = None
        for _ in range(n - 1):
            sum_n = sum_n_2 + sum_n_1
            sum_n_2 = sum_n_1
            sum_n_1 = sum_n

        return sum_n



print(Solution().Fibonacci(100))
