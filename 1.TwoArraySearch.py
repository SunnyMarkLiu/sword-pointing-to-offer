#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/6/28 下午9:28
"""

class Solution:
    # array 二维列表
    def Find(self, target, array):
        """
        从左下角元素往上查找，右边元素是比这个元素大，上边是的元素比这个元素小
        比 target 大，则右移，比target小，则上移.
        """
        row = len(array) - 1  # 行
        col = 0           # 列

        while row >= 0 and col < len(array[0]):
            if array[row][col] == target:
                return True
            elif array[row][col] > target:
                row -= 1
            else:
                col += 1

        return False
