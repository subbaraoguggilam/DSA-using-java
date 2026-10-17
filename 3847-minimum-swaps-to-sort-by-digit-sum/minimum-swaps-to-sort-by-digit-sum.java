import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        long[][] arr = new long[n][3]; // {digitSum, value, originalIndex}
        for (int i = 0; i < n; i++) {
            int x = nums[i], s = 0;
            while (x > 0) {
                s += x % 10;
                x /= 10;
            }
            arr[i][0] = s;
            arr[i][1] = nums[i];
            arr[i][2] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]);
        });

        boolean[] visited = new boolean[n];
        int cycles = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            cycles++;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = (int) arr[j][2];
            }
        }
        return n - cycles;
    }
}