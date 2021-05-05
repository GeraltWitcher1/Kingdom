package model;

import external.Log;
import utility.collection.ArrayList;
import utility.collection.QueueADT;

public class ValuableTransporter implements Runnable
{
  private QueueADT<Valuable> deposit;
  private ArrayList<Valuable> valuableList;
  private TreasureRoomDoor door;

  public ValuableTransporter(QueueADT<Valuable> queue, TreasureRoomDoor door) {
    this.deposit = queue;
    this.door = door;
    this.valuableList = new ArrayList<>();
  }

  @Override public void run() {
    while (true) {
      int gold = 0;
      int goldMinimum = (int) ((Math.random() * (1000 - 500)) + 500);
      Log.getLog()
          .addLog(getName() + " started collecting, gold minimum: " + goldMinimum);

      while (gold <= goldMinimum) {
        Valuable valuable = deposit.dequeue();
        gold += valuable.getValue();
        valuableList.add(0,valuable);
      }
      Log.getLog().addLog(
          getName() + " collected the valuables, moving to treasury, treasure list: "
              + valuableList + " total gold value: " + gold);

      door.depositValuables(valuableList);
      valuableList = new ArrayList<>();
      try {
        Thread.sleep(2000);
        door.leaveTreasuryTransporter();
        Log.getLog().addLog(getName() + " going to rest");
        Thread.sleep(8000);
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
