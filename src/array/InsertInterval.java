package array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/insert-interval/
 * 57.插入区间
 * 知识点：贪心，数组
 *
 * */
public class InsertInterval {
    public static void main(String[] args) {
        int[][] intervals =
                {{1,5}};
//        {{1,2},{3,5},{6,7},{8,10},{12,16}};
//        {{6,9},{1,3}};
        int[] newInterval =
                {};
//        {4,8};
        int[][] res = insert(intervals, newInterval);
        System.out.println(res);
    }
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        // 按区间的begin排序，end与后面的begin比较，更新end，如果不能更新，则新区间为[begin,end]
        // 重复步骤
        if (intervals == null || newInterval == null) {
            return new int[0][2];
        }
        if (newInterval.length == 0) {
            return intervals;
        }
        int[][] newInter = new int[intervals.length + 1][2];
        System.arraycopy(intervals,0, newInter, 0,intervals.length);
        newInter[intervals.length][0] = newInterval[0];
        newInter[intervals.length][1] = newInterval[1];
        ArrayList<int[]> ans = new ArrayList<>();
        Arrays.sort(newInter, (a, b) -> a[0] - b[0]);

        for (int i = 0; i < newInter.length; i++) {
            int start = newInter[i][0];
            int end = newInter[i][1];
            int j = i + 1;
            while (j < newInter.length && end >= newInter[j][0]) {
                end = Math.max(end, newInter[j++][1]);;
            }
            if (j != i + 1) {
                i = j - 1;
            }
            ans.add(new int[]{start, end});
        }
        int[][] res = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }
}
