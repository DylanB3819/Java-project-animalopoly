public class Card {
    private int value;
    private String message;

    public Card(int money) {
        value = money;
        if (money <= -350) {
            message = "You're tax was miscalculated. You must pay £" + (money*-1) + ".";
        }
        else if (-350 < money && money <= -200) {
            message = "You were caught speeding. You receive a £" + (money*-1) + " fine.";
        }
        else if (-200 < money && money <= -100) {
            message = "You spent £" + (money*-1) + " on prescriptions following a doctor's appointment.";
        }
        else if (-100 < money && money <= 0) {
            message = "You need to pay £" + (money*-1) + " for house repairs.";
        }
        else if (0 < money && money <= 100) {
            message = "You won a beauty contest and received £" + money + ".";
        }
        else if (100 < money && money <= 250) {
            message = "You won a crossword competition and received £" + money + ".";
        }
        else if (250 < money && money <= 350) {
            message = "You received £" + money + " in inheritance money.";
        }
        else if (money > 350) {
            message = "You're tax was miscalculated. You receive £" + money + ".";
        }
        else {
            message = "";
        }
    }


    public int getValue() {
        return value;
    }
    public String getMessage() {
        return message;
    }
}
