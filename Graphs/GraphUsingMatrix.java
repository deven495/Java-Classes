package Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GraphUsingMatrix {

    private int[][] matrix;

    public GraphUsingMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void printBFS() {
        boolean visited[] = new boolean[matrix.length];
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                printBFSHelper(i, visited);
            }
        }
    }

    private void printBFSHelper(int sv, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(sv);
        visited[sv] = true;
        while (!q.isEmpty()) {
            int takenOut = q.poll();
            System.out.print(takenOut + " ");
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[takenOut][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    private void printDFSHelper(int sv, boolean[] visited) {
        System.out.print(sv + " ");
        visited[sv] = true;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[sv][i] == 1 && !visited[i]) {
                printDFSHelper(i, visited);
            }
        }
    }

    public void printDFS() {
        boolean visited[] = new boolean[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            if (!visited[i]) {
                printDFSHelper(i, visited);
            }
        }
    }

    public static int[][] takeInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Vertices : ");
        int vertices = sc.nextInt();
        System.out.print("Edges : ");
        int edges = sc.nextInt();
        int matrix[][] = new int[vertices][vertices];

        for (int i = 0; i < edges; i++) {
            System.out.print("pair" + (i + 1) + ": ");
            int fv = sc.nextInt();
            int sv = sc.nextInt();
            matrix[fv][sv] = 1;
            matrix[sv][fv] = 1;

        }
        return matrix;
    }

    public boolean hasPath(int start, int end) {
        boolean visited[] = new boolean[matrix.length];
        hasPathHelper(start, end, visited);
        return (visited[end]);
    }

    private void hasPathHelper(int start, int end, boolean visited[]) {
        if (start == end) {
            return;
        }
        visited[start] = true;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[start][i] == 1 && !visited[i]) {
                hasPathHelper(i, end, visited);

            }
        }
    }

    public ArrayList<Integer> getPathDFS(int start, int end) {

        boolean visited[] = new boolean[matrix.length];
        ArrayList<Integer> list = getPathDFSHelper(start, end, visited);

        return list;
    }

    private ArrayList<Integer> getPathDFSHelper(int start, int end, boolean[] visited) {
        if (start == end) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(end);
            visited[start] = true;
            return list;
        }

        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();

        visited[start] = true;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[start][i] == 1 && !visited[i]) {
                list = getPathDFSHelper(i, end, visited);
                if (!list.isEmpty()) {
                    ans.add(start);
                    ans.addAll(list);
                    break;
                }
            }
        }

        return ans;

    }

    public ArrayList<Integer> getPathBFS(int start, int end) {
        boolean visited[] = new boolean[matrix.length];
        ArrayList<Integer> list = getPathBFShelper(start, end, visited);
        return list;
    }

    private ArrayList<Integer> getPathBFShelper(int start, int end, boolean[] visited) {

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        HashMap<Integer, Integer> map = new HashMap<>();
        while (!q.isEmpty()) {
            int takenOut = q.poll();
            visited[takenOut] = true;

            for (int i = 0; i < matrix.length; i++) {
                if (matrix[takenOut][i] == 1 && !visited[i]) {
                    q.add(i);
                    map.put(i, takenOut);
                    if (i == end) {
                        break;
                    }
                }
            }
        }
        if (map.containsKey(end)) {
            int target = end;
            ArrayList<Integer> list = new ArrayList<>();
            while (!map.isEmpty()) {
                list.add(target);
                if (target == start) {
                    Collections.reverse(list);
                    return list;
                }
                target = map.get(target);

            }
        }
        return new ArrayList<Integer>();
    }

    public ArrayList<ArrayList<Integer>> getComponents() {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        boolean visited[] = new boolean[matrix.length];
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                ArrayList<Integer> temp = new ArrayList<>();
                list.add(getSmallComponent(i, visited, temp));
            }
        }
        return list;
    }

    private ArrayList<Integer> getSmallComponent(int i, boolean[] visited, ArrayList<Integer> list) {
        list.add(i);
        visited[i] = true;
        for (int j = 0; j < matrix.length; j++) {
            if (matrix[i][j] == 1 && !visited[j]) {
                getSmallComponent(j, visited, list);
            }
        }
        return list;
    }
}
