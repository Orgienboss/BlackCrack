public class BlackjackDun {
    public static void main(String[] args) throws Exception {
        Player p = new Player("Maxy", 100, new DeckV2(-4, 7, true));
        // Fight f = new Fight(p);
        // f.battle();
        p.getDeck().genDeck();
        for (int i = 0; i < 60; i++) {
            p.getDeck().getCard(i).printCard();
        }
    }
}
