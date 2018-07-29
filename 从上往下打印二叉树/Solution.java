package 从上往下打印二叉树;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 二叉树的层次遍历
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
     * 队列实现，最直接的方式
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();

        if (root == null)
            return result;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.val);
            if (node.left != null)
                queue.push(node.left);

            if (node.right != null)
                queue.push(node.right);
        }
        return result;
    }

    /**
     * 栈模拟递归实现层次遍历
     */
    public ArrayList<Integer> PrintFromTopToBottom1(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();

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
            cur_level = next_level;
            result.addAll(cur_result);
        }
        return result;
    }

    /**
     * 递归的方式实现
     */
    public ArrayList<Integer> PrintFromTopToBottom2(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null)
            return new ArrayList<Integer>();

        bfs(result, root, 0);

        ArrayList<Integer> results = new ArrayList<>();

        for (ArrayList<Integer> level_results : result)
            results.addAll(level_results);
        return results;
    }

    private void bfs(ArrayList<ArrayList<Integer>> result, TreeNode root, int level) {
        if (root == null) return;

        // 遍历新的层
        if (level == result.size()) {
            ArrayList<Integer> cur_result = new ArrayList<>();
            cur_result.add(root.val);
            result.add(cur_result);
        } else {    // 遍历已经遍历过的层
            ArrayList<Integer> level_result = result.get(level);
            level_result.add(root.val);
            result.set(level, level_result);
        }
        // 遍历下一层
        bfs(result, root.left, level + 1);
        bfs(result, root.right, level + 1);
    }
}