package src.最小的K个数;

import java.util.ArrayList;

/**
 * 输入n个整数，找出其中最小的K个数。
 * 例如输入 4,5,1,6,2,7,3,8 这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class Solution {
    /**
     * 利用冒泡排序的思想，时间复杂度为O(n*k)
     * 每一轮排序把剩余数组中最小的一个数字放到前面已排序的后面，只要进行K轮即可。
     */
    public ArrayList<Integer> leastKNumbers(int[] nums, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0 || nums.length < k) {
            return result;
        }

        for (int i = 0; i < k; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                }
            }
            result.add(nums[i]);
        }
        return result;
    }

    /**
     * 堆排序的思想，O(NlogK)
     * 取最小的k个数：最大堆
     * 取最大的k个数：最小堆
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] nums, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0 || nums.length < k) {
            return result;
        }

        // 构建最大堆，堆得大小为 k 个元素
        for (int root = k / 2 - 1; root >= 0; root--)
            heapAdjust(nums, root, k - 1);

        // 交换根节点和最后一个值，同时继续调整堆结构
        // 前k个元素的大顶堆已经构建好了，剩下的就是其余的和大顶堆的最大值比较了
        for (int end = k; end < nums.length; end++) {
            // 如果此时的end元素比最大堆的第一个元素还要小，则把第一个元素替换掉，因为最小的k个数要包含end这个数
            if (nums[end] < nums[0]) {
                int tmp = nums[0];
                nums[0] = nums[end];
                nums[end] = tmp;

                // 同样，需要继续调整以满足堆结构
                heapAdjust(nums, 0, k - 1);
            }
        }

        for (int i = 0; i < k; i++)
            result.add(nums[i]);

        return result;
    }

    /**
     * 从 root 开始调整构建最大堆
     * @param nums  待调整的堆（数组）
     * @param root  待调整的根节点
     * @param end
     */
    private void heapAdjust(int[] nums, int root, int end) {
        while (true) {
            // 获取 root 的左节点的下标
            int child = 2 * root + 1;

            // 以当前 root 为调整起点，调整结束
            if (child > end)
                break;

            // 获取 root 对于的左右节点较大的下标
            if (child + 1 <= end && nums[child] < nums[child + 1]) {
                child++;
            }

            // 如果此时的 root 比其子节点的最大值小，交换
            if (nums[root] < nums[child]) {
                int tmp = nums[root];
                nums[root] = nums[child];
                nums[child] = tmp;
                // 注意此时 root 和 child 的值交换了，此时需要继续调整以满足 child 下标的子树也满足堆结构
                root = child;
            } else {
                break;
            }

        }
    }
}
