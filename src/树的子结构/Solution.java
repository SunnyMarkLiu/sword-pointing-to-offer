package src.树的子结构;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
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
     * 递归法
     * 第一步：在树 A 中找到与 B 的根节点一样的节点 R （递归过程查找根节点，左右子树）
     * 第二步：从树 A 的 R 节点开始依次比较对应的节点是否都相等，直到树 B 为 null
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null)
            return false;

        return doesTree1HasTree2(root1, root2) ||   // 从根节点比较
                HasSubtree(root1.left, root2) ||    // 根节点不相等，递归检测左子树
                HasSubtree(root1.right, root2);     // 根节点和左子树不相等，递归检测右子树
    }

    /**
     * 递归遍历比较从树 A 的 R 节点开始依次比较对应的节点是否都相等，直到树 B 为 null
     */
    private boolean doesTree1HasTree2(TreeNode root1, TreeNode root2) {
        if (root2 == null)  // 如果Tree2已经遍历完了都能对应的上，返回true
            return true;

        if (root1 == null)  // 如果Tree2还没有遍历完，Tree1却遍历完了。返回false
            return false;

        // 如果其中有一个点没有对应上，返回false
        if (root1.val != root2.val)
            return false;

        // 根节点的 val 相等，则比较左右节点
        return doesTree1HasTree2(root1.left, root2.left) && doesTree1HasTree2(root1.right, root2.right);
    }
}