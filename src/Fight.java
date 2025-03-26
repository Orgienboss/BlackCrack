import java.awt.event.ActionEvent;
import java.util.*;

public class Fight extends Gui1 {

    public Fight(String title, Player p) {
        super(title, p);
        // TODO Auto-generated constructor stub
    }

    // p p;
    DeckV2 pdeck = p.getDeck();
    DeckV2 eDeck;
    int turn = 0;
    Scanner scanner = new Scanner(System.in);
    String input = "";
    boolean overShotplayer = false, overShotenemy = false;
    boolean finalsetplayer = false, finalsetenemy = false;
    Player enemy;
    Random random = new Random();

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
        Player e;
        int rHp = (int) Math.random() * 100 * factor + 10;
        int min = random.nextInt(1, 20);
        System.out.println("E min: " + min);
        // int max;
        int max = (int) Math.random() * 35 + min * 2;
        System.out.println("E max: " + max);

        int img = random.nextInt(0, 10);
        boolean img2;
        if (img > 5) {
            img2 = false;
        } else {
            img2 = true;
        }

        DeckV2 eDeck = new DeckV2(min, max, img2);
        eDeck.genDeck();

        e = new Player("Enemy", rHp, eDeck);

        e.setGauge((e.getDeck().getMaxVal() + e.getDeck().getMinVal() * 11));
        return e;
    }

    public Card draw(DeckV2 deck) {
        return deck.getCard(turn);
    }

    public void battle() {
        this.enemy = genEnemy((int) Math.random() * 3 + 1);
        this.enemy.getDeck().genDeck();
        System.out.println(enemy.getGauge());
        // this.enemy.getDeck().shuffleDeck(4);
        for (int i = 0; i < enemy.getDeck().getCardDeck().length; i++) {
            enemy.getDeck().getCard(i).printCardShort();
        }

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

    public void buttonHit_ActionPerformed(ActionEvent evt) {
        hit(p);
    }

    public void buttonStay_ActionPerformed(ActionEvent evt) {
        stay(p);
    }

    public void hit(Player play) {
        Card drawn;

        if (!play.isFinalset() && !play.isFinalset()) {
            do {
                drawn = draw(play.getDeck());
            } while (drawn.isDrawn());
            drawn.setDrawn();
            setCarddrawn(drawn.toStringShort());
            if (drawn.getValue() == play.getDeck().getMinVal()
                    && play.getPoints() + drawn.getValue() * 11 <= play.getGauge()) {
                drawn.action(play, 11);
            } else {
                drawn.action(play);
            }
            System.out.println("Points: " + play.getPoints());
            setScore(String.valueOf(play.getPoints()));
            turn = turn + 1;
        }

        if (p.getPoints() > play.getGauge()) {
            overShot();
        } else if (play.getPoints() == play.getGauge()) {
            setFinal();
        }
        // enemyTurn();
        setRound(turn);
    }

    public void stay(Player play) {
        System.out.println("Final score: " + play.getPoints());
        setFinal();
        do {
            // enemyTurn();
            setRound(turn);
        } while (!enemy.isFinalset() && !enemy.isOvershot());

    }

    public void enemyTurn() {
        if (enemy.getPoints() < enemy.getGauge() - (4 * enemy.getDeck().getMinVal())) {
            hit(enemy);
        } else {
            stay(enemy);
        }
    }

}
