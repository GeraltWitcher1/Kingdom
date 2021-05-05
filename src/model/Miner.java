package model;

import external.Log;
import utility.collection.ArrayQueue;
import utility.collection.QueueADT;

import java.util.concurrent.BlockingQueue;

public class Miner implements Runnable
{
  private ValuableType[] types = {ValuableType.DIAMOND, ValuableType.GOLDNUGGET,
      ValuableType.JEWEL, ValuableType.RUBY, ValuableType.WOODENCOIN};

  private QueueADT<Valuable> queue;

  public Miner(QueueADT<Valuable> queue) {
    this.queue = queue;
  }

  @Override public void run() {
    while (true) {
      Log.getLog().addLog(getName() + " is working");
      int num = (int)(Math.random()*4);
      //maybe in a Mine class?
      Valuable valuable = Valuable.getInstance(types[num]);
      Log.getLog().addLog(getName()+ " found " + valuable);
      queue.enqueue(valuable);
      try {
        Log.getLog().addLog(getName() + " is going to rest");
        Thread.sleep(3000);
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
