package ch.zhaw.ads;

import java.util.ArrayList;

public class MySortedList extends MyList {
  @Override
  public boolean add(Object object) {
    MyList.ListNode currentNode = head;
    while (currentNode != null && currentNode.curr != null && currentNode.compareTo(object) <= 0) {
      //adding as last item
      if (currentNode.next == null) {
        currentNode.next = new MyList.ListNode(object, currentNode, null);
        size++;
        System.out.println("Test");
        return true;
      }
      currentNode = currentNode.next;
    }
    if (currentNode == head) {
      if (isEmpty()) {
        head.curr = object;
      } else {
        head = new MyList.ListNode(object, null, head);
        currentNode.previous = head;
      }
      size++;
      return true;
    }
    MyList.ListNode newNode = new MyList.ListNode(object, currentNode.previous, currentNode);
    currentNode.previous.next = newNode;
    currentNode.previous = newNode;
    size++;
    return true;
  }
}

class MyList extends ArrayList {
  ListNode head = new ListNode();
  int size = 0;

  public Object get(int index) {
    ListNode val = head;
    for (int i = 1; i <= index; i++) {
      if (val.next != null) {
        val = val.next;
      }
    }
    return val.curr;
  }

  public boolean add(Object object) {
    ListNode last = head;
    while(last != null && last.next != null) {
      last = last.next;
    }
    if (last == null) {
      System.out.println("Last is null");
    }
    if (last.curr == null) {
      last.curr = object;
    } else {
      last.next = new ListNode(object, last, null);
    }
    size++;
    return true;
  }

  public boolean remove(Object object) {
    // Then find first element with object
    ListNode currentNode = head;
    while (currentNode != null && currentNode.curr != null && !currentNode.curr.equals(object)) {
      currentNode = currentNode.next;
    }
    if (currentNode == null || isEmpty()) {
      return false;
    }
    if (currentNode == head) {
      if (currentNode.next != null) {
        head = currentNode.next;
      } else {
        clear();
      }
    } else  {
      currentNode.previous.next = currentNode.next;
      if (currentNode.next != null) {
        currentNode.next.previous = currentNode.previous;
      }
    }
    size--;
    return true;
  }

  public boolean isEmpty() {
    return head.next == null && head.previous == null && head.curr == null;
  }

  public void clear() {
    ListNode first = head;
    first.next = null;
  }

  public int size() {
    return size;
  }

  class ListNode implements Comparable {
    public ListNode previous;
    public ListNode next;
    public Object curr;

    public ListNode(Object curr, ListNode previous, ListNode next) {
      this.curr = curr;
      this.previous = previous;
      this.next = next;
    }

    public ListNode() {
      this.curr = null;
      this.next = null;
      this.previous = null;
    }


    @Override
    public int compareTo(Object o) {
      return curr.toString().compareTo(o.toString());
    }
  }
}
