package src.旋转数组的最小数字;

public class Solution {
    public int minNumberInRotateArray(int [] array) {
        if (null == array || array.length == 0)
            throw new NullPointerException("empty array error");

        int left = 0;
        int right = array.length - 1;
        int mid = 0;

        while (left < right) {
            mid = left + (right - left) / 2;

            // 如果中间的值大于最右边的值，则左边是有序的
            if (array[mid] > array[right]) {
                left = mid + 1;  // 中间值不可能为最小值
            } else {    // mid <= right，则右边是有序的，最小值在左边
                right = mid;
            }
        }

        return array[right];
    }
}
