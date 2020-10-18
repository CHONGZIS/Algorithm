package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 题目：给定若干个重复的时间区间，求这种时间区间包含的长度
 * 思路：按照所有时间区间的开始时间排序，（其实这种就是贪心）
 * 1.当前区间的start <= 上一区间的end，则当前区间和上一时间区间可以合并为[preStart, curEnd]，
 * 2.反之不能当前区间不能和之前的区间合并，当前区间与前一时间区间没有重复区间，则计算前面区间的时间并更新总时间，当前时间更新为[start, end]，
 * 3.重复1，2步骤
 * 4.加上最后一个求得的时间区间的时长
 */
public class DeduplicationInterval {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] time = new int[n][2];
        for (int i = 0; i < n; i++) {
            time[i][0] = in.nextInt();
            time[i][1] = in.nextInt();
        }
        int ans = getTimeSum(time);
        System.out.println(ans);
    }

    private static int getTimeSum(int[][] time) {
        Arrays.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0]; // 按start升序
            }
        });
        int start = -1;
        int end = -1;
        int res = 0;
        for (int i = 0; i < time.length; i++) {
            if (time[i][0] <= end) {
                end = Math.max(end, time[i][1]);
            } else {
                res += (end - start);
                start = time[i][0];
                end = time[i][1];
            }
        }
        res += end - start;
        return res;
    }
}
