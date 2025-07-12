import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Animal> animalBoard = new ArrayList<>();

    public Board(ArrayList<Player> players, ArrayList<Animal> animals) {
        this.players = players;
        this.animals = animals;
        for (int i = 0; i < 25; i++) {
            if (0 < i && i <= 13) {
                if (i == 13) {
                    animalBoard.add(null);
                }
                animalBoard.add(animals.get(i-1));
            }
            else if (i > 13) {
                animalBoard.add(animals.get(i-1));
            }
            else {
                animalBoard.add(null);
            }
        }
    }

    public void movePlayer(Player player, int move) {
        player.setLocation(move);
    }



    public void displayBoard() {
        ArrayList<String> board = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            ArrayList<Player> tempPlayers = new ArrayList<>();
            for (int j = 0; j < players.size(); j++) {
               if (i == players.get(j).getLocation()) {
                   tempPlayers.add(players.get(j));
               }
            }
            if (tempPlayers.size() > 0) {
                String tokens = "";
                for (int k = 0; k < tempPlayers.size(); k++) {
                    tokens += tempPlayers.get(k).getToken();
                }
                if (tokens.length() == 1) {
                    board.add(" " + tokens + " ");
                }
                else if (tokens.length() == 2) {
                    board.add(tokens + " ");
                }
                else {
                    board.add(tokens);
                }
            }
            else {
                if (i == 0) {
                    board.add(" S ");
                }
                else if (i == 13) {
                    board.add(" M ");
                }
                else {
                    if (i > 13) {
                        board.add(i + " ");
                    }
                    else {
                        board.add(" " + i + " ");
                    }
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            if (i == 7) {
                System.out.print(board.get(i));
                System.out.println();
            }
            else {
                System.out.print(board.get(i));
                System.out.print("|");
            }
        }
        System.out.println(board.get(25) + "|                       |" + board.get(8));
        System.out.println(board.get(24) + "|                       |" + board.get(9));
        System.out.println(board.get(23) + "|                       |" + board.get(10));
        System.out.println(board.get(22) + "|                       |" + board.get(11));
        System.out.println(board.get(21) + "|                       |" + board.get(12));
        for (int i = 20; i > 12; i--) {
            if (i == 13) {
                System.out.print(board.get(i));
                System.out.println();
            }
            else {
                System.out.print(board.get(i));
                System.out.print("|");
            }
        }
    }



    public void purchaseAnimal(Animal animal, Player player) {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            try {
                System.out.println(player.getPlayerName() + " Would you like to buy this animal?\n1) yes\n2) no");
                int answer = Integer.parseInt(scanner.nextLine());
                if (answer == 1) {
                    animal.setOwned(true);
                    animal.setOwner(player.getPlayerName());
                    player.updateBalance(animal.getCostToBuy()*-1);
                    loop = false;
                } else if (answer == 2) {
                    loop = false;
                } else {
                    System.out.println("Invalid input! - 1 or 2");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! - 1 or 2");
            }
        }
        System.out.println("\n");
    }

    public void giveInstructions(Player player) {
        int position = player.getLocation();
        if (position == 13) {
            player.setMissGo(true);
            System.out.println(player.getPlayerName() + " misses there next go!");
        }
        else if (position == 0) {
            System.out.println("You're back at the start!");
        }
        else {
            System.out.println("You've landed on an animal");
            if (animalBoard.get(position).getOwned() == false) {
                System.out.println("Animal: " + animalBoard.get(position).getSpecies());
                System.out.println("Cost to buy: " + animalBoard.get(position).getCostToBuy());
                System.out.println("Cost to land: " + animalBoard.get(position).getCostToStop());
                System.out.println("Your balance: £" + player.getBalance());
                if (animalBoard.get(position).getCostToBuy() > player.getBalance()) {
                    System.out.println("You can't afford this animal");
                }
                else {
                    purchaseAnimal(animalBoard.get(position), player);
                }
                System.out.println();
            }
            else {
                if (animalBoard.get(position).getOwner().equals(player.getPlayerName())) {
                    System.out.println("It seems you already own this animal! Perhaps you could upgrade it.");
                    System.out.println();
                }
                else {
                    System.out.println("Unfortunately, you've landed on "+ player.getPlayerName() + "'s animal.");
                    System.out.println("Your balance: £" + player.getBalance());
                    System.out.println("You have to pay: £" + animalBoard.get(position).getCostToStop());
                    System.out.println();
                    player.updateBalance(animalBoard.get(position).getCostToStop()*-1);
                }
            }
        }
    }
}
