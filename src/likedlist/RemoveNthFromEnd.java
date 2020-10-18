package likedlist;

import commonclass.ListNode;

import java.util.Stack;

/**
 *  LeetCode 19. 删除链表的倒数第N个节点
 *  https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 遍历一遍，用栈存，然后弹n个，
        // 注意删除的可能是第一个
        if (head == null) {
            return head;
        }
        ListNode root = head;
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode nthFromEnd = null;
        while (n > 0) {
            nthFromEnd = stack.pop();
            n--;
        }
        if (!stack.isEmpty()) {
            stack.peek().next = nthFromEnd.next;
            return root;
        } else {
            return root.next;
        }
    }
}
