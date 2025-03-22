import java.util.*;

public class Deck {
    String[] deck;
    int minValue;
    int maxValue;
    int cardCount;
    boolean images;
    HashMap<String, Integer> werte = new HashMap<String, Integer>();

    // Angaben wie groß das Deck sein soll + Generation
    public Deck(int minValue, int maxValue, int cardCount, boolean imageCards) {
        this.cardCount = cardCount;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.images = imageCards;
        int temp = 0, temp2 = 0;

        if (imageCards) {
            werte.put("K", 10);
            werte.put("D", 10);
            werte.put("J", 10);

            this.deck = new String[(1 + maxValue - minValue + 3) * cardCount];
            for (int i = 0; i < deck.length - (3 * this.cardCount); i++) {
                this.deck[i] = String.valueOf(this.minValue + temp2);
                temp = temp + 1;
                if (temp == this.cardCount) {
                    temp = 0;
                    temp2 = temp2 + 1;
                }
            }
            for (int i = this.deck.length - (3 * this.cardCount); i < this.deck.length; i++) {
                if (i < this.deck.length - (2 * this.cardCount)) {
                    this.deck[i] = "K";
                } else if (i < this.deck.length - this.cardCount) {
                    this.deck[i] = "D";
                } else if (i < this.deck.length) {
                    this.deck[i] = "J";
                }
            }
        } else {
            this.deck = new String[(1 + maxValue - minValue) * cardCount];
            for (int i = 0; i < deck.length; i++) {
                this.deck[i] = String.valueOf(this.minValue + temp2);
                temp = temp + 1;
                if (temp == this.cardCount) {
                    temp = 0;
                    temp2 = temp2 + 1;
                }
            }
        }
    }

    public String draw() {
        String card;
        if (images) {
            card = this.deck[(int) (Math.random() * (this.maxValue + 3 * this.cardCount)) + this.minValue];
        } else {
            card = this.deck[(int) (Math.random() * this.maxValue) + this.minValue];
        }
        return card;
    }

    public void removeCard(String card) {
        for (int i = 0; i < this.deck.length; i++) {
            if (this.deck[i] == card) {
                this.deck[i] = null;
                break;
            }
        }
    }

    public void drawRight() {
        String card;

        card = draw();
        if (card == null) {
            drawRight();
        }
        removeCard(card);

        System.out.println(card);
    }

    public void printDeck() {
        System.out.println();
        for (int i = 0; i < deck.length; i++) {
            System.out.println(deck[i]);
        }
    }
}
