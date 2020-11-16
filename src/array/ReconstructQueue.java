package array;

import java.util.Arrays;
import java.util.LinkedList;

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
}
