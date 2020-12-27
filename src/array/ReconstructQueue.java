package array;

import java.util.*;

/**
 * 406. 根据身高重建队列
 * https://leetcode-cn.com/problems/queue-reconstruction-by-height/
 */
public class ReconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
        // 按身高降序，身高相同再按k升序
        // 依次插入元素，高个子会最先按顺序插好，矮个子插入数组的第k个位置，满足前面有K个大于等于h的人，且不影响高个子
        // [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
        // 再一个一个插入。
        // [7,0]
        // [7,0], [7,1]
        // [7,0], [6,1], [7,1]
        // [5,0], [7,0], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        LinkedList<int[]> list = new LinkedList<>();
        for (int[] i : people) {
            list.add(i[1], i);
        }
        return list.toArray(new int[list.size()][2]);
    }

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        ArrayList<int[]> ans = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ans.add(new int[]{i, j});
            }
        }

        ans.sort((o1, o2) -> Math.abs(o1[0] - r0) + Math.abs(o1[1] - c0) - Math.abs(o2[0] - r0) - Math.abs(o2[1] - c0));
        return ans.toArray(new int[ans.size()][2]);
    }
    public String removeKdigits(String num, int k) {
        // 从左到右升序就是最小，想到单调栈
        Stack<Character> stack = new Stack<>();
        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && stack.peek() > c && k > 0) {
                stack.pop();
                k--;
            }
            if (stack.isEmpty() && c == '0') {
                continue;
            }
            stack.push(c);
        }

        while (k-- > 0 && !stack.isEmpty()) {
            stack.pop();
        }
        if (stack.isEmpty()) {
            return "0";
        }
        StringBuilder ans = new StringBuilder();
        for (Character c : stack) {
            ans.append(c);
        }
        return ans.toString();
    }
    public static void main(String[] args) {
        String num = "10";
        int k = 2;
        int R = 2, C = 2, r0 = 0, c0 = 1;
        new ReconstructQueue().removeKdigits(num, k);
    }
}
