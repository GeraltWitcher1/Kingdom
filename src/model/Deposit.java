package model;

import utility.collection.ArrayList;
import utility.collection.QueueADT;
import utility.collection.ListADT;

public class Deposit implements QueueADT<Valuable>
{
  private ListADT<Valuable> valuables;

  public Deposit(){
    this.valuables = new ArrayList<>();
  }

  @Override public synchronized void enqueue(Valuable element) {
    valuables.add(valuables.size(),element);
      notifyAll();
  }

  @Override public synchronized Valuable dequeue() {
    while(isEmpty()) {
      try {
        wait();
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return valuables.remove(0);
  }

  @Override public synchronized Valuable first() {
    return valuables.get(0);
  }

  @Override public synchronized int indexOf(Valuable element) {
    return valuables.indexOf(element);
  }

  @Override public synchronized boolean isEmpty() {
    return valuables.isEmpty();
  }

  @Override public synchronized boolean isFull() {
    return valuables.isFull();
  }

  @Override public synchronized int size() {
    return valuables.size();
  }

  @Override public synchronized int capacity() {
    return Integer.MAX_VALUE;
  }
}
