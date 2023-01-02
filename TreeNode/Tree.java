package TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Tree {
    public void postOrderPrinting(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < root.children.size(); i++) {
            postOrderPrinting(root.children.get(i));
        }
        System.out.print(root.data + " ");
    }

    public void preOrderPrinting(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        for (int i = 0; i < root.children.size(); i++) {
            preOrderPrinting(root.children.get(i));
        }
    }

    public int sumOfLeafs(TreeNode<Integer> root, int sum) {
        if (root == null) {
            return 0;
        }
        if (root.children.size() == 0) {
            sum += root.data;
            return sum;
        }
        for (int index = 0; index < root.children.size(); index++) {
            sum = sumOfLeafs(root.children.get(index), sum);
        }
        return sum;
    }

    public void depthOfTheTree(TreeNode<Integer> root, int k) {
        if (k < 0) {
            return;
        } else if (k == 0) {
            System.out.println(root.data);
            return;
        }
        for (int i = 0; i < root.children.size(); i++) {
            depthOfTheTree(root.children.get(i), k - 1);
        }
    }

    public int heightOfTheTree(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        int ans = 1;
        for (int i = 0; i < root.children.size(); i++) {
            int child = 1;
            child += heightOfTheTree(root.children.get(i));
            ans = Math.max(ans, child);

        }
        return ans;
    }

    public int maxNode(TreeNode<Integer> root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int ans = root.data;
        for (int i = 0; i < root.children.size(); i++) {
            int children = maxNode(root.children.get(i));
            if (children > ans) {
                ans = children;
            }
        }
        return ans;
    }

    public int numberOfNodes(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        int count = 1;

        for (int i = 0; i < root.children.size(); i++) {
            count += numberOfNodes(root.children.get(i));
        }
        return count;
    }

    public void print(TreeNode<Integer> root) {
        Queue<TreeNode<Integer>> q = new LinkedList<>();
        System.out.print(root.data + "=> ");
        for (int i = 0; i < root.children.size(); i++) {
            q.add(root.children.get(i));
            System.out.print(root.children.get(i).data + ",");
        }
        System.out.println();
        while (!q.isEmpty()) {
            TreeNode<Integer> child = q.poll();
            System.out.print(child.data + "=> ");
            for (int i = 0; i < child.children.size(); i++) {
                q.add(child.children.get(i));
                System.out.print(child.children.get(i).data + ",");
            }
            System.out.println();
        }
    }

    public TreeNode<Integer> takeInputLevelWise() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter RootData : ");
        int rootData = sc.nextInt();
        TreeNode<Integer> root = new TreeNode<Integer>(rootData);
        Queue<TreeNode<Integer>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode<Integer> child = q.poll();
            System.out.print("Enter child Count for : " + child.data + "=>");
            int children = sc.nextInt();
            for (int i = 0; i < children; i++) {
                System.out.print("Enter " + (i + 1) + " child of : " + child.data + "=>");
                int childData = sc.nextInt();
                TreeNode<Integer> childNode = new TreeNode<Integer>(childData);
                child.children.add(childNode);
                q.add(childNode);
            }

        }
        print(root);
        return root;
    }
}
