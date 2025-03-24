import java.util.Scanner;

public class Game {
    Player player;
    Dungeon dungeon;
    Scanner scanner;

    public void start() {
        Player p = new Player("Maxy", 100, new DeckV2(1, 2, false));
        Fight f = new Fight(p);
        f.battle();
    }

    // public Gui1 getGui() {
    // return g;
    // }

}
