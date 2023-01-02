package BinaryTree;

public class BinarySearchTree {
    private BinaryTreeNode<Integer> root;

    public BinarySearchTree(BinaryTreeNode<Integer> root) {
        this.root = root;
    }

    public boolean hasData(int target) {
        return hasDataHelper(target, root);
    }

    private boolean hasDataHelper(int target, BinaryTreeNode<Integer> root) {
        if (root == null) {
            return false;
        }
        if (root.data == target) {
            return true;
        } else if (root.data < target) {
            return hasDataHelper(target, root.right);
        } else {
            return hasDataHelper(target, root.left);
        }
    }

    public void deleteNode(int target) {

        this.root = deleteNodeHelper(root, target);

        return;
    }

    public BinaryTreeNode<Integer> deleteNodeHelper(BinaryTreeNode<Integer> root, int target) {
        if (root == null) {
            return null;
        }
        if (root.data == target && root.left == null && root.right == null) {
            return null;
        } else if (root.data == target && root.left != null && root.right == null) {
            return root.left;
        } else if (root.data == target && root.left == null && root.right != null) {
            return root.right;
        } else if (root.data == target && root.left != null && root.right != null) {
            int max = leftMaximum(root.left);
            root.data = max;
            root.left = deleteNodeHelper(root.left, max);
            return root;

        } else if (root.data > target) {
            root.left = deleteNodeHelper(root.left, target);
        } else {
            root.right = deleteNodeHelper(root.right, target);
        }
        return root;
    }

    private int leftMaximum(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        return Math.max(root.data, Math.max(leftMaximum(root.left), leftMaximum(root.right)));

    }

    public void insertNode(int target) {
        insertNodeHelper(root, target);
        return;
    }

    private BinaryTreeNode<Integer> insertNodeHelper(BinaryTreeNode<Integer> root, int target) {
        if (root == null) {
            BinaryTreeNode<Integer> newNode = new BinaryTreeNode<Integer>(target);
            return newNode;
        }
        if (root.data > target) {
            BinaryTreeNode<Integer> leftSe = insertNodeHelper(root.left, target);
            root.left = leftSe;
            return root;
        } else {
            BinaryTreeNode<Integer> rightSe = insertNodeHelper(root.right, target);
            root.right = rightSe;
            return root;
        }
    }

    public static void printBST(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        String rootStr = root.data + ":";
        if (root.left != null) {
            rootStr += "L" + root.left.data + ", ";
        }
        if (root.right != null) {
            rootStr += "R" + root.right.data + ", ";
        }
        System.out.println(rootStr);
        printBST(root.left);
        printBST(root.right);
    }
}