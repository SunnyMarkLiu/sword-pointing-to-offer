package 二维数组中的查找;

public class Solution {

    /***
     * 从左下角元素往上查找，右边元素是比这个元素大，上边是的元素比这个元素小
     * 比 target 大，则右移，比target小，则上移.
     */
    public boolean Find(int target, int [][] array) {
        if (null == array || array.length == 0)
            return false;

        int row = array.length - 1;     // 行
        int col = 0;  // 列

        while (row >= 0 && col < array[0].length) {
            if (array[row][col] == target)
                return true;

            // 没找到，则从左下角元素往上查找
            if (array[row][col] > target)
                row -= 1;
            else
                col += 1;
        }
        return false;
    }
}
