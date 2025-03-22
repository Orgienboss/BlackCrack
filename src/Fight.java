import java.util.*;

public class Fight {
    Player player;
    DeckV2 playerdeck;
    DeckV2 enemy;
    int turn = 0;
    Scanner scanner = new Scanner(System.in);
    String input = "";

    public Fight(Player player) {// , DeckV2 enemy) {
        this.player = player;
        this.playerdeck = player.getDeck();
        this.enemy = enemy;
    }

    public boolean yesNoScan() {
        while (!input.equals("y") && !input.equals("n")) {
            System.out.print("Draw again? 'y' or 'n': ");
            input = scanner.nextLine().trim().toLowerCase();

            if (!input.equals("y") && !input.equals("n")) {
                System.out.println("Invalid input. Try again.");
            }
        }
        if (input.equals("y")) {
            return true;
        } else {
            return false;
        }

    }

    public Card draw(DeckV2 deck) {
        return deck.getCard(turn);
    }

    public void battle() {
        player.setPoints(0);
        player.getDeck().genDeck();
        player.getDeck().shuffleDeck(4);

        do {
            Card drawn = draw(playerdeck);
            input = "";
            System.out.println("Card drawn: " + drawn.getValue());
            player.addPoints(drawn.getValue());
            System.out.println("Points: " + player.getPoints());
            turn = turn + 1;
            if (player.getPoints() == player.getGauge() || player.getPoints() > player.getGauge()) {
                break;
            }
        } while (yesNoScan());

        // WARUM AKZEPTIERT ER JA NICHT

        if (player.getPoints() < player.getGauge()) {
            System.out.println("Final score: " + player.getPoints());
        } else if (player.getPoints() > player.getGauge()) {
            System.out.println("extended gauge, you lose");
        } else if (player.getPoints() == player.getGauge()) {
            System.out.println("21! Critical Hit");
        }
    }

}
