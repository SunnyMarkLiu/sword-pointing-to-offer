#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/20 下午11:01
"""


class Solution:
    def NumberOf1(self, n):
        count = 0

        if n < 0:
            n = n & 0xffffffff

        while n != 0:
            count += 1
            n = (n - 1) & n

        return count
