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
    int enemysslain = 0;

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
        // int max;
        int max = (int) (Math.random() * 25 + (min * 4));

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
        return e;
    }

    public Card draw(DeckV2 deck) {
        Card drawn = deck.getCard(deck.getIndex());
        deck.addIndex(1);
        if (deck.getIndex() == deck.getSize()) {
            deck.genDeck();
            deck.shuffleDeck(4);
            deck.setIndex(0);
            drawn = draw(deck);
        }
        return drawn;
    }

    public void battle() {

        if (turn == 0) {
            this.enemy = genEnemy(1);
        }
        if (!(this.enemy.getHp() > 0)) {
            this.enemy = genEnemy((int) Math.random() * 3 + 1);
            newEnemyImage();
            enemysslain += 1;
        }

        generateAbilityCard();
        damageDealt = false;
        setGauge(enemy, String.valueOf(enemy.getGauge()));
        this.enemy.getDeck().genDeck();
        this.enemy.getDeck().shuffleDeck(4);
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

    public void generateAbilityCard() {
        if (abilityCooldown > 0) {
            abilityCooldown--;
            panelAbilityCard.setVisible(false);
            abilityAvailable = false;
            return;
        }

        String[] abilities = {
                "Double Damage",
                "Heal HP",
                "Shield Next Attack",
                null // Represents no ability appearing
        };

        String selectedAbility = abilities[random.nextInt(abilities.length)];

        if (selectedAbility == null) {
            panelAbilityCard.setVisible(false);
            abilityAvailable = false;
        } else {
            labelAbilityText.setText(selectedAbility);
            panelAbilityCard.setVisible(true);
            buttonUseAbility.setEnabled(true);
            abilityAvailable = true;
        }
    }

    public void useAbility() {
        if (!abilityAvailable)
            return;

        String ability = labelAbilityText.getText();

        switch (ability) {
            case "Double Damage":
                p.setDoubledamage(true);
                break;
            case "Heal HP":
                p.damage(-(random.nextInt(5, 30)));
                setHearts(p, p.getHp()); // Update UI
                break;
            case "Shield Next Attack":
                p.setShield(true);
                break;
        }

        abilityCooldown = 8; // Cooldown for 3 turns
        panelAbilityCard.setVisible(false);
        abilityAvailable = false;
    }

    public void buttonHit_ActionPerformed(ActionEvent evt) {
        generateAbilityCard();
        hit(p);
    }

    public void buttonStay_ActionPerformed(ActionEvent evt) {
        stay(p);
    }

    public void exit() {
        System.exit(0);
    }

    public void buttonRestart_ActionPerformed(ActionEvent evt) {
        Fight f = new Fight("Game", new Player("Hero", p.isPlayer(), p.getMaxHP(), new DeckV2(1, 10, true)));
        close();
        f.battle();
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
            if (!play.isPlayer()) {
                p.setFinalset(true);
            }
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
            int damage = enemy.getPoints(); // Enemy wins by default, deals damage
            p.damage(damage);
            System.out.println("Player busted! Took " + damage + " damage.");
            checkHp();
            return;
        } else if (enemy.isOvershot()) {
            int damage = p.getPoints(); // Player wins by default, deals damage
            if (p.getDoubledamage()) {
                damage *= 2;
                p.setDoubledamage(false);
            }
            enemy.damage(damage);
            System.out.println("Enemy busted! Took " + damage + " damage.");
            checkHp();
            return;
        }

        // Calculate damage based on percentage difference
        double scoreDiff = Math.abs(playerScoreP - enemyScoreP) * 2;
        if (scoreDiff < 0) {
            scoreDiff = scoreDiff * -2;
        }
        int baseDamage = (int) (scoreDiff * 10) + 3; // Convert percentage to damage
        if (p.getDoubledamage()) {
            baseDamage *= 2;
            p.setDoubledamage(false);
        }

        // Apply a bonus multiplier for near-perfect hands
        if (scoreDiff > 0.9)
            baseDamage *= 4;

        // Determine who takes damage
        if (enemyScoreP >= playerScoreP) {
            p.damage(baseDamage);
            System.out.println("Enemy wins the round! Player takes " + baseDamage + " damage.");
        } else {
            enemy.damage(baseDamage);
            System.out.println("Player wins the round! Enemy takes " + baseDamage + " damage.");
        }

        checkHp();
    }

    public void checkHp() {
        if (p.getHp() > 0) {
            roundEnd();
        } else {
            gameOver(true, enemysslain);
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
