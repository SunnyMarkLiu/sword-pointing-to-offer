package src.二叉树中和为某一值的路径;

import java.util.ArrayList;

/**
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class Solution {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {this.val = val; }

    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        dfs(root, sum, new ArrayList<>(), result);
        return result;
    }

    /**
     * 先序遍历+回溯法的思想，从根节点开始一直到叶子节点，判断和是否为sum
     */
    private void dfs(TreeNode root,
                     int sum,
                     ArrayList<Integer> curResult,
                     ArrayList<ArrayList<Integer>> result) {
        if (root == null)
            return;

        curResult.add(root.val);
        // 到达叶子节点并且此时的 sum == roo.val
        if (root.left == null && root.right == null && sum == root.val) {
            result.add(new ArrayList<>(curResult));
            // 注意回溯下，探索更多的结果
            curResult.remove(curResult.size() - 1);
            return;
        }

        // 递归遍历左右子树
        dfs(root.left, sum - root.val, curResult, result);
        dfs(root.right, sum - root.val, curResult, result);
        // 注意此处需要删除最后一个，以探索更多的可能组合
        curResult.remove(curResult.size() - 1);
    }
}