package model;

import external.Log;
import utility.collection.ArrayList;

public class Guardsman implements TreasureRoomDoor
{
  private int accountants;
  private int transporters;
  private boolean king;
  private boolean kingIsWaiting;
  private int waitingTransporters;
  private TreasureRoom treasureRoom;

  public Guardsman(TreasureRoom treasureRoom){
    this.accountants = 0;
    this.transporters = 0;
    this.waitingTransporters = 0;
    this.king = false;
    this.kingIsWaiting = false;
    this.treasureRoom = treasureRoom;
  }

  private synchronized String getName() {
    return Thread.currentThread().getName();
  }

  @Override public synchronized void depositValuables(ArrayList<Valuable> valuableList) {
    waitingTransporters++;
    while(accountants > 0 || transporters > 0 || king || kingIsWaiting){
      try {
        Log.getLog().addLog(getName() + " waiting to start depositing valuables");
        wait();
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    waitingTransporters--;
    transporters++;
    treasureRoom.depositValuables(valuableList);
  }

  @Override public synchronized void returnValuables (ArrayList<Valuable> valuableList) {
    System.out.println(king);
    while(accountants > 0 || transporters > 0){
      try {
        wait();
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    treasureRoom.returnValuables(valuableList);
    System.out.println("valuables returned");
  }

  @Override public synchronized Valuable retrieveValuable() {
    kingIsWaiting = true;
    while(accountants > 0 || transporters > 0){
      try {
        Log.getLog().addLog(getName() + " waiting to start partying");
        wait();
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    kingIsWaiting = false;
    king = true;
    Log.getLog().addLog(getName() + " gathered the valuable");
    return treasureRoom.retrieveValuable();
  }


  @Override public synchronized ArrayList<Valuable> lookAtTreasures() {
    while(transporters > 0 || waitingTransporters > 0 || king || kingIsWaiting){
      try {
        Log.getLog().addLog(getName() + " waiting to start counting");
        wait();
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
      accountants++;
      Log.getLog().addLog(getName() + " counting");
    }
    return treasureRoom.lookAtTreasures();
  }

  @Override public synchronized void stopCounting() {
    accountants--;
    if (accountants == 0) {
      notifyAll();
    }
    Log.getLog().addLog(getName() + " finished counting");
  }

  @Override public synchronized boolean isEmpty() {
    return treasureRoom.isEmpty();
  }

  @Override public synchronized void leaveTreasuryTransporter() {
    transporters--;
    notifyAll();
    Log.getLog().addLog(getName() + " finished business at the treasury");
  }

  @Override public synchronized void leaveTreasuryKing() {
    king = false;
    notifyAll();
    Log.getLog().addLog(getName() + " left the treasury");
  }
}
