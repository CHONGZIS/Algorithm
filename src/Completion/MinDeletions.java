package Completion;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinDeletions {

    public static void main(String[] args) {
        long ans  = 110;
        ans %= Math.pow(10, 9) + 7;
        System.out.println(ans);
   /*     String s = "ceabaacb";
        new MinDeletions().minDeletions(s);*/
    }
    public int minDeletions(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                int val = map.get(c);
                map.put(c, val + 1);
            } else {
                map.putIfAbsent(c, 1);
            }
        }
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        while (set.size() != map.size()) {
            set.clear();
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                char key = entry.getKey();
                int val = entry.getValue();
                if (!set.contains(val)) {
                    set.add(val);
                } else {
                    if (val - 1 == 0) {
                        map.remove(key);
                    } else {
                        map.put(key, val - 1);
                    }
                    ans++;
                    break;
                }
            }
        }

        return ans;
    }
}
