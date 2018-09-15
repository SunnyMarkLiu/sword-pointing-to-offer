package src.二叉搜索树的第k个结点;

/**
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。
 * 例如， （5，3，7，2，4，6，8） 中，按结点数值大小顺序第三小结点的值为4。
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

    private int count = 0;
    /**
     * 二叉搜索树中序遍历，获取第 k 个节点即可
     */
    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null)
            return null;

        TreeNode node = KthNode(pRoot.left, k);
        // 如果在左子树找到，则返回该节点
        if (node != null)
            return node;

        count++;

        if (count == k)
            return pRoot;

        node = KthNode(pRoot.right, k);
        // 如果在右子树找到，则返回该节点
        if (node != null)
            return node;
        return null;
    }


}