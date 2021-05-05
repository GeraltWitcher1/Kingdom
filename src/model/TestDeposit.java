package model;

public class TestDeposit
{
  public static void main(String[] args) {
    Deposit deposit = new Deposit();
    TreasureRoom treasureRoom = new TreasureRoom();
    TreasureRoomDoor door = new Guardsman(treasureRoom);

    new Thread(new Miner(deposit),"Miner1").start();
    new Thread(new Miner(deposit),"Miner2").start();

    new Thread(new ValuableTransporter(deposit,door),"Tomas").start();
    new Thread(new ValuableTransporter(deposit,door),"Ion").start();

    new Thread(new Accountant(door), "Max").start();
    new Thread(new Accountant(door), "Alex").start();

    new Thread(new King(door), "Dennis").start();
  }
}
