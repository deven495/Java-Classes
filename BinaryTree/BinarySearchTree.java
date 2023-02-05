package BinaryTree;

import java.util.ArrayList;

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

    public static ArrayList<Integer> rootToNodePath(BinaryTreeNode<Integer> root, int target) {
        if (root == null) {
            return null;
        }
        if (root.data == target) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(root.data);
            return temp;
        }
        ArrayList<Integer> leftSe = rootToNodePath(root.left, target);
        ArrayList<Integer> rightSe = rootToNodePath(root.right, target);
        if (leftSe != null) {
            leftSe.add(root.data);
            return leftSe;
        } else if (rightSe != null) {
            rightSe.add(root.data);
            return rightSe;
        } else {
            return null;
        }
    }

    public static pair<Boolean, pair<Integer, Integer>> isBSToptimised(BinaryTreeNode<Integer> root) {
        if (root == null) {
            pair<Boolean, pair<Integer, Integer>> object = new pair<>();
            object.first = true;
            object.second = new pair<Integer, Integer>();
            object.second.first = Integer.MAX_VALUE;
            object.second.second = Integer.MIN_VALUE;
            return object;
        }
        pair<Boolean, pair<Integer, Integer>> leftBST = isBSToptimised(root.left);
        pair<Boolean, pair<Integer, Integer>> rightBST = isBSToptimised(root.right);
        int min = Math.min(root.data, Math.min(rightBST.second.first, leftBST.second.first));
        int max = Math.max(root.data, Math.max(rightBST.second.second, leftBST.second.second));
        boolean isBST = (root.data > leftBST.second.second && root.data <= rightBST.second.first && leftBST.first
                && rightBST.first);
        pair<Boolean, pair<Integer, Integer>> output = new pair<>();
        output.first = isBST;
        output.second = new pair<Integer, Integer>();
        output.second.first = min;
        output.second.second = max;
        return output;
    }

    public static boolean isBST(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return true;
        }
        int leftMax = leftMaximum(root.left);
        int rightMin = rightMinimum(root.right);

        return (root.data > leftMax && root.data < rightMin);

    }

    private static int leftMaximum(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        return Math.max(root.data, Math.max(leftMaximum(root.left), leftMaximum(root.right)));

    }

    private static int rightMinimum(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        return Math.min(root.data, Math.min(rightMinimum(root.left), rightMinimum(root.right)));
    }

    public static ArrayList<Integer> searchInRange(BinaryTreeNode<Integer> root, int low, int high) {
        if (root == null) {
            ArrayList<Integer> list = new ArrayList<>();
            return list;
        }
        if (root.data > high) {
            ArrayList<Integer> op1 = searchInRange(root.left, low, high);
            return op1;
        } else if (root.data >= low && root.data <= high) {
            ArrayList<Integer> x = searchInRange(root.left, low, high);
            ArrayList<Integer> y = searchInRange(root.right, low, high);
            y.addAll(x);
            y.add(root.data);
            return y;
        } else {
            ArrayList<Integer> op3 = searchInRange(root.right, low, high);
            return op3;
        }

    }

    public static BinaryTreeNode<Integer> findElementInBST(BinaryTreeNode<Integer> root,
            BinaryTreeNode<Integer> target) {
        if (root == null) {
            return null;
        }
        if (root.data == target.data) {
            return root;
        } else if (root.data < target.data) {
            return findElementInBST(root.right, target);
        } else {
            return findElementInBST(root.left, target);
        }

    }
}