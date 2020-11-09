package Completion;

public class MaximumGenerated {
    public static void main(String[] args) {
        new MaximumGenerated().getMaximumGenerated(7);
    }
    public int getMaximumGenerated(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] ans = new int[n + 1];
        ans[1] = 1;
        int maxNum = 1;
        for (int i = 2; i <= n; i++) {
            int cur = i / 2;
            ans[i] = i % 2 == 0 ? ans[cur] : ans[cur] + ans[cur + 1];
            maxNum = Math.max(maxNum, ans[i]);
        }
        return maxNum;
    }
}
