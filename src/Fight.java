public class Fight {
    int[] deck;
    int maxValue;
    int cardCount;

    public Fight(int maxValue, int cardCount) {
        this.cardCount = cardCount + 1;
        this.maxValue = maxValue;
        this.deck = new int[maxValue];
        for (int i = 0; i < deck.length; i++) {
            this.deck[i] = this.cardCount;
        }
    }

    public void draw() {
        int card;
        // System.out.println("Anzahl: " + this.deck[1]);
        do {
            card = (int) (Math.random() * this.maxValue) + 1;
            this.deck[card - 1] = this.deck[card - 1] - 1;
            // System.out.println("Count: " + this.deck[card - 1]);
        } while (this.deck[card - 1] <= 0);

        System.out.print(card + "\t");
    }
}
