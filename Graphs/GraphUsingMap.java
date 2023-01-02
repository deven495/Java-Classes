package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphUsingMap {
    private class Nbrs {
        HashMap<String, Integer> nbrsMap = new HashMap<>();

    }

    HashMap<String, Nbrs> vtxMap;

    public GraphUsingMap() {
        vtxMap = new HashMap<>();
    }

    public boolean hasVertex(String vtx) {
        return vtxMap.containsKey(vtx);
    }

    public boolean hasEdge(String vtx1, String vtx2) {
        Nbrs v = vtxMap.get(vtx1);
        Nbrs v2 = vtxMap.get(vtx2);

        if (v == null || v2 == null || !v.nbrsMap.containsKey(vtx2)) {
            return false;
        }
        return true;
    }

    public void deleteVertex(String target) {
        if (!vtxMap.containsKey(target)) {
            return;
        }
        Nbrs sathi = vtxMap.get(target);
        ArrayList<String> keys = new ArrayList<>(sathi.nbrsMap.keySet());
        for (String key : keys) {
            Nbrs sathi2 = vtxMap.get(key);
            sathi2.nbrsMap.remove(target);
        }
        vtxMap.remove(target);
        return;
    }

    public void print() {
        ArrayList<String> str = new ArrayList<>(vtxMap.keySet());

        for (String key : str) {
            System.out.print(key + " : ");
            Nbrs nbr = vtxMap.get(key);
            System.out.println(nbr.nbrsMap);
        }
    }

    public void addVertex(String vName) {
        vtxMap.put(vName, new Nbrs());
        return;
    }

    public void addEdge(String vName1, String vName2, int cost) {
        if (!vtxMap.containsKey(vName1) || !vtxMap.containsKey(vName2)) {
            return;
        }
        Nbrs nbr1 = vtxMap.get(vName1);
        Nbrs nbr2 = vtxMap.get(vName2);
        if (nbr1.nbrsMap.containsKey(vName2)) {
            return;
        }
        nbr1.nbrsMap.put(vName2, cost);
        nbr2.nbrsMap.put(vName1, cost);
        return;
    }

    public void removeEdge(String vName1, String vName2) {
        Nbrs nbr1 = vtxMap.get(vName1);
        Nbrs nbr2 = vtxMap.get(vName2);
        if (nbr1 == null || nbr2 == null || !nbr1.nbrsMap.containsKey(vName2)) {
            System.out.println("Bhai Sai Input Daal!!!");
            return;
        }
        nbr1.nbrsMap.remove(vName2);
        nbr2.nbrsMap.remove(vName1);
        return;
    }

    public boolean hasPath(String vName1, String vName2, HashMap<String, Boolean> map) {
        map.put(vName1, true);
        if (hasEdge(vName1, vName2)) {
            return true;
        }

        Nbrs nbr = vtxMap.get(vName1);
        ArrayList<String> nbrs = new ArrayList<>(nbr.nbrsMap.keySet());
        for (String n : nbrs) {
            if (!map.containsKey(n) && hasPath(n, vName2, map)) {
                return true;
            }
        }
        return false;
    }

    private class Pair {
        String vName;
        String psf;
    }

    public boolean BFS(String vName1, String vName2) {
        HashSet<String> processed = new HashSet<>();
        Queue<Pair> q = new LinkedList<>();
        if (hasEdge(vName1, vName2)) {
            return true;
        }
        Pair p = new Pair();
        p.vName = vName1;
        p.psf = vName1;

        q.add(p);

        while (!q.isEmpty()) {
            Pair x = q.poll();
            if (processed.contains(x.vName)) {
                continue;
            }
            processed.add(x.vName);
            if (hasEdge(x.vName, vName2)) {
                System.out.print(x.psf + vName2);
                return true;
            }
            Nbrs nbr = vtxMap.get(x.vName);
            ArrayList<String> list = new ArrayList<>(nbr.nbrsMap.keySet());

            for (String string : list) {
                if (!processed.contains(string)) {
                    Pair p2 = new Pair();
                    p2.vName = string;
                    p2.psf = x.vName + string;
                    q.add(p2);
                }
            }
        }
        return false;

    }

    public boolean DFS(String vName1, String vName2) {
        HashSet<String> processed = new HashSet<>();
        Stack<Pair> stack = new Stack<>();
        if (hasEdge(vName1, vName2)) {
            System.out.println(vName1 + vName2);
            return true;
        }
        Pair p = new Pair();
        p.vName = vName1;
        p.psf = vName1;

        stack.push(p);

        while (!stack.isEmpty()) {
            Pair x = stack.pop();
            if (processed.contains(x.vName)) {
                continue;
            }
            processed.add(x.vName);
            if (hasEdge(x.vName, vName2)) {
                System.out.println(x.psf + vName2);
                return true;
            }
            Nbrs nbr = vtxMap.get(x.vName);
            ArrayList<String> list = new ArrayList<>(nbr.nbrsMap.keySet());

            for (String string : list) {
                if (!processed.contains(string)) {
                    Pair p2 = new Pair();
                    p2.vName = string;
                    p2.psf = x.psf + string;
                    stack.push(p2);
                }
            }
        }
        return false;

    }

    public boolean isCyclic() {
        HashSet<String> processed = new HashSet<>();
        Queue<Pair> q = new LinkedList<>();
        ArrayList<String> list = new ArrayList<>(vtxMap.keySet());
        for (String string : list) {
            if (processed.contains(string)) {
                continue;
            }
            Pair p = new Pair();
            p.vName = string;
            p.psf = string;
            q.add(p);

            while (!q.isEmpty()) {
                Pair x = q.poll();
                if (processed.contains(x.vName)) {
                    return true;
                }
                processed.add(x.vName);
                Nbrs nbr = vtxMap.get(x.vName);
                ArrayList<String> list2 = new ArrayList<>(nbr.nbrsMap.keySet());

                for (String string2 : list2) {
                    if (!processed.contains(string2)) {
                        Pair p2 = new Pair();
                        p2.vName = string2;
                        p2.psf = x.vName + string2;
                        q.add(p2);
                    }
                }
            }
        }

        return false;

    }

    public boolean isConnected() {
        int flag = 0;
        HashSet<String> processed = new HashSet<>();
        Queue<Pair> q = new LinkedList<>();
        ArrayList<String> list = new ArrayList<>(vtxMap.keySet());
        for (String string : list) {
            if (processed.contains(string)) {
                continue;
            }
            flag++;
            if (flag >= 2) {
                return false;
            }
            Pair p = new Pair();
            p.vName = string;
            p.psf = string;
            q.add(p);

            while (!q.isEmpty()) {
                Pair x = q.poll();
                if (processed.contains(x.vName)) {
                    continue;
                }
                processed.add(x.vName);
                Nbrs nbr = vtxMap.get(x.vName);
                ArrayList<String> list2 = new ArrayList<>(nbr.nbrsMap.keySet());

                for (String string2 : list2) {
                    if (!processed.contains(string2)) {
                        Pair p2 = new Pair();
                        p2.vName = string2;
                        p2.psf = x.vName + string2;
                        q.add(p2);
                    }
                }
            }
        }

        return true;

    }

    public boolean isTree() {
        return !isCyclic() && isConnected();
    }

    public ArrayList<ArrayList<String>> getCC() {
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        HashSet<String> processed = new HashSet<>();
        Queue<Pair> q = new LinkedList<>();
        ArrayList<String> list = new ArrayList<>(vtxMap.keySet());
        for (String string : list) {
            if (processed.contains(string)) {
                continue;
            }
            ArrayList<String> subList = new ArrayList<>();
            Pair p = new Pair();
            p.vName = string;
            p.psf = string;

            q.add(p);
            while (!q.isEmpty()) {
                Pair x = q.poll();
                if (processed.contains(x.vName)) {
                    continue;
                }
                subList.add(x.vName);
                processed.add(x.vName);
                Nbrs nbr = vtxMap.get(x.vName);
                ArrayList<String> list2 = new ArrayList<>(nbr.nbrsMap.keySet());

                for (String str2 : list2) {
                    if (!processed.contains(str2)) {
                        Pair p2 = new Pair();
                        p2.vName = str2;
                        p2.psf = x.vName + str2;
                        q.add(p2);
                    }
                }
            }
            ans.add(subList);
        }

        return ans;

    }

}
