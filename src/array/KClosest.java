package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KClosest {
    public int[][] kClosest(int[][] points, int K) {
        List<Point> list = new ArrayList<>();
        for (int[] p : points) {
            list.add(new Point(p[0], p[1], getDistance(p[0],p[1])));
        }
        Collections.sort(list, (p1, p2) -> (int) (p2.dis - p1.dis));
        int[][] ans = new int[K][2];
        while (--K >= 0) {
            ans[K][0] = list.get(K).x;
            ans[K][1] = list.get(K).y;
        }
        return ans;
    }

    public double getDistance(int x, int y) {
        return Math.pow(x,2) + Math.pow(y,2);
    }
    class Point{
        int x;
        int y;
        double dis;
        public Point(int x, int y, double dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }
}
