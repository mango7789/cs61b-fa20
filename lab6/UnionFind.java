import java.util.ArrayList;

public class UnionFind {
    int[] parent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        // set all the parents to be -1 to symbolize that they are disjoint
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid vertex. */
    private void validate(int v1) {
        if (v1 < 0 || v1 >= parent.length) {
            throw new IllegalArgumentException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        int root = find(v1);
        return -1 * parent[root];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean isConnected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Connecting a
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void connect(int v1, int v2) {
        int Root1, Root2;
        Root1 = find(v1); Root2 = find(v2);
        // if they're already connected, just execute path compression
        if (Root1 == Root2) {
            parent[v1] = parent[v1] > 0 ? Root1 : parent[v1];
            parent[v2] = parent[v2] > 0 ? Root2 : parent[v2];
            return;
        }
        // break the tie by connecting root1 to root2
        if (parent[Root1] == parent[Root2]) {
            parent[Root2] += parent[Root1];
            parent[v1] = parent[Root1] = Root2;
            parent[v2] = parent[v2] > 0 ? Root2 : parent[v2];
        }
        // o.w. connect root2 to root1
        else {
            parent[Root1] += parent[Root2];
            parent[v2] = parent[Root2] = Root1;
            parent[v1] = parent[v1] > 0 ? Root1 : parent[v1];
        }


    }

    /* Returns the root of the set v1 belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int v1) {
        validate(v1);
        int Prev = v1;
        while (parent[Prev] >= 0) {
            Prev = parent[Prev];
        }
        return Prev;
    }

}
