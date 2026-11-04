class Solution {
public:
    long long minOperations(vector<int>& nums, int x, int k) {
        int n = nums.size();
        int m = n - x + 1;
        vector<long long> cost(m);

        multiset<int> lo, hi;          // lo: smaller half (size = ceil(x/2)), hi: larger half
        long long sumLo = 0, sumHi = 0;

        auto balance = [&]() {
            // lo size should be (cnt + 1) / 2
            while (lo.size() > hi.size() + 1) {
                auto it = prev(lo.end());
                sumLo -= *it;
                sumHi += *it;
                hi.insert(*it);
                lo.erase(it);
            }
            while (lo.size() < hi.size()) {
                auto it = hi.begin();
                sumHi -= *it;
                sumLo += *it;
                lo.insert(*it);
                hi.erase(it);
            }
        };

        auto add = [&](int v) {
            if (lo.empty() || v <= *prev(lo.end())) {
                lo.insert(v);
                sumLo += v;
            } else {
                hi.insert(v);
                sumHi += v;
            }
            balance();
        };

        auto remove = [&](int v) {
            auto it = lo.find(v);
            if (it != lo.end()) {
                lo.erase(it);
                sumLo -= v;
            } else {
                it = hi.find(v);
                hi.erase(it);
                sumHi -= v;
            }
            balance();
        };

        for (int i = 0; i < n; i++) {
            add(nums[i]);
            if (i >= x) remove(nums[i - x]);
            if (i >= x - 1) {
                long long med = *prev(lo.end());
                cost[i - x + 1] = med * (long long)lo.size() - sumLo
                                + sumHi - med * (long long)hi.size();
            }
        }

        const long long INF = LLONG_MAX / 4;
        vector<vector<long long>> dp(k + 1, vector<long long>(n + 1, INF));
        for (int i = 0; i <= n; i++) dp[0][i] = 0;

        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                dp[j][i] = dp[j][i - 1];
                if (i >= x && dp[j - 1][i - x] < INF) {
                    dp[j][i] = min(dp[j][i], dp[j - 1][i - x] + cost[i - x]);
                }
            }
        }
        return dp[k][n];
    }
};