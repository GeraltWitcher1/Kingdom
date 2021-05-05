package model;

import external.Log;
import utility.collection.ArrayList;
import utility.collection.QueueADT;

public class King implements Runnable
{
  private ArrayList<Valuable> valuableList;
  private TreasureRoomDoor door;

  public King(TreasureRoomDoor door) {
    this.door = door;
    this.valuableList = new ArrayList<>();
  }

  @Override public void run() {
    while (true) {
      int gold = 0;
      int goldMinimum = (int) ((Math.random() * (4000 - 2000)) + 2000);
      Log.getLog().addLog("The king started collecting, gold minimum: " + goldMinimum);

      while(gold <= goldMinimum){
        if(door.isEmpty()){
          break;
        }
        try {
          Thread.sleep(200);
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }
        Valuable valuable = door.retrieveValuable();
        gold += valuable.getValue();
        valuableList.add(valuable);
      }

      if(gold>=goldMinimum){
        Log.getLog().addLog("The king is throwing a party! Total cost = " + gold);
        valuableList = new ArrayList<>(); //"throw away" the gold
      }else{
        Log.getLog().addLog("The king could not get enough money for the party :( Total gold gathered: " + gold);
        door.returnValuables(valuableList);
        valuableList = new ArrayList<>();
      }

      door.leaveTreasuryKing();
      try {
        Thread.sleep(25000);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }

}

