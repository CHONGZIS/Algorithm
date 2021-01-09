package array;

import java.util.ArrayList;
import java.util.Arrays;

public class FindMinArrowShots {
    public static void main(String[] args) {
        int[][] points = {{1,2},{-2147483648,2147483647}};
        new FindMinArrowShots().findMinArrowShots(points);
    }
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (p1, p2) -> p1[0] == p2[0] ? Integer.compare(p2[1], p1[1]) : Integer.compare(p1[0], p2[0]));
        ArrayList<Integer> end = new ArrayList<>(); // [1, 6、8]
        end.add(points[0][1]);
        int cnt = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > end.get(end.size() - 1)) {
                end = new ArrayList<>();
                end.add(points[i][1]);
                cnt++;
            } else {
                boolean flag = false;
                for (int e : end) {
                    if (points[i][0] > e) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    cnt++;
                    end = new ArrayList<>();
                    end.add(points[i][1]);
                } else if (points[i][1] > end.get(end.size() - 1)) {
                    end.add(points[i][1]);
                }
            }
        }
        return cnt;
    }
}
