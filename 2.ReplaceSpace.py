#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/6/28 下午9:56
"""

class Solution:
    # s 源字符串
    def replaceSpace(self, s):
        # 空间换时间的方式
        r = ''
        for i, c in enumerate(s):
            if c == ' ':
                r += '%20'
            else:
                r += s[i]

        return r

print Solution().replaceSpace('12 3')
