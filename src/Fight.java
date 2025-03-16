public class Fight {
    int[] deck;
    int minValue;
    int maxValue;
    int cardCount;

    public Fight(int minValue, int maxValue, int cardCount, boolean images) {
        this.cardCount = cardCount;
        this.minValue = minValue;
        this.maxValue = maxValue;

        if (images) {
            this.deck = new int[1 + maxValue - minValue + 3];
        }
        this.deck = new int[1 + maxValue - minValue]; // D B und K fehlen

        for (int i = 0; i < deck.length; i++) {
            this.deck[i] = this.cardCount;
        }
    }

    public int draw() {
        int card;
        card = (int) (Math.random() * this.maxValue) + this.minValue;
        return card;
    }

    public void drawRight() {
        int card;

        do {
            card = draw();
            this.deck[card - 1] = this.deck[card - 1] - 1;
        } while (this.deck[card - 1] < 0);

        System.out.print(card + "\t");
        // System.out.print("Anzahl: " + this.deck[card - 1] + "\n");
    }
}
