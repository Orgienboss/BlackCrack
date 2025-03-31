public class Player {
    boolean isPlayer;
    String name;
    int hp;
    // int posX; Falls noch Dungeoncrawler kommt
    // int posY;
    int points = 0;
    int gauge = 21;
    DeckV2 deck;
    boolean finalset = false;
    boolean overShot = false;

    public Player(String name, boolean isPlayer, int hp, DeckV2 deck) {
        this.isPlayer = isPlayer;
        this.name = name;
        this.hp = hp;
        this.deck = deck;
    }

    public void setHP(int h) {
        this.hp = h;
    }

    public void damage(int h) {
        this.hp = this.hp - h;
    }

    public void setNewRound() {
        this.finalset = false;
        this.overShot = false;
        this.points = 0;
    }

    public boolean isPlayer() {
        return this.isPlayer;
    }

    public boolean isLockedIn() {
        if (this.finalset || this.overShot) {
            return true;
        } else {
            return false;
        }
    }

    public void setFinalset(boolean b) {
        this.finalset = b;
    }

    public void setOvershot(boolean b) {
        this.overShot = b;
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

}
