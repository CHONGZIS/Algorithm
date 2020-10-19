package tree;

import commonclass.TreeNode;

public class CompleteTreeNodes {

    public int countNodes(TreeNode root) {
        // 递归解法，
        // 1.左子树的高（leftHeight）==右子树的高(rightHeight)，说明左子树是完全二叉树，左子树的节点个数可求出来 2^(leftHight) - 1，遍历求右子树的节点总数，总节点数= 左子树的个数（2^(leftHight) - 1）+右子树的个数 + 1 = 2^(leftHight) + countNodes(root.right)
        // 2.leftHight != rightHeight，说明右子树是完全二叉树，同理：总结点数=  2^(rightHeight) + countNodes(root.left)(root.right)
        if (root == null) {
            return 0;
        }
        int leftHight = getTreeHeight(root.left);
        int rightHeight = getTreeHeight(root.right);
        if (leftHight == rightHeight) {
            return (1 << leftHight) + countNodes(root.right);
        } else {
            return (1 << rightHeight) + countNodes(root.left);
        }
    }

    public int getTreeHeight(TreeNode root) {
        int height = 0;
        while (root != null) {
            height++;
            root = root.left;
        }
        return height;
    }
}
