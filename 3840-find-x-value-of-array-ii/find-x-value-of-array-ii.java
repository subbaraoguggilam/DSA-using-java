class Solution {
    int k, n;
    int[][] func; // func[node][r]
    int[][] cnt;  // cnt[node][r*k+c]
    int[] arr;    // nums[i] % k

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = nums[i] % k;

        func = new int[4 * n][k];
        cnt = new int[4 * n][k * k];
        build(1, 0, n - 1);

        int identity = 1 % k; // remainder of the "empty product" mod k

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0], val = queries[i][1], start = queries[i][2], x = queries[i][3];
            arr[idx] = val % k;
            update(1, 0, n - 1, idx);

            int[] qf = new int[k];
            int[] qc = new int[k * k];
            for (int r = 0; r < k; r++) qf[r] = r; // identity function

            queryRange(1, 0, n - 1, start, n - 1, qf, qc);
            result[i] = qc[identity * k + x];
        }
        return result;
    }

    private void setLeaf(int node, int l) {
        int v = arr[l];
        for (int rr = 0; rr < k; rr++) {
            for (int c = 0; c < k; c++) cnt[node][rr * k + c] = 0;
            int c = (rr * v) % k;
            func[node][rr] = c;
            cnt[node][rr * k + c] = 1;
        }
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            setLeaf(node, l);
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        merge(node, 2 * node, 2 * node + 1);
    }

    private void merge(int node, int left, int right) {
        int[] nf = func[node];
        int[] nc = cnt[node];
        for (int r = 0; r < k; r++) {
            int mid = func[left][r];
            nf[r] = func[right][mid];
            for (int c = 0; c < k; c++) {
                nc[r * k + c] = cnt[left][r * k + c] + cnt[right][mid * k + c];
            }
        }
    }

    private void update(int node, int l, int r, int idx) {
        if (l == r) {
            setLeaf(node, l);
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(2 * node, l, mid, idx);
        else update(2 * node + 1, mid + 1, r, idx);
        merge(node, 2 * node, 2 * node + 1);
    }

    private void queryRange(int node, int l, int r, int ql, int qr, int[] qf, int[] qc) {
        if (qr < l || r < ql) return;
        if (ql <= l && r <= qr) {
            combine(qf, qc, func[node], cnt[node]);
            return;
        }
        int mid = (l + r) / 2;
        queryRange(2 * node, l, mid, ql, qr, qf, qc);
        queryRange(2 * node + 1, mid + 1, r, ql, qr, qf, qc);
    }

    private void combine(int[] qf, int[] qc, int[] nf, int[] nc) {
        int[] newFunc = new int[k];
        int[] newCnt = new int[k * k];
        for (int r = 0; r < k; r++) {
            int mid = qf[r];
            newFunc[r] = nf[mid];
            for (int c = 0; c < k; c++) {
                newCnt[r * k + c] = qc[r * k + c] + nc[mid * k + c];
            }
        }
        System.arraycopy(newFunc, 0, qf, 0, k);
        System.arraycopy(newCnt, 0, qc, 0, k * k);
    }
}