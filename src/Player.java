public class Player {
    boolean isPlayer;
    String name;
    int hp;
    int points = 0;
    int gauge = 21;
    DeckV2 deck;
    boolean finalset = false;
    boolean overShot = false;
    int maxHP;
    boolean hasShield = false;
    boolean doubleDamage = false;

    public Player(String name, boolean isPlayer, int hp, DeckV2 deck) {
        this.isPlayer = isPlayer;
        this.name = name;
        this.hp = hp;
        this.maxHP = hp;
        this.deck = deck;
    }

    // Getter für doppelten Schaden
    public boolean getDoubledamage() {
        return this.doubleDamage;
    }

    // Getter für Schild
    public boolean getShied() {
        return this.hasShield;
    }

    // Setter für doppelten Schaden
    public void setDoubledamage(boolean b) {
        this.doubleDamage = true;
    }

    // Setter für Schild
    public void setShield(boolean b) {
        this.hasShield = b;
    }

    // Umkehren des Schildstatus (Ein- oder Ausschalten)
    public void flipShield() {
        if (this.hasShield) {
            this.hasShield = false;
        } else {
            this.hasShield = true;
        }
    }

    // Getter für die maximalen Lebenspunkte
    public int getMaxHP() {
        return this.maxHP;
    }

    // Setter für die Lebenspunkte
    public void setHP(int h) {
        this.hp = h;
    }

    // Methode zum Zufügen von Schaden an den Spieler
    public void damage(int h) {
        // Wenn ein Schild aktiv ist, wird kein Schaden genommen
        if (hasShield) {
            return;
        } else {
            this.hp -= h;
        }
    }

    // Setzt den Spieler für eine neue Runde zurück
    public void setNewRound() {
        this.finalset = false;
        this.overShot = false;
        this.points = 0;
    }

    // Überprüft, ob der Charakter der Spieler ist
    public boolean isPlayer() {
        return this.isPlayer;
    }

    // Überprüft, ob der Charakter im "Locked-In"-Zustand ist (Finalset oder
    // Overshot)
    public boolean isLockedIn() {
        if (this.finalset || this.overShot) {
            return true;
        } else {
            return false;
        }
    }

    // Setter für den "Finalset"-Status
    public void setFinalset(boolean b) {
        this.finalset = b;
    }

    // Setter für den "Overshot"-Status
    public void setOvershot(boolean b) {
        this.overShot = b;
    }

    // Getter für den "Finalset"-Status
    public boolean isFinalset() {
        return this.finalset;
    }

    // Getter für den "Overshot"-Status
    public boolean isOvershot() {
        return this.overShot;
    }

    // Getter für die aktuellen Punkte des Charakters
    public int getPoints() {
        return this.points;
    }

    // Methode zum Hinzufügen von Punkten
    public void addPoints(int x) {
        points = points + x;
    }

    // Setter für die Punkte
    public void setPoints(int x) {
        points = x;
    }

    // Getter für die Lebenspunkte des Charakters
    public int getHp() {
        return this.hp;
    }

    // Getter für das Deck des Charakters
    public DeckV2 getDeck() {
        return this.deck;
    }

    // Getter für den aktuellen Wert des Gauges
    public int getGauge() {
        return this.gauge;
    }

    // Setter für den Gauge-Wert
    public void setGauge(int i) {
        this.gauge = i;
    }

}
