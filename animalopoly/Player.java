public class Player {
    private String playerName;
    private char token;
    private boolean missGo;
    private int balance;
    private boolean bankrupt;
    private int location;

    public Player(String playerName, char token) {
        this.playerName = playerName;
        this.token = token;
        this.missGo = false;
        this.balance = 1000;
        this.location = 0;
    }

    public void setMissGo(boolean missGo) {
        this.missGo = missGo;
    }
    public boolean getMissGo() {
        return missGo;
    }
    public String getPlayerName() {
        return playerName;
    }
    public int getBalance() {
        return balance;
    }
    public void updateBalance(int amount) {
        balance += amount;
        if (balance < 0) {
            bankrupt = true;
            System.out.println("You are bankrupt!");
        }
    }
    public boolean getBankrupt() {
        return bankrupt;
    }
    public int getLocation() {
        return location;
    }
    public void setLocation(int move) {
        if ((location + move) > 25) {
            location = location + move - 26;
            if (location != 0) {
                updateBalance(500);
                System.out.println(this.playerName + " has passed start and receives £500\n");
            }
        }
        else {
            location = location + move;
        }
        if (location == 0) {
            updateBalance(1000);
            System.out.println(this.playerName + " has landed on start and receives £1000\n");
        }
    }
    public char getToken() {
        return token;
    }

}
