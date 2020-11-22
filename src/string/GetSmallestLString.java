package string;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * 5606. 具有给定数值的最小字符串
 * https://leetcode-cn.com/problems/smallest-string-with-a-given-numeric-value/
 */
public class GetSmallestLString {
    public static void main(String[] args) {
        int n = 2, k = 52;
        new GetSmallestLString().getSmallestString(n, k);
    }

    public String getSmallestString(int n, int k) {
        // 贪心算法，先说结论：根据题意输出的字符串应该符合"aa...xzz..."结构（xz...,a...x,x可能有三种状态）
        // 也就是前面的位置尽可能小放a，尾部尽可能最多的z
        // 思考一下为什么这样满足字典序最小?
        // 这样题解就出来了，从尾部开始填，能填z就填z，
        // 当k - z < 26，还有m位没填，前m-1位填a，x= k - (m - 1);
        char[] minString = new char[n];
        Arrays.fill(minString, 'a');
        int m = n - 1;
        while (k >= m + 26) { //
            k -= 26;
            minString[m--] = 'z';
        }
        if (m >= 0) {
            minString[m] = (char) ('a' + k - (m + 1));
        }
        System.out.println(new String(minString));
        return new String(minString);
    }

    ArrayList<String> ans = new ArrayList<>();

    public String getSmallestString1(int n, int k) {
        char[] res = new char[n];
        dfs(n, k, 0, 0, res);
        System.out.println(ans);
        return ans.isEmpty() ? "" : ans.get(0);
    }

    private void dfs(int n, int k, int cur, int pos, char[] res) {
        if (pos == n) {
            if (cur == k) {
                ans.add(new String(res));
            }
            return;
        }
//        12345
        // 剪枝
        int unUsed = n - pos;
        if (cur + unUsed * 26 < k || ans.size() == 1) {
            return;
        }
        // 排pos位置
        for (int i = 0; i < 26; i++) {
            char tmp = res[pos];
            res[pos] = (char) ('a' + i);
            dfs(n, k, cur + i + 1, pos + 1, res);
            res[pos] = tmp;
        }
    }
}
