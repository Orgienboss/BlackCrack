public class BlackjackDun {
    public static void main(String[] args) throws Exception {
        Player p = new Player("Maxy", 100, new DeckV2(1, 10, true));
        Fight f = new Fight(p);
        f.battle();
    }
}
