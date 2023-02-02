package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinaryUse {
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

    public static int leftMaximum(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        return Math.max(root.data, Math.max(leftMaximum(root.left), leftMaximum(root.right)));
    }

    public static int rightMinimum(BinaryTreeNode<Integer> root) {
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

    public static void inOrderBT(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        inOrderBT(root.left);
        System.out.print(root.data + " ");
        inOrderBT(root.right);
    }

    public static void postOrderBT(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        postOrderBT(root.left);
        postOrderBT(root.right);
        System.out.print(root.data + " ");
    }

    public static void preOrderBT(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrderBT(root.left);
        preOrderBT(root.right);
    }

    public static pair<Integer, Integer> heightDiameter(BinaryTreeNode<Integer> root) {
        if (root == null) {
            pair<Integer, Integer> temp = new pair<>();
            temp.first = 0;
            temp.second = 0;
            return temp;
        }
        pair<Integer, Integer> leftOutput = heightDiameter(root.left);
        pair<Integer, Integer> rightOutput = heightDiameter(root.right);
        int height = 1 + Math.max(leftOutput.first, rightOutput.first);
        int diaOpt1 = leftOutput.first + rightOutput.first;
        int diaOpt2 = leftOutput.second;
        int diaOpt3 = rightOutput.second;
        int dia = Math.max(diaOpt1, Math.max(diaOpt2, diaOpt3));
        pair<Integer, Integer> output = new pair<>();
        output.first = height;
        output.second = dia;
        return output;

    }

    public static int heightOfBT(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(heightOfBT(root.left), heightOfBT(root.right));
    }

    public static int countNodes(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        int count = 1;
        count += countNodes(root.left);
        count += countNodes(root.right);
        return count;
    }

    public static BinaryTreeNode<Integer> takeInputLevelWise() {
        Scanner sc = new Scanner(System.in);
        int rootData;
        System.out.print("Enter RootData : ");
        rootData = sc.nextInt();
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);
        Queue<BinaryTreeNode<Integer>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            BinaryTreeNode<Integer> parent = q.poll();
            System.out.print("Enter left Child of " + parent.data + " : ");
            int leftChild = sc.nextInt();
            if (leftChild >= 0) {

                BinaryTreeNode<Integer> leftBacha = new BinaryTreeNode<Integer>(leftChild);
                parent.left = leftBacha;
                q.add(leftBacha);
            }
            System.out.print("Enter right Child of " + parent.data + " : ");
            int rightChild = sc.nextInt();
            if (rightChild >= 0) {

                BinaryTreeNode<Integer> rightBacha = new BinaryTreeNode<Integer>(rightChild);
                parent.right = rightBacha;
                q.add(rightBacha);
            }
        }
        sc.close();
        return root;
    }

    public static void printBinaryTree(BinaryTreeNode<Integer> root) {
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
        printBinaryTree(root.left);
        printBinaryTree(root.right);
    }

    public static BinaryTreeNode<Integer> takeInput(Scanner sc) {
        System.out.print("Enter Root Data : ");
        int rootData = sc.nextInt();
        if (rootData == -1) {
            return null;
        }
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);
        root.left = takeInput(sc);
        root.right = takeInput(sc);
        return root;
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

    public static int numOfLeafs(BinaryTreeNode<Integer> root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        return numOfLeafs(root.left) + numOfLeafs(root.right);
    }

    public static void printNodesAtDepthK(BinaryTreeNode<Integer> root, int k) {
        if (root == null) {
            return;
        }
        if (k == 0) {
            System.out.print(root.data + " ");
        }
        printNodesAtDepthK(root.left, k - 1);
        printNodesAtDepthK(root.right, k - 1);
    }

    public static BinaryTreeNode<Integer> removeLeafs(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return null;
        }
        root.left = removeLeafs(root.left);
        root.right = removeLeafs(root.right);
        return root;
    }

    public static void mirror(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        mirror(root.left);
        mirror(root.right);
        BinaryTreeNode<Integer> temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}
