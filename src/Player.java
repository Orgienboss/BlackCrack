public class Player {
    String name;
    int hp;
    int posX;
    int posY;
    int points;
    int gauge = 21;
    DeckV2 deck;

    public Player(String name, int hp, DeckV2 deck) {
        this.name = name;
        this.hp = hp;
        this.deck = deck;
    }

    public int getPoints() {
        return this.points;
    }

    public void addPoints(int x) {
        points = points + x;
    }

    public void setPoints(int x) {
        points = x;
    }

    public int getHp() {
        return this.hp;
    }

    public DeckV2 getDeck() {
        return this.deck;
    }

    public int getGauge() {
        return this.gauge;
    }
}
