#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/20 下午9:03
"""


class Solution:
    def hasPath(self, matrix, rows, cols, strpath):
        if not matrix or not strpath or rows == 0 or cols == 0:
            return False

        # 定义一个布尔类型的矩阵用于标示是否被访问过
        visited = [False] * (rows * cols)

        # 遍历 matrix 的每个元素
        for row in range(rows):
            for col in range(cols):
                if self.hasPathHelper(matrix, rows, cols, strpath, row, col, visited, 0):
                    return True

        return False

    def hasPathHelper(self, matrix, rows, cols, strpath,
                      row, col, visited, next_match_length):

        if next_match_length == len(strpath):
            return True

        # 当前节点（row, col）是否匹配到
        hasPath = False

        if row >= 0 and row < rows and col >= 0 and col < cols and \
            matrix[row * cols + col] == strpath[next_match_length] and \
            not visited[row * cols + col]:

            # 设置标志位
            visited[row * cols + col] = True

            # 匹配下一个字符
            hasPath = self.hasPathHelper(matrix, rows, cols, strpath, row - 1, col, visited, next_match_length + 1) or \
                      self.hasPathHelper(matrix, rows, cols, strpath, row + 1, col, visited, next_match_length + 1) or \
                      self.hasPathHelper(matrix, rows, cols, strpath, row, col - 1, visited, next_match_length + 1) or \
                      self.hasPathHelper(matrix, rows, cols, strpath, row, col + 1, visited, next_match_length + 1)

            # 如果匹配失败，则回溯
            if not hasPath:
                visited[row * cols + col] = False

        return hasPath
