package 二叉搜索树与双向链表;

import java.util.Stack;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
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
     * 递归法：
     * 1、将 root 的左子树构造出双向链表，返回左双向链表的头指针
     * 2、找到左双向链表的尾节点
     * 3、如果左双向链表的尾节点不为空，将 root 拼接到左双向链表的尾部
     * 4、将 root 的右子树构造成双向链表，返回右双向链表的头指针
     * 5、如果右双向链表的头指针不为空，拼接到 root 的末尾
     * 6、返回的时候注意判断左双向链表是否为空，如果为空返回 root
     */
    public TreeNode Convert1(TreeNode root) {
        if (root == null)
            return null;

        if (root.left == null && root.right == null)
            return root;

        // 1、将 root 的左子树构造出双向链表，返回左双向链表的头指针
        TreeNode leftHead = Convert1(root.left);
        // 2、找到左双向链表的尾节点
        TreeNode leftLast = leftHead;
        while (leftLast != null && leftLast.right != null) {
            leftLast = leftLast.right;
        }

        // 3、如果左双向链表的尾节点不为空，将 root 拼接到左双向链表的尾部
        if (leftLast != null) {
            leftLast.right = root;
            root.left = leftLast;
        }
        // 4、将 root 的右子树构造成双向链表，返回右双向链表的头指针
        TreeNode rightHead = Convert1(root.right);
        // 5、如果右双向链表的头指针不为空，拼接到 root 的末尾
        if (rightHead != null) {
            root.right = rightHead;
            rightHead.left = root;
        }
        return leftHead == null ? root : leftHead;
    }

    /**
     * 非递归法：中序遍历的思想，遍历过程中修改当前遍历节点与前一遍历节点的指针指向，
     * 因此遍历时需要保存当前遍历节点的前一个节点。
     */
    public TreeNode Convert(TreeNode root) {
        if (root == null)
            return null;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        TreeNode head = null;  // 双向链表的头结点
        TreeNode pre = null;

        while (curNode != null || stack.size() > 0) {
            // 当前节点不为空，继续遍历左节点
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }

            // 访问到最左节点之后，从 stack 中 pop 取数据
            curNode = stack.pop();
            if (head == null) {
                head = curNode;
                pre = head;
            } else {
                pre.right = curNode;
                curNode.left = pre;
                pre = curNode;
            }
            // 访问右子树
            curNode = curNode.right;
        }
        return head;
    }
}