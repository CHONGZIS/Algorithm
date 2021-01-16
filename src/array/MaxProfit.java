package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxProfit {
    public static int maxProfit(int[] prices) {
        // 遍历一遍，计算连续上升的段落差值，取最大的两个
        if (prices.length <= 1) {
            return 0;
        }
        List<Integer> values = new ArrayList<>(2);
        int len = prices.length;
        int i = 0;

        while (i < len) {
            int min = prices[i];
            int max = min;
            while (i < len && prices[i] >= max) {
                max = prices[i];
                i++;
            }
            if (max > min) {
                int val = max - min;
                values.add(val);
            } else {
                i++;
            }
        }
        Collections.sort(values);
        int cnt = 0;
        int ans = 0;
        for (i = values.size() - 1; i >= 0; i--) {
            if (cnt >= 2) {
                break;
            }
            ans += values.get(i);
            cnt++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] prices = {1,2,4,2,5,7,2,4,9,0};
        maxProfit(prices);
    }
}
