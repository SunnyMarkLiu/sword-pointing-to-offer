package src.二叉树的镜像;

import java.util.Stack;

/**
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 */
public class Solution {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /**
     * 递归方式实现
     */
    public void Mirror1(TreeNode root) {
        if (root == null)
            return;
        // 如果 root 节点没有左右子树，直接返回
        if (root.left == null && root.right == null)
            return;

        // 有左子树或右子树，进行交换
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        // 递归交换左右子树
        if (root.left != null)
            Mirror1(root.left);

        if (root.right != null)
            Mirror1(root.right);

    }

    /**
     * 非递归的方式实现，用栈模拟递归
     */
    public void Mirror(TreeNode root) {
        if (root == null)
            return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (stack.size() > 0) {
            TreeNode node = stack.pop();
            // 存在左节点或右节点，则交换
            if (node.left != null || node.right != null) {
                TreeNode tmp = node.left;
                node.left = node.right;
                node.right = tmp;
            }

            if (node.left != null)
                stack.push(node.left);

            if (node.right != null)
                stack.push(node.right);
        }
    }
}