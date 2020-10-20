package likedlist;

import commonclass.ListNode;

import java.util.ArrayList;

/**
 * LeetCode 143. 重排链表
 *
 * https://leetcode-cn.com/problems/reorder-list/
 * */
public class ReArrangeLikedList {

    public void reorderList(ListNode head) {
        // 根据测试用例自己手动画图，理清代码思路
        if (head == null) {
            return;
        }
        ArrayList<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur); // 1.将链表存储到集合中
            cur = cur.next;
        }
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            list.get(left).next = list.get(right);
            left++;
            if (left == right) { // 链表个数为偶数时，最后left.next = right, 不用重排，
                break;
            }
            list.get(right).next = list.get(left);
            right--;
        }
        list.get(right).next = null; // 最后一个指向null，不然会造成环
    }
}
