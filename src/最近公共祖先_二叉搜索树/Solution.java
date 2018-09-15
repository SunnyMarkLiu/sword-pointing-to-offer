package src.最近公共祖先_二叉搜索树;

/**
 * 二叉搜索树任意两个节点的最近公共父节点
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
     * 二叉搜索树的性质：根节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null)
            return null;

        // root 的值比 p q 都大，则公共父节点在左子树
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);

        // root 的值比 p q 都小，则公共父节点在右子树
        } else if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        else
            // root 在 p，q 中间，则 root 即为公共父节点
            return root;
    }
}
