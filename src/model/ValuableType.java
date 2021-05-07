package model;

public enum ValuableType
{
    DIAMOND(200){
        @Override public String toString() {
            return "Diamond";
        }
    },
    GOLDNUGGET(150){
        @Override public String toString() {
            return "Gold Nugget";
        }
    },
    JEWEL(100){
        @Override public String toString() {
            return "Jewel";
        }
    },
    RUBY(50){
        @Override public String toString() {
            return "Ŗuby";
        }
    },
    WOODENCOIN(10){
        @Override public String toString() {
            return "Wooden Coin";
        }
    };

    private final int value;

    ValuableType(int value){
      this.value = value;
    }

    public int getValue(){
      return value;
    }
}
