package src.把二叉树打印成多行;

import java.util.ArrayList;

/**
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
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
     * 二叉树的层次遍历
     */
    ArrayList<ArrayList<Integer>> Print(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        ArrayList<TreeNode> cur_level = new ArrayList<>();
        cur_level.add(root);

        while (!cur_level.isEmpty()) {
            // 当前层的结果
            ArrayList<Integer> cur_result = new ArrayList<>();
            // 下一层的节点
            ArrayList<TreeNode> next_level = new ArrayList<>();

            for (TreeNode node : cur_level) {
                cur_result.add(node.val);

                if (node.left != null)
                    next_level.add(node.left);
                if (node.right != null)
                    next_level.add(node.right);
            }
            result.add(cur_result);
            cur_level = next_level;
        }
        return result;
    }
    
}