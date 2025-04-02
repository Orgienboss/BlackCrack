import java.awt.event.ActionEvent;
import java.util.*;

public class Fight extends Gui1 {

    // Konstruktor
    public Fight(String title, Player p) {
        super(title, p);
    }

    // Instanzvariablen
    DeckV2 pdeck = p.getDeck(); // Deck des Spielers
    DeckV2 eDeck; // Deck des Gegners
    int turn = 0; // Aktuelle Runde
    Scanner scanner = new Scanner(System.in);
    String input = "";
    boolean overShotplayer = false, overShotenemy = false;
    boolean finalsetplayer = false, finalsetenemy = false;
    Player enemy; // Gegner-Player
    Random random = new Random(); // Zufallsgenerator für zufällige Aktionen
    int carddrawer = 0;
    int enemysslain = 0; // Anzahl der besiegten Gegner

    // Generiert einen neuen Gegner mit zufälligen Werten
    public Player genEnemy(int factor) {
        Player e;
        int rHp = (int) (Math.random() * 100 * factor) + 10; // Zufällige HP
        int min = random.nextInt(1, 3); // Zufälliger Minimalwert für das Deck
        int max = (int) (Math.random() * 25 + (min * 4)); // Zufälliger Maximalwert für das Deck

        boolean img2 = random.nextInt(0, 10) <= 5; // Zufällige Bildwahl für den Gegner
        eDeck = new DeckV2(min, max, img2); // Erstellen des Decks des Gegners
        eDeck.genDeck(); // Deck des Gegners generieren

        e = new Player("Enemy", false, rHp, eDeck); // Neuer Gegner-Player

        // Setzt die Anzeigeleiste des Gegners
        e.setGauge((e.getDeck().getMaxVal() + (e.getDeck().getMinVal() * 11)));
        return e;
    }

    // Zieht eine Karte aus einem Deck
    public Card draw(DeckV2 deck) {
        Card drawn = deck.getCard(deck.getIndex());

        deck.addIndex(1); // Erhöht den Index, um die nächste Karte zu ziehen
        if (deck.getIndex() == deck.getSize()) {
            deck.genDeck(); // Deck neu generieren
            deck.shuffleDeck(4); // Deck mischen
            deck.setIndex(0); // Zurück zum ersten Index
            drawn = draw(deck); // Rekursive Ziehung einer neuen Karte
        }
        return drawn;
    }

    // Der Hauptkampfmechanismus
    public void battle() {

        if (turn == 0) {
            this.enemy = genEnemy(1); // Wenn es die erste Runde ist, generiere einen Gegner
        }
        if (!(this.enemy.getHp() > 0)) {
            this.enemy = genEnemy((int) Math.random() * 3 + 1); // Gegner neu generieren, wenn er besiegt wurde
            newEnemyImage();
            enemysslain += 1; // Zähle die besiegten Gegner
        }

        // Generiere eine Fähigkeitskarte
        generateAbilityCard();
        damageDealt = false; // Rücksetzen des Schadens
        setGauge(enemy, String.valueOf(enemy.getGauge())); // Setze die Anzeigeleiste des Gegners
        this.enemy.getDeck().genDeck();
        this.enemy.getDeck().shuffleDeck(4);
        this.enemy.setNewRound(); // Starte eine neue Runde für den Gegner
        setScore(enemy, "");
        setHearts(p, p.getHp()); // Setze die Lebensanzeige des Spielers
        setHearts(enemy, enemy.getHp()); // Setze die Lebensanzeige des Gegners
        p.setNewRound(); // Starte eine neue Runde für den Spieler
        setScore(p, "");
        p.getDeck().genDeck(); // Deck des Spielers neu generieren
        p.getDeck().shuffleDeck(4);

        setGauge(p, String.valueOf(p.getGauge())); // Setze die Anzeigeleiste des Spielers

        hit(p); // Spieler führt seinen Zug aus
    }

    // Generiert eine Fähigkeitskarte für den Spieler
    public void generateAbilityCard() {
        if (abilityCooldown > 0) {
            abilityCooldown--; // Verringere den Cooldown
            panelAbilityCard.setVisible(false); // Verberge die Fähigkeitskarte
            abilityAvailable = false;
            return;
        }

        String[] abilities = {
                "Double damage", // Doppelschaden
                "Heal HP", // Heile HP
                "Shield next attack", // Schutz für den nächsten Angriff
                null // Keine Fähigkeit wird angezeigt
        };

        // Wähle zufällig eine Fähigkeit aus
        String selectedAbility = abilities[random.nextInt(abilities.length)];

        if (selectedAbility == null) {
            panelAbilityCard.setVisible(false); // Verberge die Karte, wenn keine Fähigkeit ausgewählt wurde
            abilityAvailable = false;
        } else {
            labelAbilityText.setText(selectedAbility); // Setze die Fähigkeitsbeschreibung
            panelAbilityCard.setVisible(true); // Zeige die Fähigkeitskarte
            buttonUseAbility.setEnabled(true); // Ermögliche die Benutzung der Fähigkeit
            abilityAvailable = true;
        }
    }

    // Wende die ausgewählte Fähigkeit an
    @Override
    public void useAbility() {
        if (!abilityAvailable)
            return; // Wenn keine Fähigkeit verfügbar ist, tue nichts

        String ability = labelAbilityText.getText();

        // Je nach gewählter Fähigkeit wird eine Aktion ausgeführt
        switch (ability) {
            case "Double damage":
                p.setDoubledamage(true); // Doppelschaden aktivieren
                break;
            case "Heal HP":
                p.damage(-(random.nextInt(5, 30))); // Heile eine zufällige Menge HP
                setHearts(p, p.getHp()); // Aktualisiere die Lebensanzeige des Spielers
                break;
            case "Shield next attack":
                p.setShield(true); // Aktiviere den Schutz für den nächsten Angriff
                break;
        }

        abilityCooldown = 8; // Setze den Cooldown auf 8 Runden
        panelAbilityCard.setVisible(false); // Verberge die Fähigkeitskarte
        abilityAvailable = false; // Deaktiviere die Verfügbarkeit
    }

    // Aktion für den "Hit"-Button
    @Override
    public void buttonHit_ActionPerformed(ActionEvent evt) {
        generateAbilityCard(); // Generiere eine neue Fähigkeitskarte
        hit(p); // Spieler führt seinen Zug aus
    }

    // Aktion für den "Stay"-Button
    @Override
    public void buttonStay_ActionPerformed(ActionEvent evt) {
        stay(p); // Spieler bleibt stehen
    }

    // Beendet das Spiel
    public void exit() {
        System.exit(0);
    }

    // Aktion für den "Restart"-Button
    @Override
    public void buttonRestart_ActionPerformed(ActionEvent evt) {
        Fight f = new Fight("Game", new Player("Hero", p.isPlayer(), p.getMaxHP(), new DeckV2(1, 10, true)));
        close(); // Schließe das aktuelle Spiel
        f.battle(); // Starte ein neues Spiel
    }

    // Der Spieler zieht eine Karte
    public void hit(Player play) {

        if (play.isLockedIn())
            return; // Der Spieler kann nicht mehr ziehen, wenn er sich entschieden hat

        Card drawn;
        do {
            drawn = draw(play.getDeck()); // Ziehe eine Karte aus dem Deck
        } while (drawn.isDrawn()); // Stelle sicher, dass die Karte noch nicht gezogen wurde

        drawn.setDrawn(true); // Markiere die Karte als gezogen
        setCarddrawn(play, drawn.toStringShort()); // Zeige die gezogene Karte an

        // Überprüfe, ob die Karte als Ass behandelt werden kann (besondere Regel)
        if (drawn.getValue() == play.getDeck().getMinVal() &&
                play.getPoints() + drawn.getValue() * 11 <= play.getGauge()) {
            drawn.action(play, 11); // Behandle das Ass als 11 Punkte
        } else {
            drawn.action(play); // Behandle die Karte mit ihrem normalen Wert
        }

        setScore(play, String.valueOf(play.getPoints())); // Zeige den aktuellen Punktestand an

        // Überprüfe, ob der Spieler "überzogen" hat (Bust)
        if (play.getPoints() > play.getGauge()) {
            play.setOvershot(true); // Setze den "überzogen"-Status
            overShot(play); // Verarbeite den "überzogen"-Fall
            stay(play); // Der Spieler bleibt stehen
        }
        // Überprüfe, ob der Spieler eine perfekte Hand hat
        else if (play.getPoints() == play.getGauge()) {
            setFinal(play); // Setze die Hand als final
            if (!play.isPlayer()) {
                p.setFinalset(true);
                setFinal(p); // Setze auch die Hand des Spielers als final
            }
            damageDeal(); // Berechne den Schaden
        }

        // Der Gegner macht seinen Zug nach dem Spieler
        if (play.isPlayer() && !enemy.isLockedIn()) {
            enemyTurn(); // Der Gegner führt seinen Zug aus
            turn++; // Erhöhe die Runde
        } else if (!play.isPlayer() && !enemy.isLockedIn() && p.isLockedIn()) {
            enemyTurn(); // Der Gegner führt seinen Zug aus
            turn++; // Erhöhe die Runde
        }

        setRound(turn); // Setze die aktuelle Runde
    }

    // Der Spieler bleibt stehen
    public void stay(Player play) {

        if (!play.isLockedIn()) {
            setFinal(play); // Setze die Hand als final
            play.setFinalset(true); // Setze den Status des Spielers als final
        }

        // Wenn der Spieler bleibt, sollte der Gegner seinen Zug machen
        if (play.isPlayer() && !enemy.isLockedIn()) {
            enemyTurn(); // Der Gegner führt seinen Zug aus
        }

        // Wenn beide Spieler ihre Züge abgeschlossen haben, berechne den Schaden
        if (p.isLockedIn() && enemy.isLockedIn() && play.isPlayer()) {
            damageDeal();
        }

    }

    // Der Gegner führt seinen Zug aus
    public void enemyTurn() {

        double enemyScoreP = (double) enemy.getPoints() / enemy.getGauge();

        if (enemyScoreP < 0.75) {
            // Wenn der Gegner weniger als 75% seines maximalen Werts hat, wird er
            // risikofreudiger
            hit(enemy);
        } else if (enemyScoreP >= 0.75 && enemyScoreP < 0.90) {
            // Wenn der Gegner zwischen 75%-90% seines maximalen Werts hat, hat er eine
            // Chance zu bleiben
            if (random.nextBoolean()) {
                hit(enemy);
            } else {
                stay(enemy);
            }
        } else {
            // Wenn der Gegner 90% oder mehr hat, bleibt er immer stehen
            stay(enemy);
        }
    }

    // Berechnet den Schaden basierend auf den Handwerten
    public void damageDeal() {

        if (damageDealt)
            return; // Verhindere doppelte Berechnungen
        damageDealt = true;

        // Berechne den prozentualen Anteil der Hand des Spielers und des Gegners
        double playerScoreP = (double) p.getPoints() / p.getGauge();
        double enemyScoreP = (double) enemy.getPoints() / enemy.getGauge();

        // Überprüfe zuerst, ob einer der Spieler "überzogen" hat
        if (p.isOvershot()) {
            int damage = enemy.getPoints(); // Der Gegner gewinnt und fügt Schaden zu
            p.damage(damage);
            checkHp();
            return;
        } else if (enemy.isOvershot()) {
            int damage = p.getPoints(); // Der Spieler gewinnt und fügt Schaden zu
            if (p.getDoubledamage()) {
                damage *= 2; // Verdopple den Schaden bei Doppelschaden
                p.setDoubledamage(false);
            }
            enemy.damage(damage);
            checkHp();
            return;
        }

        // Berechne den Schaden basierend auf dem Unterschied in den Handwerten
        double scoreDiff = Math.abs(playerScoreP - enemyScoreP) * 2;
        if (scoreDiff < 0) {
            scoreDiff = scoreDiff * -2;
        }
        int baseDamage = (int) (scoreDiff * 10) + 3; // Konvertiere den Unterschied in Schaden
        if (p.getDoubledamage()) {
            baseDamage *= 2;
            p.setDoubledamage(false); // Setze Doppelschaden zurück
        }

        // Multipliziere den Schaden bei nahezu perfekten Händen
        if (scoreDiff > 0.9)
            baseDamage *= 4;

        // Bestimme, wer den Schaden nimmt
        if (enemyScoreP >= playerScoreP) {
            p.damage(baseDamage);
        } else {
            enemy.damage(baseDamage);
        }

        checkHp();
    }

    // Überprüft die Lebenspunkte beider Spieler
    public void checkHp() {
        if (p.getHp() > 0) {
            roundEnd(); // Ende der Runde, wenn der Spieler noch lebt
        } else {
            gameOver(true, enemysslain); // Spiel vorbei, wenn der Spieler gestorben ist
        }
    }

    // Setzt den Zustand der Runde zurück und startet den Kampf neu
    @Override
    public void buttonContinue_ActionPerformed(ActionEvent evt) {
        roundReset();
        p.setPoints(0);
        enemy.setPoints(0);
        battle(); // Starte den Kampf neu
    }
}
