package BinaryTree;

import java.util.ArrayList;
import java.util.Scanner;

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

    private static BinaryTreeNode<Integer> deleteNodeHelper(BinaryTreeNode<Integer> root, int target) {
        if (root == null) {
            return null;
        }
        if (target < root.data) {
            root.left = deleteNodeHelper(root.left, target);
        } else if (target > root.data) {
            root.right = deleteNodeHelper(root.right, target);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null && root.right == null) {
                return root.left;
            } else if (root.left == null && root.right != null) {
                return root.right;
            } else {
                int min = rightMinimum(root.right);
                root.data = min;
                root.right = deleteNodeHelper(root.right, min);
            }
        }
        return root;
    }

    public void insertNode(int target) {
        root = insertNodeHelper(root, target);
        return;
    }

    private BinaryTreeNode<Integer> insertNodeHelper(BinaryTreeNode<Integer> root, int target) {
        if (root == null) {
            BinaryTreeNode<Integer> bt = new BinaryTreeNode<Integer>(target);
            return bt;
        }
        if (target > root.data) {
            root.right = insertNodeHelper(root.right, target);
        } else {
            root.left = insertNodeHelper(root.left, target);
        }
        return root;
    }

    public void printBST() {
        printBSTHelper(root);
        return;
    }

    private static void printBSTHelper(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        String str = root.data + ": ";
        if (root.left != null) {
            str += "L" + root.left.data + ", ";
        }
        if (root.right != null) {
            str += "R" + root.right.data + ", ";
        }
        System.out.println(str);
        printBSTHelper(root.left);
        printBSTHelper(root.right);

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
        if (leftMax > root.data || rightMin < root.data) {
            return false;
        }
        boolean leftBST = isBST(root.left);
        boolean rightBST = isBST(root.right);

        return leftBST && rightBST;

    }

    private static int leftMaximum(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return Integer.MIN_VALUE;
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

    public static BinaryTreeNode<Integer> makeBSTfromSortedArr(int arr[]) {
        return makeBSThelper(arr, 0, arr.length - 1);
    }

    private static BinaryTreeNode<Integer> makeBSThelper(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int midInd = (end + start + 1) / 2;
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(arr[midInd]);
        BinaryTreeNode<Integer> left = makeBSThelper(arr, start, midInd - 1);
        BinaryTreeNode<Integer> right = makeBSThelper(arr, midInd + 1, end);
        root.left = left;
        root.right = right;
        return root;
    }

    public static boolean isBSToptimised2(BinaryTreeNode<Integer> root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.data < min || root.data > max) {
            return false;
        }
        boolean left = isBSToptimised2(root.left, min, root.data - 1);
        boolean right = isBSToptimised2(root.right, root.data, max);
        return left && right;
    }

    public static BinaryTreeNode<Integer> takeInputLevelWise2(boolean isRoot, int rootData, boolean isLeftChild) {
        if (isRoot) {
            System.out.print("Enter rootData : ");
        } else {
            if (isLeftChild) {
                System.out.print("Enter LeftChild of " + rootData + " : ");
            } else {
                System.out.print("Enter RightChild of " + rootData + " : ");
            }
        }
        Scanner sc = new Scanner(System.in);
        int rootData1 = sc.nextInt();
        if (rootData1 == -1) {
            return null;
        }
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData1);
        BinaryTreeNode<Integer> leftChild = takeInputLevelWise2(false, rootData1, true);
        BinaryTreeNode<Integer> rightChild = takeInputLevelWise2(false, rootData1, false);
        root.left = leftChild;
        root.right = rightChild;
        return root;
    }

}