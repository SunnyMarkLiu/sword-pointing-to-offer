#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/18 下午8:19
"""


class Solution:
    def minNumberInRotateArray(self, rotateArray):

        if not rotateArray or len(rotateArray) == 0:
            return None

        left = 0
        right = len(rotateArray) - 1
        while left < right:
            mid = left + (right - left) // 2
            # 中间的大于右边的，则左边的有序
            if rotateArray[mid] > rotateArray[right]:
                left = mid + 1  # mid 肯定不是最小值
            else:   # mid <= right，右半边为有序数组，则最小值在左边
                right = mid     # mid 可能为最小值

        return rotateArray[right]


print(Solution().minNumberInRotateArray([1,0,1,1,1]))
