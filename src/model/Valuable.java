package model;

import java.util.HashMap;

public class Valuable
{
  private static final HashMap<ValuableType, Valuable> map = new HashMap<>();
  private ValuableType  valuableType;

  private Valuable(ValuableType valuableType){
    this.valuableType = valuableType;
  }

  public static Valuable getInstance(ValuableType valuableType)
  {
    Valuable instance = map.get(valuableType);
    if (instance == null)
    {
      synchronized (map)
      {
        instance = map.get(valuableType);
        if (instance == null)
        {
          instance = new Valuable(valuableType);
          map.put(valuableType, instance);
        }
      }
    }
    return instance;
  }

  public int getValue(){
    return valuableType.getValue();
  }

  @Override public String toString() {
    return String.format("valuable type: %s",valuableType);
  }
}
