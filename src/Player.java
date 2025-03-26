public class Player {
    String name;
    int hp;
    // int posX; Falls noch Dungeoncrawler kommt
    // int posY;
    int points;
    int gauge = 21;
    DeckV2 deck;
    boolean finalset = false;
    boolean overShot = false;

    public Player(String name, int hp, DeckV2 deck) {
        this.name = name;
        this.hp = hp;
        this.deck = deck;
    }

    public boolean isFinalset() {
        return this.finalset;
    }

    public boolean isOvershot() {
        return this.overShot;
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

    public void setGauge(int i) {
        this.gauge = i;
    }

    public Player genEnemy(int factor) {
        Player e; // Gauge
        int rHp = (int) Math.random() * 100 * factor + 10;
        int b;

        int min = (int) Math.random() * 20 - 10;
        int max = (int) Math.random() * 35 + min * 2;
        int img = (int) Math.random();
        boolean img2;
        if (img > 0.5) {
            img2 = false;
        } else {
            img2 = true;
        }

        DeckV2 eDeck = new DeckV2(min, max, img2);
        e = new Player("Enemy", rHp, eDeck);
        do {
            b = (int) Math.random();
        } while (b < 1 && b >= 0.1);
        e.setGauge(e.getDeck().getMaxVal() * b);
        return e;
    }
}
