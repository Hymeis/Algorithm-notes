class UnionFind {
    int[] rank;
    int[] parent;

    public UnionFind(int n) {
        rank = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public boolean union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB) return false;
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
        if (parent[a] != a) {
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }
}