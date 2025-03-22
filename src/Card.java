public class Card {
    int value;
    String name;
    char rank;

    public Card(String name, int value, char rank) {
        this.value = value;
        this.name = name;
        this.rank = rank;
    }

    public void printCard() {
        System.out.println(name + " of " + rank + "\t Value: " + this.value);
    }

    public void printCardShort() {
        System.out.println(rank + " " + name);
    }

    public int getValue() {
        return this.value;
    }
}
