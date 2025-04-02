import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Random;

public abstract class Gui1 extends JFrame {
    // Anfang Attribute
    private Label labelTitle = new Label();
    private Panel panelPlayerPH = new Panel(null);
    private Panel panelEnemyPH = new Panel(null);
    private Label labelGauge = new Label();
    private Label labelScore = new Label();
    private Label labelHPplayer = new Label();
    private Label labelHPenemy = new Label();
    private Label labelGaugeEnemy = new Label();
    private Label labelScoreEnemy = new Label();
    private Button buttonHit = new Button();
    private Button buttonStay = new Button();
    private Button buttonContinue = new Button();
    private Label labelCarddrawn = new Label();
    private Label labelCarddrawnEnemy = new Label();
    private Label labelState = new Label();
    private Label labelStateEnemy = new Label();
    private Label labelRound = new Label();
    private Panel panelPlayerHp = new Panel();
    private Panel panelEnemyHp = new Panel();
    private ImageIcon playerHearts = new ImageIcon("./lib/hearts.png");
    private ImageIcon playerIcon = new ImageIcon("./lib/player.png");
    private Panel panelPlayerHpBox = new Panel();
    private Panel panelEnemyHpBox = new Panel();
    private Panel panelGameOver = new Panel();
    private Button buttonRestart = new Button("Restart");
    private Label labelEnemySlain = new Label("Enemys slain: ");
    private Label labelGameOver = new Label("Game Over");
    private ImageIcon enemyIcon1 = new ImageIcon("./lib/enemy1.png");
    private ImageIcon enemyIcon2 = new ImageIcon("./lib/enemy2.png");
    private ImageIcon[] enemyIconArray = { enemyIcon1, enemyIcon2 };

    protected JLabel enemyImg = new JLabel();
    protected Panel panelAbilityCard = new Panel();
    protected Label labelAbilityText = new Label();
    protected Button buttonUseAbility = new Button("Use Ability");
    protected boolean abilityAvailable = false;
    protected int abilityCooldown = 8;

    Random random = new Random();
    boolean damageDealt;
    Player p;
    Container cp;
    // Fight f = new Fight("Game", p);
    // Ende Attribute

    public Gui1(String title, Player player) {
        super(title);
        this.p = player;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 900;
        int frameHeight = 700;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        cp = getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(231, 215, 193));

        // Anfang Komponenten
        buttonRestart.setBounds(370, 320, 160, 60);
        buttonRestart.setVisible(false);
        buttonRestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonRestart_ActionPerformed(evt);
            }
        });
        cp.add(buttonRestart);

        labelEnemySlain.setBounds(370, 280, 160, 60);
        labelEnemySlain.setAlignment(1);
        labelEnemySlain.setVisible(false);
        cp.add(labelEnemySlain);

        labelGameOver.setBounds(0, 30, frameWidth, 60);
        labelGameOver.setAlignment(1);
        labelGameOver.setFont(new Font("Distant Galaxy", Font.BOLD, 65));
        labelGameOver.setVisible(false);
        cp.add(labelGameOver);

        panelGameOver.setBounds(0, 0, frameWidth, frameHeight);
        panelGameOver.setBackground(new Color(231, 215, 193));
        panelGameOver.setVisible(false);
        cp.add(panelGameOver);

        labelTitle.setBounds(256, 24, 407, 52);
        labelTitle.setText("BLACKDUNGEON");
        labelTitle.setAlignment(Label.CENTER);
        labelTitle.setFont(new Font("Distant Galaxy", Font.BOLD, 48));
        cp.add(labelTitle);

        buttonContinue.setBounds(400, 300, 100, 50);
        buttonContinue.setLabel("Continue");
        buttonContinue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonContinue_ActionPerformed(evt);
            }
        });
        buttonContinue.setVisible(false);
        cp.add(buttonContinue);

        panelPlayerPH.setBounds(25, 152, 190, 270);
        panelPlayerPH.setBackground(new Color(167, 138, 127));
        JLabel temp0 = new JLabel(playerIcon);
        temp0.setSize(190, 270);
        panelPlayerPH.add(temp0);
        cp.add(panelPlayerPH);

        panelEnemyPH.setBounds(670, 152, 190, 270);
        panelEnemyPH.setBackground(new Color(167, 138, 127));
        enemyImg = new JLabel(enemyIconArray[random.nextInt(0, (enemyIconArray.length))]);
        enemyImg.setSize(190, 270);
        panelEnemyPH.add(enemyImg);
        cp.add(panelEnemyPH);

        labelGauge.setBounds(25, 420, 190, 20);
        labelGauge.setAlignment(1);
        labelGauge.setText("Gauge: ");
        cp.add(labelGauge);

        labelGaugeEnemy.setBounds(670, 420, 190, 20);
        labelGaugeEnemy.setAlignment(1);
        labelGaugeEnemy.setText("Gauge: ");
        cp.add(labelGaugeEnemy);

        labelScore.setBounds(240, 160, 110, 20);
        labelScore.setText("Score: ");
        cp.add(labelScore);

        labelScoreEnemy.setBounds(525, 160, 110, 20);
        labelScoreEnemy.setAlignment(2);
        labelScoreEnemy.setText("Score: ");
        cp.add(labelScoreEnemy);

        buttonHit.setBounds(240, 360, 75, 25);
        buttonHit.setLabel("Hit");
        buttonHit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonHit_ActionPerformed(evt);
            }
        });
        cp.add(buttonHit);

        buttonStay.setBounds(240, 400, 73, 25);
        buttonStay.setLabel("Stay");
        buttonStay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonStay_ActionPerformed(evt);
            }
        });
        cp.add(buttonStay);

        labelCarddrawn.setBounds(240, 180, 170, 20);
        labelCarddrawn.setText("Card drawn: ");
        cp.add(labelCarddrawn);

        labelCarddrawnEnemy.setBounds(525, 180, 110, 20);
        labelCarddrawnEnemy.setAlignment(2);
        labelCarddrawnEnemy.setText("Card drawn: ");
        cp.add(labelCarddrawnEnemy);

        labelState.setBounds(240, 195, 200, 40);
        labelState.setText("");
        labelState.setVisible(false);
        labelState.setFont(new Font("Distant Galaxy", Font.ITALIC, 20));
        cp.add(labelState);

        labelStateEnemy.setBounds(435, 195, 200, 40);
        labelStateEnemy.setAlignment(2);
        labelStateEnemy.setText("");
        labelStateEnemy.setVisible(false);
        labelStateEnemy.setFont(new Font("Distant Galaxy", Font.ITALIC, 20));
        cp.add(labelStateEnemy);

        labelRound.setBounds(256, 70, 407, 52);
        labelRound.setFont(new Font("Distant Galaxy", Font.CENTER_BASELINE, 20));
        labelRound.setText("Round 1");
        labelRound.setAlignment(1);
        cp.add(labelRound);

        panelPlayerHpBox.setBounds(114, 120, 1, 40);
        panelPlayerHpBox.setBackground(new Color(231, 215, 193));
        cp.add(panelPlayerHpBox);

        panelEnemyHpBox.setBounds(770, 120, 1, 40);
        panelEnemyHpBox.setBackground(new Color(231, 215, 193));
        cp.add(panelEnemyHpBox);

        labelHPplayer.setBounds(25, 110, 90, 10);
        labelHPplayer.setText("HP: ");
        cp.add(labelHPplayer);

        labelHPenemy.setBounds(770, 110, 90, 10);
        labelHPenemy.setText("HP: ");
        labelHPenemy.setAlignment(2);
        cp.add(labelHPenemy);

        panelPlayerHp.setBounds(25, 120, 90, 40);
        JLabel temp1 = new JLabel(playerHearts);
        temp1.setSize(90, 24);
        panelPlayerHp.add(temp1);
        cp.add(panelPlayerHp);

        panelEnemyHp.setBounds(770, 120, 90, 40);
        JLabel temp2 = new JLabel(playerHearts);
        temp2.setSize(90, 24);
        panelEnemyHp.add(temp2);
        cp.add(panelEnemyHp);

        panelAbilityCard.setBounds(300, 500, 300, 80);
        panelAbilityCard.setBackground(new Color(200, 200, 200));
        panelAbilityCard.setLayout(null);
        panelAbilityCard.setVisible(false);
        cp.add(panelAbilityCard);

        // Ability Text Label
        labelAbilityText.setBounds(10, 10, 280, 30);
        labelAbilityText.setAlignment(Label.CENTER);
        panelAbilityCard.add(labelAbilityText);

        // Use Ability Button
        buttonUseAbility.setBounds(100, 50, 100, 25);
        buttonUseAbility.setEnabled(false);
        buttonUseAbility.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                useAbility();
            }
        });
        panelAbilityCard.add(buttonUseAbility);

        setResizable(false);
        setVisible(true);

    }

    // wird noch überschrieben
    public void useAbility() {
    }

    // Ändert das Bild vom Gegner
    public void newEnemyImage() {
        enemyImg.setIcon(enemyIconArray[random.nextInt(enemyIconArray.length)]);
        enemyImg.setSize(190, 270);
        panelEnemyPH.add(enemyImg);
    }

    // Schließt das Fenster
    public void close() {
        super.hide();
    }

    // Setzt die Punkte von Class Player im Gui fest
    public void setScore(Player play, String s) {
        if (play.isPlayer) {
            labelScore.setText("Score: " + s);
        } else {
            labelScoreEnemy.setText("Score: " + s);
        }
    }

    // Setzt den Blackjack/gauge von Class Player im Gui fest
    public void setGauge(Player play, String s) {
        if (play.isPlayer) {
            labelGauge.setText("Gauge: " + s);
        } else {
            labelGaugeEnemy.setText("Gauge: " + s);
        }

    }

    // Lässt den Continue-Knopf erscheinen
    public void roundEnd() {
        buttonContinue.setVisible(true);
    }

    // Gameover-screen anzeigen
    public void gameOver(boolean b, int a) {
        panelGameOver.setVisible(b);
        labelEnemySlain.setText("Enemys slain: " + a);
        labelEnemySlain.setVisible(b);
        labelGameOver.setVisible(b);
        buttonRestart.setVisible(b);
    }

    // zeigt letzte gezogene Karte an
    public void setCarddrawn(Player play, String s) {
        if (play.isPlayer) {
            labelCarddrawn.setText("Card drawn: " + s);
        } else {
            labelCarddrawnEnemy.setText("Card drawn: " + s);
        }

    }

    // Zeigt die Leben im Gui an
    public void setHearts(Player play, double b) {
        if (play.isPlayer) {
            labelHPplayer.setText("HP: " + (int) b);
            double a = 100 - b;
            double x = panelPlayerHp.getX() - (a / play.getMaxHP()) * 90 + panelPlayerHp.getWidth();
            int w = panelPlayerHpBox.getWidth();
            panelPlayerHpBox.setBounds((int) x, 120, (int) (w + a), 40); // Schiebt eine Box mit Hintergrundfarbe über
                                                                         // die Herzen
        } else {
            labelHPenemy.setText("HP: " + (int) b);
            double w = panelEnemyHp.getWidth() * (b / play.getMaxHP());
            w = 90 - w;
            panelEnemyHpBox.setBounds(panelEnemyHpBox.getX(), 120, (int) w, 40); // Schiebt eine Box mit
                                                                                 // Hintergrundfarbe über die Herzen
        }
    }

    // Buttons werden in Fight überschrieben
    public void buttonHit_ActionPerformed(ActionEvent evt) {
    }

    public void buttonStay_ActionPerformed(ActionEvent evt) {
    }

    public void buttonContinue_ActionPerformed(ActionEvent evt) {
    }

    public void buttonRestart_ActionPerformed(ActionEvent evt) {
    }

    // Guireaktion wenn Player.getOvershot()
    public void overShot(Player play) {
        if (play.isPlayer) {
            labelState.setText("OVERSHOT");
            lockIn();
        } else {
            labelStateEnemy.setText("OVERSHOT");
            labelStateEnemy.setVisible(true);
        }

    }

    // Knöpfe sperren
    public void lockIn() {
        labelState.setVisible(true);
        buttonHit.setEnabled(false);
        buttonStay.setEnabled(false);
        buttonUseAbility.setEnabled(false);
    }

    // Guireaktion wenn Player.getFinalset()
    public void setFinal(Player play) {
        if (play.isPlayer) {
            labelState.setText("FINAL");
            lockIn();
        } else {
            labelStateEnemy.setText("FINAL");
            labelStateEnemy.setVisible(true);
        }

    }

    // Knöpfe wieder entsperren, ...
    public void roundReset() {
        labelRound.setText("Round 1");
        labelState.setVisible(false);
        labelStateEnemy.setVisible(false);
        buttonHit.setEnabled(true);
        buttonStay.setEnabled(true);
        buttonContinue.setVisible(false);
        buttonUseAbility.setEnabled(true);
    }

    // Rounde Text ändern
    public void setRound(int r) {
        labelRound.setText("Round " + r);
    }

    // Ende Methoden

}
