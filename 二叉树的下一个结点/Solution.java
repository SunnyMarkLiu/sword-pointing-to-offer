package 二叉树的下一个结点;

public class Solution {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null)
            return null;

        // 1. 如果该节点有右子树，则下一个节点是这个右子树的最左节点
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null)
                pNode = pNode.left;
            return pNode;
        }

        // 2. 如果该节点没有右子树，两种情况：
        //      如果这个节点处于左边的位置则下一个节点是其父节点；
        //      如果这个节点处于右边的位置则需要向上寻找父节点的父节点，找到的父节点使得节点是这个父节点的左节点

        TreeLinkNode parent = pNode.next;
        if (parent != null && parent.left == pNode) // pNode 是 parent 的左节点
            return parent;

        while (parent != null && parent.right == pNode) { // 直到找到 pNode 是 parent 的左节点
            pNode = parent;
            parent = parent.next; // 向上找父节点
        }

        return parent;
    }
}