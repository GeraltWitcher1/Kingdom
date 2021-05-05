package model;

import utility.collection.ArrayList;

public class TreasureRoom implements TreasureRoomDoor
{
  private ArrayList<Valuable> treasures;

  public TreasureRoom() {
    this.treasures = new ArrayList<>();
  }

  @Override public void depositValuables(ArrayList<Valuable> valuableList) {
    int size = valuableList.size();
    for (int i = 0; i < size; i++) {
      treasures.add(valuableList.remove(0));
    }
  }

  @Override public void returnValuables(ArrayList<Valuable> valuableList) {
    int size = valuableList.size();
    for (int i = 0; i < size; i++) {
      treasures.add(valuableList.remove(0));
    }
  }

  @Override public Valuable retrieveValuable() {
    return treasures.remove(0);
  }

  @Override public void leaveTreasuryTransporter() {
    //not used
  }

  @Override public void leaveTreasuryKing() {
    //not used
  }

  @Override public void stopCounting() {
    //not used
  }

  @Override public boolean isEmpty() {
    return treasures.size() == 0;
  }

  @Override public ArrayList<Valuable> lookAtTreasures() {
    //returns a copy for view-only security
    ArrayList<Valuable> temp = new ArrayList<>();
    for (int i = 0; i < treasures.size(); i++) {
      temp.add(treasures.get(i));
    }
    return temp;
  }

}
