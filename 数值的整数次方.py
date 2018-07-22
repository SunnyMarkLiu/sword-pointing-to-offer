#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/22 下午6:09
"""


class Solution:

    def Power(self, base, exponent):

        # base 是否为 0
        if abs(base) < 1.0e-9:
            if exponent <= 0:
                raise ValueError

        absexponent = exponent
        if exponent < 0:
            absexponent = -exponent

        result = self.getPower(base, absexponent)

        if exponent < 0:
            result = 1 / result

        return result

    def getPower(self, base, absexponent):
        if absexponent == 0:
            return 1
        if absexponent == 1:
            return base

        result = self.getPower(base, absexponent >> 1)
        result *= result

        if (absexponent & 1) == 1:
            result *= base

        return result
