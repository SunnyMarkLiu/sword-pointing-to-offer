package src.按之字形顺序打印二叉树;

import java.util.ArrayList;

/**
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
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
     * 二叉树的层次遍历，同时注意奇偶行的add方向
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        ArrayList<TreeNode> cur_level = new ArrayList<>();
        cur_level.add(root);
        int level = 1;
        while (!cur_level.isEmpty()) {
            // 当前层的结果
            ArrayList<Integer> cur_result = new ArrayList<>();
            // 下一层的节点
            ArrayList<TreeNode> next_level = new ArrayList<>();

            for (TreeNode node : cur_level) {
                // 此处有两种方法，一种是根据奇偶行添加 value，一种是根据奇偶行添加left 和right
                if ((level & 1) == 0) // 偶数leve，从右到左打印
                    cur_result.add(0, node.val);
                else    // 奇数行从左到右打印
                    cur_result.add(node.val);

                if (node.left != null)
                    next_level.add(node.left);

                if (node.right != null)
                    next_level.add(node.right);
            }

            result.add(cur_result);
            cur_level = next_level;
            level++;
        }
        return result;
    }

}