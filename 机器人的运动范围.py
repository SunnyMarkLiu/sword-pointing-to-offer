#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/20 下午10:00
"""


class Solution:
    def movingCount(self, threshold, rows, cols):
        if not threshold or rows <= 0 or cols <= 0:
            return 0

        # 定义一个布尔类型的矩阵用于标示是否被访问过
        visited = [False] * (rows * cols)
        return self.movingCountHelper(threshold, rows, cols, 0, 0, visited)

    def movingCountHelper(self, threshold, rows, cols, row, col, visited):

        count = 0

        if row >= 0 and row < rows and col >= 0 and col < cols and \
                self.sumUnderThreshold(threshold, row, col) and \
                not visited[row * cols + col]:
            # 设置标志位
            visited[row * cols + col] = True

            # 遍历匹配上下左右的节点
            count = 1 + self.movingCountHelper(threshold, rows, cols, row - 1, col, visited) + \
                        self.movingCountHelper(threshold, rows, cols, row + 1, col, visited) + \
                        self.movingCountHelper(threshold, rows, cols, row, col - 1, visited) + \
                        self.movingCountHelper(threshold, rows, cols, row, col + 1, visited)

        return count

    def sumUnderThreshold(self, threshold, row, col):

        sum = 0

        while row > 0:
            sum += row % 10
            row = row // 10

        while col > 0:
            sum += col % 10
            col = col // 10

        return sum <= threshold
