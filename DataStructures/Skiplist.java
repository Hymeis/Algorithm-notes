package DataStructures;

import java.util.Random;
import java.util.Stack;

/**
 * Author: Hymeis
 * Date: Feb. 6, 2025
 * Description: Skiplist is a data structure with optimized runtime for 
 * insert, remove, and search. All rumn times are on average O(lg(n)).
 * The idea is that whenever we look for a number in the list, we always
 * start from the top left, go to right first, and go down if the right
 * value is larger than what we're looking for. In simple words,
 * prioritize going right, then bottom.
 */


public class Skiplist {
    /**
     * A 2-D version of LinkedList. A node can go next or go down
     */
    class ListNode {
        int val;
        ListNode next;
        ListNode down;
        
        public ListNode(int start) {
            this.val = start;
            next = null;
            down = null;
        }
        public ListNode(int start, ListNode next, ListNode down) {
            this.val = start;
            this.next = next;
            this.down = down;    
        }
    }

    private final static int COIN_HEAD = 1;
    private final static int NEGATIVE_INF = Integer.MIN_VALUE;
    // The head of the Skiplist. Heads are by default negative infinity
    ListNode head;
    public Skiplist() {
        head = new ListNode(NEGATIVE_INF);
    }
    
    /**
     * Look for target inside the Skiplist
     * Time Complexity: O(lg n) on average 
     * @param target the value we would like to look for in the Skiplist
     * @return true if target exists in the Skiplist. False otherwise
     */
    public boolean search(int target) {
        ListNode curr = head;
        while (curr != null) {
            while (curr.next != null && curr.next.val < target) {
                curr = curr.next;
            }
            if (curr.next != null && curr.next.val == target) return true;
            curr = curr.down;
        }
        return false;
    }
    
    /**
     * Add num inside the Skiplist. Duplicates are allowed.
     * Time Complexity: O(lg n) on average
     * @param num the number we want to insert into the Skiplist
     */
    public void add(int num) {
        ListNode curr = head;
        Stack<ListNode> st = new Stack<>();

        // Record prev nodes' locations each row
        while (curr != null) {
            while (curr.next != null && curr.next.val < num) {
                curr = curr.next;
            }
            st.push(curr);
            curr = curr.down;
        }

        // First insert (insert at least once)
        ListNode prev = st.pop();
        ListNode bottom = new ListNode(num, prev.next, null);
        prev.next = bottom;

        // Coin Flip. If flip to HEAD(1), add another num above
        Random r = new Random();
        while (r.nextInt(2) == COIN_HEAD) {
            // No more rows on top -- add a new row above
            if (st.empty()) {
                ListNode newHead = new ListNode(NEGATIVE_INF, null, head);
                head = newHead;
                ListNode newBottom = new ListNode(num, null, bottom);
                bottom = newBottom;
                head.next = bottom;
            }
            // Insert on the row above
            else {
                prev = st.pop();
                ListNode newBottom = new ListNode(num, prev.next, bottom);
                bottom = newBottom;
                prev.next = bottom;
            }
        }
    }
    
    /**
     * Erase the given number (one column only) inside the Skiplist
     * @param num the number we'd like to erase
     * @return true if num exists in the Skiplist. False otherwise.
     */
    public boolean erase(int num) {
        ListNode curr = head;
        boolean hasNum = false;
        while (curr != null) {
            while (curr.next != null && curr.next.val < num) {
                curr = curr.next;
            }
            ListNode currLevel = curr;
            if (currLevel.next != null && currLevel.next.val == num) {
                hasNum = true;
                currLevel.next = currLevel.next.next;
            }
            curr = curr.down;
        }
        return hasNum;
    }
}
