package model;

public class TestDeposit
{
  public static void main(String[] args) {
    Deposit deposit = new Deposit();

    new Thread(new Miner(deposit),"Alex").start();
    new Thread(new Miner(deposit),"Max").start();

    new Thread(new ValuableTransporter(deposit),"Tomas").start();
    new Thread(new ValuableTransporter(deposit),"Ion").start();
  }
}
