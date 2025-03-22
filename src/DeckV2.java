import java.util.*;

public class DeckV2 {
    Card[] deck;
    int decksize;
    int cardcount;
    int minValue;
    int maxValue;
    boolean images;

    public DeckV2(int cardCount, int minValue, int maxValue, boolean images) {
        this.cardcount = cardCount;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.decksize = (maxValue - minValue + 1) * cardCount;
        if (images) {
            this.decksize = decksize + 3 * cardCount;
        }
        this.images = images;
    }

    public int getSize() {
        return this.decksize;
    }

    public Card getCard(int pos) {
        return this.deck[pos];
    }

    public void genDeck() {
        System.out.println("Size: " + this.decksize);
        this.deck = new Card[this.decksize];
        int val = 1;
        char rank = 'N';
        int count = 0;
        for (int i = 0; i < deck.length; i++) {
            switch (count) {
                case 0:
                    rank = 'H';
                    count = count + 1;
                    break;

                case 1:
                    rank = 'D';
                    count = count + 1;
                    break;

                case 2:
                    rank = 'S';
                    count = count + 1;
                    break;

                case 3:
                    rank = 'C';
                    count = 0;
                    break;

                default:
                    break;
            }

            this.deck[i] = new Card(String.valueOf(val), val, rank);
            if (count == 0) {
                val = val + 1;
            }

            // KÖNIG DAME UN BUBE MACHEN

        }
    }
}
