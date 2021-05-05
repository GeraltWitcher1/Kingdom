package model;

import external.Log;
import utility.collection.ArrayList;

public class Accountant implements Runnable
{
  private TreasureRoomDoor door;

  public Accountant(TreasureRoomDoor door) {
    this.door = door;
  }

  @Override public void run() {
    while(true){
      int sum = 0;
      ArrayList<Valuable> treasures = door.lookAtTreasures();
      for(int i = 0; i< treasures.size(); i++){
        sum+=treasures.get(i).getValue();
        try {
          Thread.sleep(200);
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      Log.getLog().addLog(Thread.currentThread().getName()+" finished counting, result: "+ sum);
      door.stopCounting();
      try {
        Thread.sleep((int) ((Math.random() * (30000 - 10000)) + 10000));
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
