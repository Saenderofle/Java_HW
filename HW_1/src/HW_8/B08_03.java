package HW_8;

import java.util.*;


public class B08_03<T> {

    private final Map<T, Set<T>> adj = new HashMap<>();

    public void addVertex(T v) {
        adj.putIfAbsent(v, new HashSet<>());
    }

    public void removeVertex(T v) {
        if (!adj.containsKey(v)) return;

        for (T neighbor : adj.get(v)) {
            adj.get(neighbor).remove(v);
        }
        adj.remove(v);
    }

    public void addEdge(T v1, T v2) {
        addVertex(v1);
        addVertex(v2);

        adj.get(v1).add(v2);
        adj.get(v2).add(v1);
    }

    public void removeEdge(T v1, T v2) {
        if (adj.containsKey(v1)) adj.get(v1).remove(v2);
        if (adj.containsKey(v2)) adj.get(v2).remove(v1);
    }

    public Set<T> getNeighbors(T v) {
        return adj.getOrDefault(v, Set.of());
    }

    public void printGraph() {
        for (var entry : adj.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }

}
