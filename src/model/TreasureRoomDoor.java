package model;

import utility.collection.ArrayList;

public interface TreasureRoomDoor
{
  void enterTreasuryWriter();
  void enterTreasuryReader();
  void leaveTreasuryWriter();
  void leaveTreasuryReader();
  void depositValuables(ArrayList<Valuable> valuableList);
  Valuable retrieveValuable();;
  ArrayList<Valuable> lookAtTreasures();
  boolean isEmpty();
}
