package src.平衡二叉树;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 * 平衡二叉树：空树；左子树和右子树的深度之差的绝对值不超过1，
 *          且它的左子树和右子树都是一颗平衡二叉树。
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

    private Map<TreeNode, Integer> map = new HashMap<>();
    /**
     * 自顶向下递归获取左子树和右子树的深度，判断差值是否大于1。
     * 由于是自顶向下的，底层的子树重复计算了多次，此处可以优化，map缓存中间结果
     */
    public boolean IsBalanced_Solution2(TreeNode root) {
        if (root == null)
            return true;

        int leftH = getDepth(root.left);
        int rightH = getDepth(root.right);

        if (Math.abs(leftH - rightH) > 1)
            return false;

        return IsBalanced_Solution2(root.left) && IsBalanced_Solution2(root.right);
    }

    /**
     * 递归获取当前节点的树的深度
     */
    private int getDepth(TreeNode root) {
        if (root == null)
            return 0;

        if (map.containsKey(root))
            return map.get(root);

        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);

        int depth = leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
        map.put(root, depth);
        return depth;
    }

    /**
     * 自底向上递归判断，只要子树有不满足的直接返回不符合的标记，符合返回树的深度
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null)
            return true;

        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode root) {
        if (root == null)
            return 0;

        int leftH = getHeight(root.left);
        int rightH = getHeight(root.right);

        // 如果此时不满足，直接返回 -1 的标记，由于是递归，左子树或右子树为-1，也直接返回 -1
        if (Math.abs(leftH - rightH) > 1 || leftH == -1 || rightH == -1)
            return -1;

        return Math.max(leftH, rightH) + 1;
    }
}