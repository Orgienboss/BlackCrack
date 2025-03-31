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
    int carddrawer = 0;

    // Gui1 g = new Gui1("Game", p);

    // public Fight(p p) {// , DeckV2 enemy) {
    // this.p = p;
    // this.pdeck = p.getDeck();
    // this.enemy = enemy;
    // }

    // public boolean yesNoScan() {
    // while (!input.equals("y") && !input.equals("n")) {
    // System.out.print("Draw again? 'y' or 'n': ");
    // input = scanner.nextLine().trim().toLowerCase();

    // if (!input.equals("y") && !input.equals("n")) {
    // }
    // }
    // if (input.equals("y")) {
    // return true;
    // } else {
    // return false;
    // }

    // }

    public Player genEnemy(int factor) {
        Player e;
        int rHp = (int) (Math.random() * 100 * factor) + 10;
        int min = random.nextInt(1, 3);
        System.out.println(min);
        // int max;
        int max = (int) (Math.random() * 25 + (min * 4));
        System.out.println(max);

        int img = random.nextInt(0, 10);
        boolean img2;
        if (img > 5) {
            img2 = false;
        } else {
            img2 = true;
        }

        DeckV2 eDeck = new DeckV2(min, max, img2);
        eDeck.genDeck();

        e = new Player("Enemy", false, rHp, eDeck);

        e.setGauge((e.getDeck().getMaxVal() + (e.getDeck().getMinVal() * 11)));
        System.out.println("e.Gauge: " + e.getGauge());
        return e;
    }

    public Card draw(DeckV2 deck) {
        Card drawn = deck.getCard(carddrawer);
        carddrawer = carddrawer + 1;
        if (drawn == null || carddrawer == deck.getSize()) {
            deck.genDeck();
            deck.shuffleDeck(4);
            drawn = draw(deck);
        }
        return drawn;
    }

    public void battle() {

        if (turn == 0) {
            this.enemy = genEnemy(1);
        }
        if (this.enemy.getHp() <= 0 || this.enemy == null) {
            this.enemy = genEnemy((int) Math.random() * 3 + 1);
        }

        damageDealt = false;
        setGauge(enemy, String.valueOf(enemy.getGauge()));
        this.enemy.getDeck().genDeck();
        this.enemy.getDeck().printDeck();
        System.out.println("---------------");
        this.enemy.getDeck().shuffleDeck(4);
        this.enemy.getDeck().printDeck();
        this.enemy.setNewRound();
        setScore(enemy, "");
        setHearts(p, p.getHp());
        setHearts(enemy, enemy.getHp());
        p.setNewRound();
        setScore(p, "");
        p.getDeck().genDeck();
        p.getDeck().shuffleDeck(4);

        setGauge(p, String.valueOf(p.getGauge()));

        hit(p);
        // hit(enemy);
    }

    public void buttonHit_ActionPerformed(ActionEvent evt) {
        hit(p);
    }

    public void buttonStay_ActionPerformed(ActionEvent evt) {
        stay(p);
    }

    public void hit(Player play) {

        if (play.isLockedIn())
            return; // Don't allow hitting after staying

        Card drawn;
        do {
            drawn = draw(play.getDeck());
        } while (drawn.isDrawn());

        drawn.setDrawn();
        setCarddrawn(play, drawn.toStringShort());

        // Check if the card can be treated as an Ace (special rule)
        if (drawn.getValue() == play.getDeck().getMinVal() &&
                play.getPoints() + drawn.getValue() * 11 <= play.getGauge()) {
            drawn.action(play, 11);
        } else {
            drawn.action(play);
        }

        setScore(play, String.valueOf(play.getPoints()));

        // Handle Overshot (Bust) Condition
        if (play.getPoints() > play.getGauge()) {
            play.setOvershot(true);
            overShot(play);
            stay(play);
        }
        // Handle Perfect Hand (Instant Lock-In)
        else if (play.getPoints() == play.getGauge()) {
            setFinal(play);
            damageDeal();
        }

        // Enemy takes its turn after the player hits
        if (play.isPlayer() && !enemy.isLockedIn()) {
            enemyTurn();
            turn++;
        } else if (!play.isPlayer() && !enemy.isLockedIn() && p.isLockedIn()) {
            enemyTurn();
            turn++;
        }

        setRound(turn);

        // Card drawn;

        // if (!play.isLockedIn()) {
        // do {
        // drawn = draw(play.getDeck());
        // } while (drawn.isDrawn());
        // drawn.setDrawn();
        // setCarddrawn(play, drawn.toStringShort());
        // if (drawn.getValue() == play.getDeck().getMinVal()
        // && play.getPoints() + (drawn.getValue() * 11) <= play.getGauge()) {
        // drawn.action(play, 11);
        // } else {
        // drawn.action(play);
        // }
        // setScore(play, String.valueOf(play.getPoints()));

        // }

        // if (play.getPoints() > play.getGauge()) {
        // overShot(play);
        // play.setOvershot(true);
        // damageDeal();
        // // stay(play);
        // // do {
        // // enemyTurn();
        // // setRound(turn);
        // // } while (!enemy.isFinalset() && !enemy.isOvershot());
        // } else if (play.getPoints() == play.getGauge()) {
        // setFinal(play);
        // damageDeal();
        // }

        // if (play.isPlayer || (!enemy.isLockedIn() && p.isFinalset())) {
        // enemyTurn();
        // turn = turn + 1;
        // }

        // setRound(turn);
    }

    public void stay(Player play) {

        if (!play.isLockedIn()) {
            setFinal(play);
            play.setFinalset(true);
        }

        // If the player stays, the enemy should act
        if (play.isPlayer() && !enemy.isLockedIn()) {
            enemyTurn();
        }

        // If both have locked in, process damage calculation
        if (p.isLockedIn() && enemy.isLockedIn() && play.isPlayer()) {
            damageDeal();
        }

        // if (!play.isOvershot()) {
        // setFinal(play);
        // play.setFinalset(true);
        // }
        // if (play.isPlayer) {
        // // do {
        // if (!enemy.isLockedIn()) {
        // enemyTurn();
        // setRound(turn);
        // }

        // // } while (!enemy.isFinalset() && !enemy.isOvershot());
        // }

        // if (p.isLockedIn() && enemy.isLockedIn() && play.isPlayer()) {
        // damageDeal();
        // }
    }

    public void enemyTurn() {
        // if (enemy.getPoints() < (enemy.getGauge() - (2 *
        // enemy.getDeck().getMinVal()))) {
        // hit(enemy);
        // } else {
        // stay(enemy);
        // }

        double enemyScoreP = (double) enemy.getPoints() / enemy.getGauge();

        if (enemyScoreP < 0.75) {
            // If below 75% of max gauge, enemy takes more risks
            hit(enemy);
        } else if (enemyScoreP >= 0.75 && enemyScoreP < 0.90) {
            // If between 75%-90%, enemy has a chance to stay
            if (random.nextBoolean()) { // 50% chance to hit
                hit(enemy);
            } else {
                stay(enemy);
            }
        } else {
            // If at 90% or more, enemy always stays
            stay(enemy);
        }
    }

    public void damageDeal() {

        if (damageDealt)
            return; // Prevent multiple calls per round
        damageDealt = true;

        // Calculate percentage of each player's hand relative to their max gauge
        double playerScoreP = (double) p.getPoints() / p.getGauge();
        double enemyScoreP = (double) enemy.getPoints() / enemy.getGauge();

        // Handle overshot cases first
        if (p.isOvershot()) {
            int damage = enemy.getPoints() / 2; // Enemy wins by default, deals damage
            p.damage(damage);
            System.out.println("Player busted! Took " + damage + " damage.");
            roundEnd();
            return;
        } else if (enemy.isOvershot()) {
            int damage = p.getPoints() / 2; // Player wins by default, deals damage
            enemy.damage(damage);
            System.out.println("Enemy busted! Took " + damage + " damage.");
            roundEnd();
            return;
        }

        // Calculate damage based on percentage difference
        double scoreDiff = Math.abs(playerScoreP - enemyScoreP);
        if (scoreDiff < 0) {
            scoreDiff = scoreDiff * -2;
        }
        int baseDamage = (int) (scoreDiff * 10); // Convert percentage to damage

        // Apply a bonus multiplier for near-perfect hands
        if (scoreDiff > 0.9)
            baseDamage *= 2;

        // Determine who takes damage
        if (enemyScoreP >= playerScoreP) {
            p.damage(baseDamage);
            System.out.println("Enemy wins the round! Player takes " + baseDamage + " damage.");
        } else {
            enemy.damage(baseDamage);
            System.out.println("Player wins the round! Enemy takes " + baseDamage + " damage.");
        }

        // End the round if the player is still alive
        if (p.getHp() > 0) {
            roundEnd();
        }
    }
    // double playerScoreP = (double) p.getPoints() / p.getGauge();
    // System.out.println(playerScoreP);
    // double enemyScoreP = (double) enemy.getPoints() / enemy.getGauge();
    // System.out.println(enemyScoreP);

    // if (this.p.isOvershot()) {
    // int a = this.enemy.getPoints() * 2;
    // this.p.damage(a);

    // } else if (this.enemy.isOvershot()) {
    // int a = this.p.getPoints() * 2;
    // this.enemy.damage(a);

    // } else if (enemyScoreP > playerScoreP || enemyScoreP == playerScoreP) {
    // int a = this.enemy.getPoints() - this.p.getPoints();
    // if (a < 2) {
    // a = 2;
    // }
    // if (enemyScoreP == 1) {
    // a = a * 2;
    // }
    // this.p.damage(a * 2);
    // } else if (enemyScoreP < playerScoreP) {
    // int a = this.p.getPoints() - this.enemy.getPoints();
    // if (a < 2) {
    // a = 2;
    // }
    // if (playerScoreP == 1) {
    // a = a * 2;
    // }
    // this.enemy.damage(a);
    // }

    // if (this.p.getHp() > 0) {
    // roundEnd();
    // }
    // }

    // @Override
    public void buttonContinue_ActionPerformed(ActionEvent evt) {
        roundReset();
        p.setPoints(0);
        enemy.setPoints(0);
        battle();
    }

}
