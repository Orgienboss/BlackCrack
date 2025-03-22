public class BlackjackDun {
    public static void main(String[] args) throws Exception {
        // Deck d = new Deck(1, 2, 2, false);

        // d.drawRight();
        // d.drawRight();
        // d.drawRight();

        // d.printDeck();

        DeckV2 d2 = new DeckV2(4, 1, 10, true);
        d2.genDeck();
        for (int i = 0; i < d2.getSize(); i++) {
            d2.getCard(i).printCard();
        }
    }
}
