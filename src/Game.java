public class Game {

    // starte das Spiel
    public void start() {
        Player p = new Player("Maxy", true, 100, new DeckV2(1, 10, true));
        Fight f = new Fight("Game", p);
        f.battle();
    }
}
