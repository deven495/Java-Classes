package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinaryTree {
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
        return root;
    }

    public static int heightOfBT(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(heightOfBT(root.left), heightOfBT(root.right));
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

    public static boolean isBalancedBT(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return true;
        }
        int leftHeight = heightOfBT(root.left);
        int rightHeight = heightOfBT(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        boolean leftOpt = isBalancedBT(root.left);
        boolean rightOpt = isBalancedBT(root.right);

        return leftOpt && rightOpt;
    }

    public static pair2 isBTBalanced(BinaryTreeNode<Integer> root) {
        if (root == null) {
            pair2 p = new pair2();
            p.height = 0;
            p.isBT = true;
            return p;
        }
        pair2 leftSe = isBTBalanced(root.left);
        pair2 rightSe = isBTBalanced(root.right);
        int height = 1 + (Math.max(leftSe.height, rightSe.height));
        boolean isBT = true;
        if ((Math.abs(leftSe.height - rightSe.height) > 1) || leftSe.isBT == false || rightSe.isBT == false) {
            isBT = false;
        }
        pair2 ans = new pair2();
        ans.height = height;
        ans.isBT = isBT;
        return ans;

    }

    public static int diameter(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        int op1 = heightOfBT(root.left) + heightOfBT(root.right);
        int op2 = diameter(root.left);
        int op3 = diameter(root.right);

        return Math.max(op1, Math.max(op2, op3));

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

    public static pair3 diameterOptimised(BinaryTreeNode<Integer> root) {
        if (root == null) {
            pair3 p = new pair3(0, 0);
            return p;
        }
        pair3 lefthd = diameterOptimised(root.left);
        pair3 righthd = diameterOptimised(root.right);

        int height = 1 + Math.max(lefthd.height, righthd.height);
        int diaOpt1 = lefthd.height + righthd.height;
        int diaOpt2 = lefthd.diameter;
        int diaOpt3 = righthd.diameter;
        return new pair3(height, Math.max(diaOpt1, Math.max(diaOpt2, diaOpt3)));
    }

    public static BinaryTreeNode<Integer> makeTree(int[] pre, int[] in) {
        return makeTreeHelper(pre, in, 0, pre.length - 1, 0, in.length - 1);
    }

    private static BinaryTreeNode<Integer> makeTreeHelper(int[] pre, int[] in, int preStart, int preEnd, int inStart,
            int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(pre[preStart]);
        int rootInd = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (in[i] == pre[preStart]) {
                rootInd = i;
                break;
            }
        }
        root.left = makeTreeHelper(pre, in, preStart + 1, preStart + (rootInd - inStart),
                inStart, rootInd - 1);
        root.right = makeTreeHelper(pre, in, preStart + (rootInd - inStart) + 1, preEnd,
                rootInd + 1, inEnd);

        return root;
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

    public static ArrayList<Integer> nodeToRootPath(BinaryTreeNode<Integer> root, int target) {
        if (root == null) {
            return null;
        }
        if (root.data == target) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(root.data);
            return list;
        }
        ArrayList<Integer> left = nodeToRootPath(root.left, target);
        if (left != null) {
            left.add(root.data);
            return left;
        }
        ArrayList<Integer> right = nodeToRootPath(root.right, target);
        if (right != null) {
            right.add(root.data);
            return right;
        }
        return null;
    }
}
