public class Card {
    int value;
    String name;
    char rank;
    boolean drawn = false;

    public Card(String name, int value, char rank) {
        this.value = value;
        this.name = name;
        this.rank = rank;
    }

    public void setDrawn() {
        this.drawn = true;
    }

    public void setDrawn(boolean b) {
        this.drawn = b;
    }

    public boolean isDrawn() {
        return drawn;
    }

    public void printCard() {
        System.out.println(name + " of " + rank + "\t Value: " + this.value);
    }

    public void printCardShort() {
        System.out.println(rank + " " + name);
    }

    public String toStringShort() {
        String s = rank + " " + name;
        return s;
    }

    public int getValue() {
        return this.value;
    }

    public void action(Player player) {
        player.addPoints(value);
    }

    public void action(Player player, int factor) {
        player.addPoints(value * factor);
    }
}
