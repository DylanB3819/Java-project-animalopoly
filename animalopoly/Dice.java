public class Dice {
    private int value;

    public Dice() {
        value = 0;
    }
    public int roll() {
        int num = (int)(Math.random() * 6) + 1;
        value = num;
        return num;
    }
}
