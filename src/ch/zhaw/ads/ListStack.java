package ch.zhaw.ads;

import java.util.ArrayList;
import java.util.List;

public class ListStack implements Stack {

  List wannabeStack = new ArrayList();
  int stackSize = -1;

  public ListStack(int stackSize) {
    if (stackSize > 0) {
      this.stackSize = stackSize;
    } else {
      throw new IllegalArgumentException("not a valid size number");
    }
  }

  public ListStack() {}

  public void push(Object x) throws StackOverflowError {
    if (isFull()) {
      throw new ArrayIndexOutOfBoundsException("!The array is full!");
    }
    wannabeStack.add(x);
  }

  public Object pop() {
    final Object popObject = findLast();
    if (popObject != null) wannabeStack.remove(wannabeStack.size() - 1);
    return popObject;
  }

  public boolean isEmpty() {
    return wannabeStack.isEmpty();
  }

  public Object peek() {
    return findLast();
  }

  public void removeAll() {
    wannabeStack.clear();
  }

  public boolean isFull() {
    return !(stackSize == -1 || (stackSize > 0 && stackSize <= wannabeStack.size()));
  }

  private Object findLast() {
    return wannabeStack.stream().reduce((f, l) -> l).orElse(null);
  }
}
