class Solution {

    int n, k;
    int[] nums;
    Node[] tree;

    static class Node {
        int prod;
        int[] count;

        Node(int k) {
            count = new int[k];
        }
    }

    // Create node for one element
    Node createNode(int value) {
        Node node = new Node(k);

        node.prod = value % k;

        // One non-empty prefix: the element itself
        node.count[node.prod] = 1;

        return node;
    }

    // Merge two nodes
    Node merge(Node left, Node right) {

        if (left == null) return right;
        if (right == null) return left;

        Node result = new Node(k);

        // Product of entire segment
        result.prod =
            (int) ((long) left.prod * right.prod % k);

        // Prefixes completely inside left
        for (int i = 0; i < k; i++) {
            result.count[i] += left.count[i];
        }

        // Prefixes that contain all of left
        // and then continue into right
        for (int i = 0; i < k; i++) {

            if (right.count[i] == 0)
                continue;

            int remainder =
                (int) ((long) left.prod * i % k);

            result.count[remainder] += right.count[i];
        }

        return result;
    }

    // Build segment tree
    void build(int node, int left, int right) {

        if (left == right) {
            tree[node] = createNode(nums[left]);
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] =
            merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Point update
    void update(
        int node,
        int left,
        int right,
        int index,
        int value
    ) {

        if (left == right) {
            nums[index] = value;
            tree[node] = createNode(value);
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] =
            merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Range query
    Node query(
        int node,
        int left,
        int right,
        int ql,
        int qr
    ) {

        if (qr < left || right < ql) {
            return null;
        }

        if (ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        Node l =
            query(node * 2, left, mid, ql, qr);

        Node r =
            query(node * 2 + 1, mid + 1, right, ql, qr);

        return merge(l, r);
    }

    public int[] resultArray(
        int[] nums,
        int k,
        int[][] queries
    ) {

        this.nums = nums;
        this.k = k;
        this.n = nums.length;

        tree = new Node[4 * n];

        // Build initial tree
        build(1, 0, n - 1);

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Update nums[index]
            update(
                1,
                0,
                n - 1,
                index,
                value
            );

            // Get nums[start ... n-1]
            Node result =
                query(
                    1,
                    0,
                    n - 1,
                    start,
                    n - 1
                );

            // Number of prefixes having product % k == x
            answer[i] = result.count[x];
        }

        return answer;
    }
}