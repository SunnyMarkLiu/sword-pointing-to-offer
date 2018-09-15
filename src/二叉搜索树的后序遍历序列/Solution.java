package src.二叉搜索树的后序遍历序列;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class Solution {

    /**
     * 二叉搜索树的后序遍历的最后一个值为根节点，
     * 去掉根节点之后的序列继续判断是否满足 => 递归
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0)
            return false;

        return helper(sequence, 0, sequence.length - 1);
    }

    /**
     * 递归判断序列是否满足 BST 的后序遍历
     */
    private boolean helper(int[] seq, int start, int end) {
        if (start > end)
            return true;

        int root = seq[end];
        // 找到左子树的位置，左子树的值都比 root 小
        int left = start;
        while (left <= end - 1) {
            if (seq[left] > root)   // 此时出现大于root的值，属于右子树
                break;
            left++;
        }
        // 判断此时的右子树是否满足都大于 root
        int right = left;
        while (right <= end - 1) {
            if (seq[right] < root)  // 不满足，false
                return false;
            right++;
        }
        // 递归判断 root 前面的序列是否满足
        // return helper(seq, start, end - 1);

        // 此处可优化为判断左右子树是否满足
        return helper(seq, start, left - 1) && helper(seq, left, end - 1);
    }
}