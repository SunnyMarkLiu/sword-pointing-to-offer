package src.机器人的运动范围;


public class Solution {

    /**
     * 回溯法
     */
    public int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows <= 0 || cols <= 0)
            return 0;

        // 定义一个布尔类型的矩阵用于标示是否被访问过
        boolean[] visited = new boolean[rows * cols];
        return movingCountHelper(threshold, rows, cols, 0, 0, visited);
    }

    private int movingCountHelper(int threshold, int rows, int cols, int row, int col, boolean[] visited) {

        int count = 0;

        if (row >=0 && row < rows && col >=0 && col < cols &&   // 边界检查
                sumUnderThreshold(threshold, row, col) &&
                !visited[row * cols + col]) {   // 匹配的节点没有被访问过
            // 设置标志位
            visited[row * cols + col] = true;

            // 递归遍历上下左右节点
            count = 1 + movingCountHelper(threshold, rows, cols, row - 1, col, visited) +
                        movingCountHelper(threshold, rows, cols, row + 1, col, visited) +
                        movingCountHelper(threshold, rows, cols, row, col - 1, visited) +
                        movingCountHelper(threshold, rows, cols, row, col + 1, visited);
        }
        return count;
    }

    private boolean sumUnderThreshold(int threshold, int row, int col) {

        int sum = 0;
        while (row > 0) {
            sum += row % 10;
            row = row / 10;
        }

        while (col > 0) {
            sum += col % 10;
            col = col / 10;
        }

        return sum <= threshold;
    }
}