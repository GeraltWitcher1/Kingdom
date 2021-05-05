package model;

import utility.collection.ArrayList;

public interface TreasureRoomDoor
{
  void depositValuables(ArrayList<Valuable> valuableList);
  void returnValuables(ArrayList<Valuable> valuableList);
  Valuable retrieveValuable();
  void leaveTreasuryTransporter();
  void leaveTreasuryKing();
  ArrayList<Valuable> lookAtTreasures();
  void stopCounting();
  boolean isEmpty();
}
