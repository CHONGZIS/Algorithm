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
        // 注意删除的可能是第一个,加哑结点指向head，就可以省去判断head节点
        ListNode dummpy = new ListNode();
        dummpy.next = head;
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        while (n > 0) {
            stack.pop();
            n--;
        }
        if (!stack.isEmpty()) {
            ListNode preNode = stack.peek();
            preNode.next = preNode.next.next;
        } else {
            dummpy.next = dummpy.next.next; // 删的是第一个，
        }
        return dummpy.next;
    }
}
