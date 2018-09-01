package 数字在排序数组中出现的次数;

/**
 * 统计一个数字在排序数组中出现的次数。
 * [1,2,3,3,3,3], 3
 * return 4
 */
public class Solution {

    /**
     * 直接查找法，时间复杂度 O(n)
     */
    public int GetNumberOfK1(int[] array, int k) {
        if (array == null || array.length == 0)
            return 0;

        // 首先找到数字的开始位置
        int i = 0;
        while (i < array.length && array[i] != k)
            i++;

        if (i == array.length)
            return 0;

        int count = 1;
        while (i + 1 < array.length && array[i] == array[i + 1]) {
            i++;
            count++;
        }
        return count;
    }

    /**
     * 由于是有序数组，又涉及查找，联想到二分查找，时间复杂度 O(logn)
     * 1、二分查找第一个 K 的位置
     * 2、二分查找最后一个 K 的位置
     * 3、两位置相减即可
     */
    public int GetNumberOfK2(int[] array, int k) {
        if (array == null || array.length == 0)
            return 0;

        int firstK = getFirstK(array, k, 0, array.length - 1);
        int lastK = getLastK(array, k, 0, array.length - 1);

        int count = 0;
        if (firstK != -1 && lastK != -1)
            count = lastK - firstK + 1;

        return count;
    }

    private int getFirstK(int[] array, int k, int start, int end) {
        // -1 表示不存在 k
        if (start > end) return -1;

        int mid = start + (end - start) / 2;
        int midData = array[mid];

        if (midData == k) {
            // 如果此时的中间值和前一个不相等，或者此时的 mid 为第 0 个数，则为第一个k
            if ((mid - 1 >= 0 && midData != array[mid - 1]) || mid == 0) {
                return mid;
            } else {
                // mid 前面的数仍然等于 k
                end = mid - 1;
            }
        } else if (midData > k) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
        return getFirstK(array, k, start, end);
    }

    private int getLastK(int[] array, int k, int start, int end) {
        if (start > end) return -1;

        int mid = start + (end - start) / 2;
        int midData = array[mid];

        if (midData == k) {
            // 如果此时的中间值和后一个不相等，或者此时的 mid 为最后一个数，则为最后一个k
            if ((mid + 1 < array.length && midData != array[mid+1]) || mid == array.length - 1)
                return mid;
            else {
                start = mid + 1;
            }
        } else if (midData < k) {
            start = mid + 1;
        } else {
            end = mid - 1;
        }
        return getLastK(array, k, start, end);
    }

    /**
     * 由于数组是排序的整数数组，转换思路：想寻找 k-0.5 和 k+0.5 的位置，然后相减即可，查找数仍然采用二分搜索
     */
    public int GetNumberOfK(int[] array, int k) {
        if (array == null)
            return 0;

        return biSearch(array, k + 0.5) - biSearch(array, k - 0.5);
    }

    /**
     * 二分查找 value 应该插入在 array 的位置
     */
    private int biSearch(int[] array, double value) {
        int start = 0;
        int end = array.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            // array[mid] 不可能和 value 相等
            if (array[mid] > value) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
