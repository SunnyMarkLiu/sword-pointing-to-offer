package src.顺时针打印矩阵;

import java.util.ArrayList;

public class Solution {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();

        if (matrix == null)
            return result;

        int rows = matrix.length;
        int cols = matrix[0].length;

        // 计算打印的圈数
        int circles = ((rows > cols ? cols : rows) - 1) / 2 + 1;

        for (int i = 0; i < circles; i++) {
            // 从左往右的每一行，行为 i，变化的是列，从 i 开始
            for (int j = i; j < cols - i; j++)
                result.add(matrix[i][j]);

            // 从上往下的每一列，列为 cols-i-1，变化的是行，行从 i+1 开始
            for (int j = i + 1; j < rows - i; j++)
                result.add(matrix[j][cols - i - 1]);

            // 从右往左的每一行，行为 rows-i-1，变化的是列，从 cols-i-2开始，到 i 列
            // 注意此时的行为 rows-i-1，要不等于已经处理过的行 i（从左往右）
            if (rows - i - 1 != i)
                for (int j = cols - i - 2; j >= i; j--)
                    result.add(matrix[rows - i - 1][j]);

            // 从下往上的每一列，列为 i，变化的是行，从 rows-i-2 开始，到i+1列
            // 注意此时的列为 i，要不等于已经处理过的 cols-i-1（从上往下）
            if (i != cols-i-1)
                for (int j = rows - i - 2; j >= i + 1; j--)
                    result.add(matrix[j][i]);
        }
        return result;
    }
}