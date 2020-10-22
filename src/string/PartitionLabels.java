package string;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        partitionLabels(s);
    }
    public static List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        if (S == null) {
            return result;
        }
        // 遍历字符串，数组存每个字母出现在字符串的最后一个位置索引index[i]
        // left往左走，end=Math(end, index[i]),如果left==end,说明后面没有重复的值
        int[] indexs = new int[26];
        for (int i = 0; i < S.length(); i++) {
            indexs[S.charAt(i) - 'a'] = i;
        }
        int left = 0;
        int start = 0;
        int end = 0;
        while (left < S.length()) {
            end = Math.max(end, indexs[S.charAt(left) - 'a']);
            if (end == left) {
                result.add(end - start + 1);
                start = left + 1;
            }
            left++;
        }
        return result;
    }
}
