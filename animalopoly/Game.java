import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    private String winner = "";
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Animal> animals = new ArrayList<>();
    private String[] species = {"mouse", "rat", "fish", "bird", "cat", "dog", "chicken", "fox",
                                "wolf", "pig", "sheep", "goat", "cow", "horse", "bear", "shark",
                                "giraffe", "whale", "lion", "tiger", "elephant", "cheetah", "rhino", "platypus"};
    private int[] costsToBuy = new int[24];
    private int[] costsToStop = new int[24];
    private int[] cardValues = {-500,-450,-400,-350,-300,-250,-200,-150,-100,-50,50,100,150,200,250,300,350,400,450,500};

    public Game() {
        System.out.println("Number of players: ");
        Scanner scanner = new Scanner(System.in);
        int numOfPlayers = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i < numOfPlayers + 1; i++) {
            System.out.println("Player " + i + "'s name: ");
            String playerName = scanner.nextLine();

            boolean loop = true;
            char playerToken = 0;
            while (loop) {
                try {
                    System.out.println("Player " + i + "'s token: ");
                    String token = scanner.nextLine();
                    if (token.length() == 1) {
                        loop = false;
                        playerToken = token.charAt(0);
                    } else {
                        System.out.println("Invalid token!");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid token!");
                }
            }
            players.add(new Player(playerName, playerToken));
        }
        for (int j = 0; j < 24; j++) {
            costsToBuy[j] = 200 + (j*25);
            costsToStop[j] = 3*costsToBuy[j]/5;
        }
    }



    public boolean collectCard(String playerName) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(playerName + " rolled a double. Would you like to draw a card?\n1) yes\n2) no");
                int answer = Integer.parseInt(scanner.nextLine());
                if (answer == 1) {
                    return true;
                } else if (answer == 2) {
                    return false;
                } else {
                    System.out.println("Invalid input! - 1 or 2");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! - 1 or 2");
            }
        }
    }

    public void upgradeAnimal(Player player) {
        ArrayList<String> animalsOwned = new ArrayList<>();
        ArrayList<Integer> animalLevels = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            if (animals.get(i).getOwner().equals(player.getPlayerName())) {
                animalsOwned.add(animals.get(i).getSpecies());
                animalLevels.add(animals.get(i).getLevel());
            }
        }
        boolean loop = true;
        if (player.getBalance() < 200) {
            loop = false;
        }
        Scanner scanner = new Scanner(System.in);
        boolean upgrade = false;
        while (loop) {
            try {
                System.out.println("Would you like to upgrade an animal for £200?\n1) yes\n2) no");
                int answer = Integer.parseInt(scanner.nextLine());
                if (answer == 1) {
                    loop = false;
                    upgrade = true;
                } else if (answer == 2) {
                    loop = false;
                    upgrade = false;
                } else {
                    System.out.println("Invalid input! - 1 or 2");
                }
            }
            catch (Exception e) {
                System.out.println("Invalid input! - 1 or 2");
            }
        }
        if (animalsOwned.size() > 0 && upgrade == true && !(animalLevels.get(0) == 3 && animalsOwned.size() == 1)) {
            loop = true;
            while (loop) {
                try {
                    System.out.println("Which animal would you like to upgrade?");
                    for (int i = 1; i < animalsOwned.size() + 1; i++) {
                        System.out.println(i + ") " + animalsOwned.get(i-1) + ": level: " + animalLevels.get(i-1));
                    }
                    int animal = Integer.parseInt(scanner.nextLine());
                    if (0 < animal && animal <= animalsOwned.size()) {
                        for (int i = 0; i < 24; i++) {
                            if (animals.get(i).getSpecies().equals(animalsOwned.get(animal-1))) {
                                if (animals.get(i).getLevel() < 3) {
                                    animals.get(i).upgrade();
                                    System.out.println("You upgraded your " + animalsOwned.get(animal-1) + ".\n");
                                    loop = false;
                                }
                                else {
                                    System.out.println("Your animal has reached the maximum level of 3!\n");
                                }
                            }
                        }
                    }
                    else {
                        System.out.println("Invalid input!");
                    }
                }
                catch (Exception e) {
                    System.out.println("Invalid input!");
                }
            }
        }
        else {
            System.out.println("You don't have any animals to upgrade or you can't afford it!\n");

        }
    }

    public void resetAnimals(Player player) {
        for (int i = 0; i < 24; i++) {
            if (animals.get(i).getOwner().equals(player.getPlayerName())) {
                animals.get(i).setOwner("");
                animals.get(i).setOwned(false);
                if (animals.get(i).getLevel() == 1) {
                    animals.get(i).setCostToStop(animals.get(i).getCostToStop()/2);
                }
                else if (animals.get(i).getLevel() == 2) {
                    animals.get(i).setCostToStop(animals.get(i).getCostToStop()/4);
                }
                else if (animals.get(i).getLevel() == 3) {
                    animals.get(i).setCostToStop(animals.get(i).getCostToStop()/8);
                }
                animals.get(i).setLevel(0);
            }
        }
    }


    public static void main(String[] args) {
        Game game = new Game();
        for (int i = 0; i < 20; i++) {
            game.cards.add(new Card(game.cardValues[i]));
        }
        for (int j = 0; j < 24; j++) {
            game.animals.add(new Animal(game.species[j],game.costsToBuy[j],game.costsToStop[j]));
        }
        Board board = new Board(game.players, game.animals);
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();

        System.out.println("Welcome to Animalopoly!");
        System.out.println("\n");

        while (game.winner.equals("")) {
            for (int i = 0; i < game.players.size(); i++) {
                if (game.players.get(i).getMissGo() == true) {
                    System.out.println(game.players.get(i).getPlayerName() + " has to miss a go\n");
                    game.players.get(i).setMissGo(false);
                }
                else if (game.players.get(i).getBankrupt() == true) {
                    System.out.println(game.players.get(i).getPlayerName() + " is bankrupt\n");
                    game.resetAnimals(game.players.get(i));
                    game.players.remove(game.players.get(i));
                }
                else if (game.players.size() == 1) {
                    game.winner = game.players.get(i).getPlayerName();
                    System.out.println("Game over!");
                    System.out.println(game.winner + " wins!!!");
                }
                else {
                    int roll1 = dice1.roll();
                    int roll2 = dice2.roll();
                    int move = roll1 + roll2;
                    System.out.println(game.players.get(i).getPlayerName() + " rolled " + roll1 + " and " + roll2 + " and moves " + move + " spaces");
                    System.out.println();

                    if (roll1 == roll2) {
                        if (game.collectCard(game.players.get(i).getPlayerName())) {
                            int randNum = (int)(Math.random() * 20);
                            System.out.println(game.cards.get(randNum).getMessage());
                            game.players.get(i).updateBalance(game.cards.get(randNum).getValue());
                            System.out.println("Your new balance: " + game.players.get(i).getBalance() + "\n");
                        }
                    }
                    board.movePlayer(game.players.get(i), move);
                    board.displayBoard();
                    board.giveInstructions(game.players.get(i));
                    game.upgradeAnimal(game.players.get(i));
                }
            }
        }
    }
}

