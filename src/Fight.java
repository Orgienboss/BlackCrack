import java.awt.event.ActionEvent;
import java.util.*;

public class Fight extends Gui1 {

    public Fight(String title, Player p) {
        super(title, p);
        // TODO Auto-generated constructor stub
    }

    // p p;
    DeckV2 pdeck = p.getDeck();
    DeckV2 enemy;
    int turn = 0;
    Scanner scanner = new Scanner(System.in);
    String input = "";
    boolean overShotplayer = false, overShotenemy = false;
    boolean finalsetplayer = false, finalsetenemy = false;
    // Gui1 g = new Gui1("Game", p);

    // public Fight(p p) {// , DeckV2 enemy) {
    // this.p = p;
    // this.pdeck = p.getDeck();
    // this.enemy = enemy;
    // }

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

    public Player genEnemy(int factor) {
        Player e; // Gauge
        int rHp = (int) Math.random() * 100 * factor + 10;
        int b;

        int min = (int) Math.random() * 20 - 10;
        int max = (int) Math.random() * 35 + min * 2;
        int img = (int) Math.random();
        boolean img2;
        if (img > 0.5) {
            img2 = false;
        } else {
            img2 = true;
        }

        DeckV2 eDeck = new DeckV2(min, max, img2);
        e = new Player("Enemy", rHp, eDeck);
        do {
            b = (int) Math.random();
        } while (b < 1 && b >= 0.1);
        e.setGauge(e.getDeck().getMaxVal() * b);
        return e;
    }

    public Card draw(DeckV2 deck) {
        return deck.getCard(turn);
    }

    public void battle() {
        p.setPoints(0);
        p.getDeck().genDeck();
        p.getDeck().shuffleDeck(4);
        Card drawn;
        setGauge(String.valueOf(p.getGauge()));

        do {
            do {
                drawn = draw(pdeck);
            } while (drawn.isDrawn());
            drawn.setDrawn();
            input = "";
            System.out.println("Card drawn: " + drawn.getValue());
            setCarddrawn(drawn.toStringShort());

            if (drawn.getValue() == p.getDeck().getMinVal() && p.getPoints() + drawn.getValue() * 11 <= p.getGauge()) {
                drawn.action(p, 11);
            } else {
                drawn.action(p);
            }

            System.out.println("Points: " + p.getPoints());
            setScore(String.valueOf(p.getPoints()));
            turn = turn + 1;
            if (p.getPoints() == p.getGauge() || p.getPoints() > p.getGauge()) {
                break;
            }
        } while (yesNoScan());

        if (p.getPoints() < p.getGauge()) {
            System.out.println("Final score: " + p.getPoints());
        } else if (p.getPoints() > p.getGauge()) {
            System.out.println("extended gauge, you lose");
        } else if (p.getPoints() == p.getGauge()) {
            System.out.println(p.getGauge() + "! Critical Hit");
        }
    }

    public void button1_ActionPerformed(ActionEvent evt) {
        hit();
    }

    public void button2_ActionPerformed(ActionEvent evt) {
        stay();
    }

    public void hit() {
        Card drawn;

        if (!finalsetplayer && !overShotplayer) {
            do {
                drawn = draw(pdeck);
            } while (drawn.isDrawn());
            drawn.setDrawn();
            input = "";
            System.out.println("Card drawn: " + drawn.getValue());
            setCarddrawn(drawn.toStringShort());
            if (drawn.getValue() == p.getDeck().getMinVal() && p.getPoints() + drawn.getValue() * 11 <= p.getGauge()) {
                drawn.action(p, 11);
            } else {
                drawn.action(p);
            }
            System.out.println("Points: " + p.getPoints());
            setScore(String.valueOf(p.getPoints()));
            turn = turn + 1;
        }

        if (p.getPoints() > p.getGauge()) {
            overShot();
        } else if (p.getPoints() == p.getGauge()) {
            setFinal();
        }
        // enemy actions
        setRound(turn);
    }

    public void stay() {
        System.out.println("Final score: " + p.getPoints());
        setFinal();
        // enemy actions
        setRound(turn);
    }

}
