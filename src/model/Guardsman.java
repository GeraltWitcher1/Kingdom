package model;

import external.Log;
import utility.collection.ArrayList;

import java.util.HashMap;

public class Guardsman implements TreasureRoomDoor
{
  private int readers;
  private int writers;
  private int waitingWriters;
  private TreasureRoom treasureRoom;
  private HashMap<Thread,Boolean> hasWriteAccess;
  private HashMap<Thread,Boolean> hasReadAccess;

  public Guardsman(TreasureRoom treasureRoom){
    this.readers = 0;
    this.writers = 0;
    this.waitingWriters = 0;
    this.treasureRoom = treasureRoom;
    this.hasWriteAccess = new HashMap<>();
    this.hasReadAccess = new HashMap<>();
  }

  @Override public synchronized void enterTreasuryWriter() {
    waitingWriters++;
    while(readers > 0 || writers > 0){
      try {
        Log.getLog().addLog(getName() + " waiting to enter the treasury");
        wait();
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    waitingWriters--;
    Log.getLog().addLog(getName() + " entered treasury");
    writers++;
    hasWriteAccess.put(Thread.currentThread(),true);
  }

  @Override public synchronized void enterTreasuryReader() {
    while(writers > 0 || waitingWriters > 0){
      try {
        Log.getLog().addLog(getName() + " waiting to start counting");
        wait();
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    readers++;
    hasReadAccess.put(Thread.currentThread(),true);
    Log.getLog().addLog(getName() + " entered the treasury");
  }

  @Override public synchronized void leaveTreasuryReader() {
    readers--;
    if (readers == 0) {
      notifyAll();
    }
    Log.getLog().addLog(getName() + " left the treasury");
    hasReadAccess.replace(Thread.currentThread(),false);
  }

  @Override public synchronized void leaveTreasuryWriter() {
    writers--;
    notifyAll();
    Log.getLog().addLog(getName() + " left the treasury");
    hasWriteAccess.replace(Thread.currentThread(),false);
  }

  @Override public void depositValuables(ArrayList<Valuable> valuableList) {
    if(hasWriteAccess.get(Thread.currentThread())){
      treasureRoom.depositValuables(valuableList);
    } else throw new IllegalStateException("No write access");  
  }

  @Override public Valuable retrieveValuable() {
    if(hasWriteAccess.get(Thread.currentThread())){
      return treasureRoom.retrieveValuable();
    } else throw new IllegalStateException("No write access");
  }

  @Override public ArrayList<Valuable> lookAtTreasures() {
    if(hasReadAccess.get(Thread.currentThread())){
      return treasureRoom.lookAtTreasures();
    } else throw new IllegalStateException("No read access");
  }

  @Override public boolean isEmpty() {
    return treasureRoom.isEmpty();
  }

  private String getName() {
    return Thread.currentThread().getName();
  }
}
