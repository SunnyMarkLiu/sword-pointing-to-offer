package src.矩阵中的路径;

public class Solution {
    /**
     * 回溯法
     */
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || str == null || rows == 0 || cols == 0)
            return false;

        // 定义一个布尔类型的矩阵用于标示是否被访问过
        boolean[] visited = new boolean[rows * cols];

        // 遍历 matrix 的所有元素
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // 从矩阵的当前元素开始遍历
                if (hasPathHelper(matrix, rows, cols, str, row, col, visited, 0))
                    return true;
            }
        }
        return false;
    }

    /**
     * 递归从四个方向进行检测
     *
     * next_match_length 下一个待匹配成功的长度
     */
    private boolean hasPathHelper(char[] matrix, int rows, int cols, char[] str,
                                  int row, int col, boolean[] visited, int next_match_length) {

        // 匹配完最后一个字符
        if (next_match_length == str.length)
            return true;

        // 当前节点（row, col）是否匹配到
        boolean hasPath = false;

        // 匹配到
        if (row >= 0 && row < rows && col >= 0 && col < cols && // 边界检查
            matrix[row * cols + col] == str[next_match_length] &&  // 匹配成功
            !visited[row * cols + col]) {  // 匹配的节点没有被访问过

            // 设置标志位
            visited[row * cols + col] = true;

            // 递归上下左右检测，匹配成功长度+1
            hasPath = hasPathHelper(matrix, rows, cols, str, row - 1, col, visited, next_match_length + 1) ||
                      hasPathHelper(matrix, rows, cols, str, row + 1, col, visited, next_match_length + 1) ||
                      hasPathHelper(matrix, rows, cols, str, row, col - 1, visited, next_match_length + 1) ||
                      hasPathHelper(matrix, rows, cols, str, row, col + 1, visited, next_match_length + 1);

            // 如果当前节点（row, col）的四周都没匹配到，则回溯
            if (!hasPath)
                visited[row * cols + col] = false;  // 取消标志位
        }
        return hasPath;
    }
}