class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    // Create dummy node to simplify code
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;
    
    // Traverse both lists
    while (list1 != null && list2 != null) {
        if (list1.val <= list2.val) {
            current.next = list1;
            list1 = list1.next;
        } else {
            current.next = list2;
            list2 = list2.next;
        }
        current = current.next;
    }
    
    // Attach remaining nodes
    current.next = (list1 != null) ? list1 : list2;
    
    return dummy.next;
}
