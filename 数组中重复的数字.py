#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/10 下午8:50
"""
class Solution:
    # 这里要特别注意~找到任意重复的一个值并赋值到duplication[0]
    # 函数返回True/False
    def duplicate(self, numbers, duplication):
        # write code here
        number_map = {}
        for number in numbers:
            if number not in number_map:
                number_map[number_map] = 1
            else:
                # duplicated
                duplication[0] = number
                return True

        return False
