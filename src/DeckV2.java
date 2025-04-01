public class DeckV2 {
    Card[] deck;
    int decksize;
    int minValue;
    int maxValue;
    boolean images;
    int index = 0;

    public DeckV2(int minValue, int maxValue, boolean images) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.decksize = (maxValue - minValue + 1) * 4;
        if (images) {
            this.decksize = decksize + 3 * 4;
        }
        this.images = images;
    }

    public int getIndex() {
        return this.index;
    }

    public void addIndex(int a) {
        this.index += a;
    }

    public void setIndex(int a) {
        this.index = a;
    }

    public Card[] getCardDeck() {
        return deck;
    }

    public int getSize() {
        return this.decksize;
    }

    public Card getCard(int pos) {
        return this.deck[pos];
    }

    public void printDeck() {
        for (int i = 0; i < deck.length; i++) {
            deck[i].printCardShort();
        }
    }

    public int getMinVal() {
        return this.minValue;
    }

    public int getMaxVal() {
        return this.maxValue;
    }

    public int getMaxPossible() {
        int a = 0;
        for (int i = 0; i < deck.length; i++) {
            a = a + deck[i].getValue();
        }
        return a;
    }

    public void genDeck() {
        // System.out.println("Size: " + this.decksize);
        this.deck = new Card[this.decksize];
        int val = this.minValue;
        char rank = 'N';
        int count = 0;
        int count2 = 0;
        String s = null;

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

            if (val > this.maxValue) {
                if (count2 == 0) {
                    s = "King";
                } else if (count2 == 4) {
                    s = "Queen";
                } else if (count2 == 4 * 2) {
                    s = "Jack";
                }
                this.deck[i] = new Card(s, maxValue, rank);
                count2 = count2 + 1;
            } else {
                this.deck[i] = new Card(String.valueOf(val), val, rank);
            }

            if (count == 0) {
                val = val + 1;
            }

        }
    }

    // Durchmischung des Decks bzw Arrays (ggf. mehrmals)
    public void shuffleDeck(int factor) {
        for (int j = 0; j < factor; j++) {
            for (int i = 0; i < deck.length; i++) {
                int m = (int) (Math.random() * (this.deck.length));
                Card temp = this.deck[m];
                this.deck[m] = this.deck[i];
                this.deck[i] = temp;
            }
        }
    }

}
