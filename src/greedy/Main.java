package greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public int videoStitching(int[][] clips, int T) {
        // 贪心，start时间先排序，
        // [0,2]
        // [1,5]
        // [1,9]
        // [4,6]
        // [5,9]
        // [8,10]

        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        ArrayList<Integer> vals;

        for (int[] clip : clips) {
            if (map.containsKey(clip[0])) {
                vals = map.get(clip[0]);
            } else {
                vals = new ArrayList<>();
            }
            vals.add(clip[1]);
            map.put(clip[0], vals);
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int preEnd = -1;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
            int start = entry.getKey();
            int end = -1;
            for (int val : entry.getValue()) {
                end = Math.max(end, val);
            }
            if (end <= preEnd) {
                return  -1;
            } else {
                ArrayList<Integer> tmp = new ArrayList<>();
                tmp.add(start, end);
                result.add(tmp);
                if (end == T) {
                    break;
                }
            }
        }
        return result.size();
    }
}
