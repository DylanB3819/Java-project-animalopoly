public class Animal {
    private String species;
    private int level;
    private int costToBuy;
    private int costToStop;
    private String owner;
    private boolean owned;

    public Animal(String species, int costToBuy, int costToStop) {
        this.species = species;
        this.level = 0;
        this.costToBuy = costToBuy;
        this.costToStop = costToStop;
        this.owner = "";
        this.owned = false;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String newOwner) {
        this.owner = newOwner;
    }

    public boolean getOwned() {
        return this.owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int newLevel) {
        this.level = newLevel;
    }

    public int getCostToBuy() {
        return this.costToBuy;
    }

    public void setCostToBuy(int newCost) {
        this.costToBuy = newCost;
    }

    public int getCostToStop() {
        return this.costToStop;
    }

    public void setCostToStop(int newCost) {
        this.costToStop = newCost;
    }

    public void upgrade() {
        this.level += 1;
        costToStop *= 2;
    }
    public String getSpecies() {
        return this.species;
    }
}
