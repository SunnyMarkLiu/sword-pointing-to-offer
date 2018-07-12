package 根据前序和后续遍历重建二叉树;

import java.util.Arrays;

public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode reConstructBinaryTree(int[] pre_order, int[] in_order) {
        if (null == pre_order || null == in_order)
            return null;

        if (pre_order.length == 0 || in_order.length == 0)
            return null;

        // 根据 pre_order 的第一个节点创建头节点
        TreeNode head = new TreeNode(pre_order[0]);

        // 找到 head 在 in_order 的下标
        for (int index = 0; index < in_order.length; index++)
            if (pre_order[0] == in_order[index]) {
                // 创建左子树
                head.left = reConstructBinaryTree(
                        Arrays.copyOfRange(pre_order, 1, index + 1),    // index 指定了左子树的长度
                        Arrays.copyOfRange(in_order, 0, index)
                );
                // 创建右子树
                head.right = reConstructBinaryTree(
                        Arrays.copyOfRange(pre_order, index + 1, pre_order.length),
                        Arrays.copyOfRange(in_order, index + 1, in_order.length)
                );
            }
        return head;
    }
}