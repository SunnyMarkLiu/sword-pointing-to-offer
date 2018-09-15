package src;

import java.util.Arrays;

/**
 * 排序算法
 * 排序方法	        平均时间	    最好时间	    最坏时间
 * 桶排序(不稳定)	    O(n)	    O(n)	    O(n)
 * 基数排序(稳定) 	O(n)	    O(n)	    O(n)
 * 归并排序(稳定)	    O(nlogn)	O(nlogn)	O(nlogn)
 * 快速排序(不稳定)	O(nlogn)	O(nlogn)	O(n^2)
 * 堆排序(不稳定)	    O(nlogn)	O(nlogn)	O(nlogn)
 * <p>
 * 冒泡排序(稳定) 	O(n^2)	    O(n)	    O(n^2)
 * <p>
 * 直接插入排序(稳定)	O(n^2)	    O(n)	    O(n^2)
 * 希尔排序(不稳定)	O(n^1.25)
 * 选择排序(不稳定)	O(n^2)	    O(n^2)	    O(n^2)
 */
public class Sort {
    public static void main(String[] args) {

        int[] nums = {49, 38, 65, 97, 76, 13, 27, 78, 34, 12, 64, 5, 4, 62};
        System.out.println("=============原始数组=============");
        System.out.println(Arrays.toString(nums));
        heapSort(nums);
        System.out.println("排序后：" + Arrays.toString(nums));
    }

    /**
     * 直接插入排序
     * 基本思想：每一步将一个待排序的记录，插入到前面已经排好序的有序序列中去，直到插完所有元素为止。
     */
    public static void insertSort(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;

        // 从第一个数开始到 len - 1，默认第一个数为有序的
        for (int i = 1; i < nums.length; i++) {
            int j = i;
            // 当前的数比前面排序好的数要小，第j个数插入到j-1前面
            while (j > 0 && nums[j] < nums[j - 1]) {
                int tmp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = tmp;
                j--;
            }
        }
    }

    /**
     * 希尔排序
     * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
     * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，
     * 整个文件恰被分成一组，算法便终止。
     */
    public static void shellSort(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;

        int gap = nums.length / 2;
        while (gap > 0) {

            // 从第gap个元素，逐个对其所在组进行直接插入排序操作
            for (int i = gap; i < nums.length; i++) {
                int j = i;
                while (j - gap >= 0 && nums[j] < nums[j - gap]) {
                    int tmp = nums[j];
                    nums[j] = nums[j - gap];
                    nums[j - gap] = tmp;
                    j -= gap;
                }
            }
            gap /= 2;
        }
    }

    /**
     * 简单选择排序
     * 初始化默认第一个数为最小值，然后在剩下的数组里找出比当前最小值还要小（循环找到最小）的数，如果找到了和第一个数交换，
     * 此时第一个位置确认为最小的数，接着找第二小的数拍第二位置，如此循环到倒数第二个数和最后一个数比较为止。
     */
    public static void selectSort(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;

        // i: [0, n-2]
        for (int i = 0; i < nums.length - 1; i++) {
            // 记录当前最小值的下标
            int minIndex = i;

            // j: [i+1, n-1]
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex])
                    minIndex = j;
            }

            // 找到了比当前 i 还小的最小值，则交换
            if (minIndex != i) {
                int tmp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = tmp;
            }

        }
    }

    /**
     * 堆排序
     * 数组从小到大排序，构建大根堆；
     * 数组从大到小排序，构建小根堆。
     * 1、初始化将无序数组构建大根堆，
     * 2、将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
     * 3、重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，
     *   反复执行调整+交换步骤，直到整个序列有序。
     */
    public static void heapSort(int[] nums) {
        // 初始化创建最大堆
        // 从最后一个非叶子结点开始调整最大堆，叶子结点已经满足堆的要求
        int beginIndex = nums.length / 2 - 1;
        for (int i=beginIndex; i >= 0; i--)
            maxHeapify(nums, i, nums.length - 1);

        // 堆排序，将最大（根）的和的最后一个交换，堆大小减一
        for (int end = nums.length - 1; end >= 0; end--) {
            // 交换堆的根节点和最有一个值，使得最大值位于最后
            int tmp = nums[0];
            nums[0] = nums[end];
            nums[end] = tmp;

            // 交换之后继续调整堆的结构
            maxHeapify(nums, 0, end - 1);
        }
    }

    private static void maxHeapify(int[] nums, int start, int end) {
        int root = start;
        while (true) {
            // 从root开始调整最大堆
            int child = 2 * root + 1; // 左节点
            if (child > end)
                break;

            // 找出两个child中较大的一个
            if (child <= end - 1 && nums[child] < nums[child+1])
                child += 1;

            if (nums[root] < nums[child]) {
                // 最大堆的堆顶小于较大的child, 交换顺序
                int tmp = nums[root];
                nums[root] = nums[child];
                nums[child] = tmp;
                // 正在调整的节点设置为root, 调整完根结点后需要调整子结点
                root = child;
            } else {
                // 无需调整的时候, 退出
                break;
            }
        }
    }

    /**
     * 冒泡排序
     * 基本思想：在要排序的一组数中，对当前还未排好序的范围内的全部数，
     * 自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。
     * 即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
     */
    public static void bubbleSort(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;

        for (int i = 0; i < nums.length - 1; i++) {

            // 记录剩下的是否为有序，如果有序直接 break
            boolean sorted = true;

            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                    sorted = false;
                }
            }

            if (sorted)
                break;
        }
    }

    /**
     * 归并排序
     * 首先归并排序使用了二分法，归根到底的思想还是分而治之。拿到一个长数组，
     * 将其不停的分为左边和右边两份，然后以此递归分下去。然后再将她们按照两个有序数组的样子合并起来
     */
    public static void mergeSort(int[] nums, int left, int right) {
        if (nums == null || nums.length <= 1 || left >= right)
            return;

        // 分而, 找出中间索引
        int mid = left + (right - left) / 2;

        //对左边数组进行递归
        mergeSort(nums, left, mid);
        //对右边数组进行递归
        mergeSort(nums, mid + 1, right);
        // 将排序结果进行合并
        merge(nums, left, mid, right);
    }

    private static void merge(int[] nums, int left, int mid, int right) {
        int[] tmpNums = new int[nums.length];
        int tmp = left; // 用于最后复制到原数组中
        int tmp_i = left;
        int left_i = left;
        int right_i = mid + 1;

        while (left_i <= mid && right_i <= right) {
            if (nums[left_i] < nums[right_i]) {
                tmpNums[tmp_i++] = nums[left_i++];
            } else {
                tmpNums[tmp_i++] = nums[right_i++];
            }
        }

        // 如果左边剩余，则拼接
        while (left_i <= mid)
            tmpNums[tmp_i++] = nums[left_i++];

        // 如果右边剩余，则拼接
        while (right_i <= right)
            tmpNums[tmp_i++] = nums[right_i++];

        // tmp 覆盖原数组
        while (tmp <= right) {
            nums[tmp] = tmpNums[tmp];
            tmp++;
        }
    }

    /**
     * 快速排序
     * 选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，
     * 将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,
     * 此时基准元素在其排好序后的正确位置,然后再用同样的方法递归地排序划分的两部分。
     */
    public static void quickSort(int[] arr) {
        qsort(arr, 0, arr.length - 1);
    }

    private static void qsort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);        //将数组分为两部分
            qsort(arr, low, pivot - 1);                   //递归排序左子数组
            qsort(arr, pivot + 1, high);                  //递归排序右子数组
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];     // 枢轴记录
        while (low < high) {
            // 右分支找到小于基准数的下标
            while (low < high && arr[high] >= pivot)
                high--;
            arr[low] = arr[high];           // 交换比枢轴小的记录到左端
            // 左分支找到大于基准值的下表
            while (low < high && arr[low] <= pivot)
                low++;
            arr[high] = arr[low];           // 交换比枢轴小的记录到右端
        }
        // 扫描完成，此时 low == high，设置枢轴的位置
        arr[low] = pivot;
        // 返回的是枢轴的位置
        return low;
    }
}
