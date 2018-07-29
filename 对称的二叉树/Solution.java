package 对称的二叉树;

/**
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
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

    public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null)
            return true;

        return helper(pRoot.left, pRoot.right);
    }

    private boolean helper(TreeNode node1, TreeNode node2) {
        // 两边都为 null，则比较完成，对称
        if (node1 == null && node2 == null)
            return true;

        // node1 和 node2 又一个不为 null，不对称
        if (node1 == null || node2 == null)
            return false;

        // 都不为 null，比较 val 是否相等
        if (node1.val == node2.val)
            // 值相等，比较对于的左右子树是否对称
            return helper(node1.left, node2.right) && helper(node1.right, node2.left);
        return false;
    }
}