package likedlist;

import commonclass.ListNode;

public class IsPalindrome {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        boolean result = new IsPalindrome().isPalindrome(head);
        System.out.println(result);
    }
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode firstEnd = endOfFirstHalf(head);
        ListNode secondStart = reverse(firstEnd.next);
        boolean result = true;
        ListNode p1 = head;
        ListNode p2 = secondStart;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false; // 不能直接返回
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        firstEnd.next = reverse(secondStart); // 需要还原链表，链表一旦修改，再保留原来的头节点没意义
        return result;
    }

    public ListNode endOfFirstHalf(ListNode head) {
        // 快指针走两步，慢指针走一步，快指针到null,如果链表长度为偶数，慢指针指到一半，长度为奇数，慢指针知道最中间的点，所以分两半时，第一半会大于等于第二半
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = reverse(head.next);
        head.next.next = head;
        head.next = null;
        // ListNode pre = null;
        // ListNode cur = head;
        // while (cur != null) {
        //     ListNode next = cur.next;
        //     cur.next = pre;
        //     pre = cur;
        //     cur = next;
        // }
        return pre;
    }
}
