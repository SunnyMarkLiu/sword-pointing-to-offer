#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/18 下午8:56
"""


# -*- coding:utf-8 -*-
class Solution:
    def jumpFloorII(self, number):
        if number <= 0:
            return 0

        return 2 ** (number - 1)
