package model;

import external.Log;
import utility.collection.ArrayList;
import utility.collection.ArrayQueue;
import utility.collection.QueueADT;

public class ValuableTransporter implements Runnable
{
  private QueueADT<Valuable> queue;
  private ArrayList<Valuable> valuableList;

  public ValuableTransporter(QueueADT<Valuable> queue) {
    this.queue = queue;
    this.valuableList = new ArrayList<>();
  }

  @Override public void run() {
    while (true) {
      int gold = 0;
      int goldMinimum = (int) ((Math.random() * (1000 - 500)) + 500);
      Log.getLog().addLog(getName() + " started collecting, gold minimum: " + goldMinimum);

      while (gold <= goldMinimum) {
        Valuable valuable = queue.dequeue();
        gold += valuable.getValue();
        valuableList.add(valuable);
      }

      Log.getLog().addLog(getName() + " collected the valuables, moving to treasury, treasure list: " + valuableList);
      valuableList = new ArrayList<>();
      try {
        Log.getLog().addLog(getName() + " going to rest");
        Thread.sleep(10000);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private String getName() {
    return Thread.currentThread().getName();
  }
}
