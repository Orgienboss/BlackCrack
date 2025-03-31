import java.util.Scanner;

public class Game {
    Player player;
    Dungeon dungeon;
    Scanner scanner;
    Fight f;

    public void start() {
        Player p = new Player("Maxy", true, 100, new DeckV2(1, 10, true));
        this.player = p;
        f = new Fight("Game", p);
        f.battle();
    }

    // public Gui1 getGui() {
    // return g;
    // }

}
