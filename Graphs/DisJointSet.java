package Graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class DisJointSet extends GraphUsingMap {
    HashMap<String, Node> map = new HashMap<>();

    private class Node {
        String data;
        int rank;
        Node parent;

    }

    public void create(String value) {
        Node nn = new Node();
        nn.data = value;
        nn.rank = 0;
        nn.parent = nn;
    }

    public void union(String value1, String value2) {
        Node n1 = map.get(value1);
        Node n2 = map.get(value2);

        Node re1 = find(n1);
        Node re2 = find(n2);

        if (re1.data.equals(re2.data)) {
            return;
        } else {
            if (re1.rank == re2.rank) {
                re2.parent = re1;
                re1.rank += 1;
            } else if (re1.rank > re2.rank) {
                re2.parent = re1;
            } else {
                re1.parent = re2;
            }
        }

    }

    public String find(String value) {
        return find(map.get(value)).data;
    }

    private Node find(Node node) {
        if (node == node.parent) {
            return node;
        }
        Node rr = find(node.parent);
        node.parent = rr;
        return rr;
    }

    private class Edgepair {
        String v1;
        String v2;
        int cost;
    }

    public ArrayList<Edgepair> getAllEdges() {
        ArrayList<Edgepair> edges = new ArrayList<>();
        for (String vName : vtxMap.keySet()) {
            Nbrs nbr = vtxMap.get(vName);
            for (String string : nbr.nbrsMap.keySet()) {
                Edgepair ep = new Edgepair();
                ep.v1 = vName;
                ep.v2 = string;
                ep.cost = nbr.nbrsMap.get(string);
                edges.add(ep);
            }
        }
        return edges;
    }

    public void kruskal() {
        ArrayList<Edgepair> edges = getAllEdges();
        Collections.sort(edges, (a, b) -> (a.cost - b.cost));
        for (String Vname : vtxMap.keySet()) {
            create(Vname);
        }
        for (Edgepair ep : edges) {
            String r1 = find(ep.v1);
            String r2 = find(ep.v2);

            if (r1.equals(r2)) {
                continue;
            } else {
                union(ep.v1, ep.v2);
            }

        }
    }
}