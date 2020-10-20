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


    public void reorderList_new(ListNode head) {
        // 1.找到中点，2.反转后半段链表，3.合并两个链表
        if (head == null) {
            return;
        }
        ListNode node = head;
        ListNode mid = findMiddleNode(node);
        ListNode second = reverse(mid.next);
        mid.next = null;
        merge(head, second);
    }

    public ListNode findMiddleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode tmp = null;
        while (head != null) {
            tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }

    public void merge(ListNode h, ListNode h2) {
        ListNode h1 = h;
        while(h1 != null && h2 != null) {
            ListNode tmp1 = h1.next;
            ListNode tmp2 = h2.next;
            h1.next = h2;
            if (tmp1 == null) {
                break;
            }
            h2.next = tmp1;
            h1 = tmp1;
            h2 = tmp2;
        }
    }
}
