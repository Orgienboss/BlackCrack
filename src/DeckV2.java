public class DeckV2 {

    private Card[] deck;
    private int decksize;
    private int minValue;
    private int maxValue;
    private int index = 0;

    /**
     * Konstruktor zum Erstellen eines Decks mit bestimmten Kartenwerten und
     * Bildern.
     * 
     * @param minValue Der minimale Kartenwert.
     * @param maxValue Der maximale Kartenwert.
     * @param images   Bestimmt, ob Bildkarten hinzugefügt werden sollen.
     */
    public DeckV2(int minValue, int maxValue, boolean images) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.decksize = (maxValue - minValue + 1) * 4 + (images ? 12 : 0);
    }

    // Gibt den aktuellen Index zurück
    public int getIndex() {
        return index;
    }

    // Fügt dem aktuellen Index einen Wert hinzu
    public void addIndex(int a) {
        index += a;
    }

    // Setzt den Index auf einen bestimmten Wert
    public void setIndex(int a) {
        index = a;
    }

    // Gibt das Kartendeck zurück
    public Card[] getCardDeck() {
        return deck;
    }

    // Gibt die Deckgröße zurück
    public int getSize() {
        return decksize;
    }

    // Gibt eine Karte an einer bestimmten Position im Deck zurück
    public Card getCard(int pos) {
        return deck[pos];
    }

    // Gibt den minimalen Kartenwert zurück
    public int getMinVal() {
        return minValue;
    }

    // Gibt den maximalen Kartenwert zurück
    public int getMaxVal() {
        return maxValue;
    }

    // Berechnet den maximal möglichen Kartenwert im Deck (Summe der Werte aller
    // Karten)
    public int getMaxPossible() {
        int sum = 0;
        for (Card c : deck) {
            sum += c.getValue();
        }
        return sum;
    }

    /**
     * Erstellt ein Deck mit Kartenwerten von minValue bis maxValue.
     * Falls Bilderkarten aktiviert sind, werden zusätzlich König, Dame und Bube
     * hinzugefügt mit maxValue.
     */
    public void genDeck() {
        deck = new Card[decksize];
        char[] suits = { 'H', 'D', 'S', 'C' }; // Symbole für die Kartenfarben (Hearts, Diamonds, Spades, Cross)
        int val = minValue, count2 = 0;

        for (int i = 0; i < decksize; i++) {
            char rank = suits[i % 4]; // Bestimmt den Rang der Karte (suit)

            if (val > maxValue) {
                // Wenn der Wert über maxValue hinausgeht, werden Bildkarten erstellt
                // (König,Dame, Bube)
                String[] faces = { "King", "Queen", "Jack" };
                deck[i] = new Card(faces[count2 / 4], maxValue, rank);
                count2++;
            } else {
                // Erstellt eine normale Karte mit Wert und Farbe
                deck[i] = new Card(String.valueOf(val), val, rank);
            }

            // Nachdem jede Karte einer Farbe zugeordnet wurde, wird der Wert erhöht
            if (i % 4 == 3)
                val++;
        }
    }

    /**
     * Mischt das Deck durch Tausch zufälliger Kartenplätze.
     * 
     * @param factor = Anzahl der Mischdurchläufe
     */
    public void shuffleDeck(int factor) {
        for (int j = 0; j < factor; j++) {
            for (int i = 0; i < deck.length; i++) {
                // Zufällige Position im Deck auswählen
                int m = (int) (Math.random() * deck.length);
                // Karten tauschen
                Card temp = deck[m];
                deck[m] = deck[i];
                deck[i] = temp;
            }
        }
    }
}
