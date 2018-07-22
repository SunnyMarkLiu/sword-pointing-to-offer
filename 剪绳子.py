#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/22 下午4:31
"""


class Solution:

    def maxProductAfterCutting(self, length):
        if length < 2:
            return 0

        if length == 2:
            return 1

        if length == 3:
            return 2

        # 子问题的最优解存储在f数组中，数组中的第i个元素表示把长度为i的绳子剪成若干段后各段长度乘积的最大值。
        products = [0] * (length + 1)
        # 第二层循环从1开始，保证了至少剪一刀，此处的 producs 初始化的注意可以不剪情况下的最大值
        products[0] = 0
        products[1] = 1
        products[2] = 2
        products[3] = 3

        for i in range(4, length + 1):
            max_product = 0
            for j in range(1, i // 2 + 1):
                product = products[j] * products[i - j]
                if product > max_product:
                    max_product = product

            products[i] = max_product

        return products[length]
