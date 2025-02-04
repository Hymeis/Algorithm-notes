/**
 * Author: Hymeis
 * Date Created: Feb. 4, 2025
 * Program description: The UnionFind class. We can use this to "union" connected parts
 * One most common way to use Union Find class is to apply it on Kruskal algorithm 
 */
class UnionFind {
    int[] rank;
    int[] parent;

    public UnionFind(int n) {
        rank = new int[n];
        parent = new int[n];
        // Each part is by default the parent of their own
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public boolean union(int a, int b) {
        // Find the parent of two elements
        int parentA = find(a);
        int parentB = find(b);
        // We don't union if they are connected (i.e. have the same parent)
        if (parentA == parentB) return false;
        // If disjoint, we connect the part with lower rank onto the other w/ higher rank
        if (rank[parentA] < rank[parentB]) {
            parent[parentA] = parentB;
        }
        else if (rank[parentA] > rank[parentB]) {
            parent[parentB] = parentA;
        }
        else {
            parent[parentB] = parentA;
            rank[parentA]++;
        }
        return true;
    }

    public int find(int a) {
        // Avoid infinite loop
        if (parent[a] != a) {
            parent[a] = find(parent[a]);
        }
        // Always return the parent
        return parent[a];
    }
}