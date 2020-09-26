package Array;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class DeduplicationInterval {

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("00000000");
        System.out.println(df.format(1101));
//        System.out.println(df.format("011"));
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
        int start = time[0][0];
        int end = time[0][1];
        int res = 0;
        for (int i = 1; i < time.length; i++) {
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
