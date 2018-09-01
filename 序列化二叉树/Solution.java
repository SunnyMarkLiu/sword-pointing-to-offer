package 序列化二叉树;

/**
 * 请实现两个函数，分别用来序列化和反序列化二叉树
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
     * 前序遍历二叉树
     */
    String Serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            sb.append("$,");
            return sb.toString();
        }

        // 注意此处的值不能直接拼接需要加上分割符
        sb.append(root.val).append(",");
        sb.append(Serialize(root.left));
        sb.append(Serialize(root.right));
        return sb.toString();
    }

    /**
     * 前序遍历的方式构造二叉树
     */
    TreeNode Deserialize(String str) {
        if (str == null || str.trim().length() == 0)
            return null;

        str = str.trim();
        String[] strs = str.split(",");
        return helper(strs);
    }

    private int rootIndex = -1;
    TreeNode helper(String[] strs) {
        rootIndex++;
        if (rootIndex >= strs.length)
            return null;

        // 构造 root 节点
        if (strs[rootIndex].equals("$"))
            return null;

        TreeNode root = new TreeNode(Integer.parseInt(strs[rootIndex]));
        // 构造左子树和右子树，利用每次递归 rootIndex 自增 1
        root.left = helper(strs);
        root.right = helper(strs);
        return root;
    }
}