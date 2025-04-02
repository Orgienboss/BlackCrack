public class Card {

    private final int value;
    private final String name;
    private final char rank;
    private boolean drawn = false;

    /**
     * Konstruktor zum Erstellen einer Karte mit einem Namen, Wert und Symbol.
     * 
     * @param name  Der Name der Karte (z.B. "2", "King").
     * @param value Der Wert der Karte (z.B. 2, 6, 10 für Bildkarten).
     * @param rank  Das Symbol der Karte (z.B. 'H' für Hearts, 'D' für Diamonds).
     */
    public Card(String name, int value, char rank) {
        this.value = value;
        this.name = name;
        this.rank = rank;
    }

    // Setzt den Status der Karte, ob sie gezogen wurde oder nicht
    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    // Gibt zurück, ob die Karte bereits gezogen wurde
    public boolean isDrawn() {
        return drawn;
    }

    // Gibt die Karte als String in kürzerer Form zurück (z.B. "König H").
    public String toStringShort() {
        return rank + " " + name;
    }

    // Gibt den Wert der Karte zurück
    public int getValue() {
        return value;
    }

    /**
     * Führt eine Aktion aus, bei der die Kartenpunkte zum Spieler hinzugefügt
     * werden.
     * 
     * @param player Der Spieler, dem die Punkte hinzugefügt werden.
     */
    public void action(Player player) {
        player.addPoints(value);
    }

    /**
     * Führt eine Aktion aus, bei der die Kartenpunkte mit einem Multiplikator zum
     * Spieler
     * hinzugefügt werden.
     * 
     * @param player Der Spieler, dem die Punkte hinzugefügt werden.
     * @param factor Der Multiplikator für die Punkte.
     */
    public void action(Player player, int factor) {
        player.addPoints(value * factor);
    }
}
