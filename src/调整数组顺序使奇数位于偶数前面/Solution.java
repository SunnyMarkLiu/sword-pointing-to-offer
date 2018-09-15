package src.调整数组顺序使奇数位于偶数前面;

public class Solution {
    /**
     * 思路正确，注意改变了奇数之间和偶数之间的相对顺序
     */
    public void reOrderArray1(int[] array) {
        if (array == null || array.length == 0)
            return;

        // oddIndex 用于定位奇数，evenIndex 用于定位偶数
        int oddIndex = 0, evenIndex = array.length - 1;
        while (oddIndex < evenIndex) {
            // oddIndex 直到找到偶数用于下次的交换
            while (oddIndex < evenIndex && !isEven(array[oddIndex]))
                oddIndex++;

            // evenIndex 直到找到奇数用于下次的交换
            while (oddIndex < evenIndex && isEven(array[evenIndex]))
                evenIndex--;

            // oddIndex 指向了偶数，evenIndex 指向了奇数，交换
            if (oddIndex < evenIndex) {
                int tmp = array[oddIndex];
                array[oddIndex] = array[evenIndex];
                array[evenIndex] = tmp;
            }
        }
    }

    /**
     * 检查是否是偶数
     */
    private boolean isEven(int number) {
        return (number & 1) == 0;
    }

    /**
     * 类似冒泡排序的非法，相邻两个数之间比较
     */
    public void reOrderArray(int[] array) {
        if (array == null || array.length == 0)
            return;

        for (int i = 0; i < array.length - 1; i++) // 最多比较 n-1 趟
            for (int j = 0; j < array.length - i - 1; j++) {
                // 如果偶数在前面奇数在后面，交换
                if (isEven(array[j]) && !isEven(array[j + 1])) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
    }
}