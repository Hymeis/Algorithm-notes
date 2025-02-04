package MST;

import java.util.PriorityQueue;
/**
 * Author: Hymeis
 * Date Created: Feb. 4, 2025
 * Program description: you have random 2d integer points on the board. 
 *  Find the sum of the minimum spanning tree (MST) edges.
 */
public class Kruskal {
    public class UnionFind {
        int[] parent;
        int[] rank;
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX == parentY) return false;
            if (rank[parentX] < rank[parentY]) {
                parent[parentX] = parentY;
            }
            else if (rank[parentY] > rank[parentX]) {
                parent[parentY] = parentX;
            }
            else {
                parent[parentX] = parentY;
                rank[parentY]++;
            }

            return true;
        }
    }
    private int getDist(int[][] points, int i, int j) {
        int x = Math.abs(points[i][0] - points[j][0]);
        int y = Math.abs(points[i][1] - points[j][1]);
        return x + y;
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // Generate all possible edges
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dist = getDist(points, i, j);
                pq.offer(new int[]{dist, i, j});
            }
        }
        UnionFind uf = new UnionFind(n);
        int ans = 0;
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            if (uf.union(edge[1], edge[2])) {
                ans += edge[0];
            }
        }
        return ans;
    }

    // TBD: main
}
