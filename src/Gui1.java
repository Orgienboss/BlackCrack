import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * description
 *
 * @version 1.0 from 24.03.2025
 * @author
 */

public class Gui1 extends JFrame {
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
    private Panel panelPlayerHpBox = new Panel();
    private Panel panelEnemyHpBox = new Panel();

    boolean damageDealt;
    Player p;
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
        Container cp = getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(231, 215, 193));

        // Anfang Komponenten
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
        cp.add(panelPlayerPH);

        panelEnemyPH.setBounds(670, 152, 190, 270);
        panelEnemyPH.setBackground(new Color(167, 138, 127));
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

        setResizable(false);
        setVisible(true);

    }

    public void setScore(Player play, String s) {
        if (play.isPlayer) {
            labelScore.setText("Score: " + s);
        } else {
            labelScoreEnemy.setText("Score: " + s);
        }
    }

    public void setGauge(Player play, String s) {
        if (play.isPlayer) {
            labelGauge.setText("Gauge: " + s);
        } else {
            labelGaugeEnemy.setText("Gauge: " + s);
        }

    }

    public void roundEnd() {
        buttonContinue.setVisible(true);
    }

    public void setCarddrawn(Player play, String s) {
        if (play.isPlayer) {
            labelCarddrawn.setText("Card drawn: " + s);
        } else {
            labelCarddrawnEnemy.setText("Card drawn: " + s);
        }

    }

    public void setHearts(Player play, double b) {
        if (play.isPlayer) {
            labelHPplayer.setText("HP: " + (int) b);
            double a = 100 - b;
            double x = panelPlayerHp.getX() - (a / play.getHp()) * 90 + panelPlayerHp.getWidth();
            int w = panelPlayerHpBox.getWidth();
            panelPlayerHpBox.setBounds((int) x, 120, (int) (w + a), 40);
        } else {
            labelHPenemy.setText("HP: " + (int) b);
            double w = panelEnemyHp.getWidth() * (b / play.getHp());
            w = 90 - w;
            panelEnemyHpBox.setBounds(panelEnemyHpBox.getX(), 120, (int) w, 40);
        }
    }

    // public void setHeartsplayer(double b) {
    // double a = 100 - b;
    // double x = panelPlayerHp.getX() - (a / 100) * 90 + panelPlayerHp.getWidth();
    // int w = panelPlayerHpBox.getWidth();
    // panelPlayerHpBox.setBounds((int) x, 120, (int) (w + a), 40);
    // }

    // public void setHeartsenemy(double b) {
    // double w = panelEnemyHp.getWidth() * (b / 100);
    // w = 90 - w;
    // panelEnemyHpBox.setBounds(panelEnemyHpBox.getX(), 120, (int) w, 40);
    // }

    // Anfang Methoden
    public void buttonHit_ActionPerformed(ActionEvent evt) {
    }

    public void buttonStay_ActionPerformed(ActionEvent evt) {
    }

    public void buttonContinue_ActionPerformed(ActionEvent evt) {
    }

    public void overShot(Player play) {
        if (play.isPlayer) {
            labelState.setText("OVERSHOT");
            labelState.setVisible(true);
            buttonHit.setEnabled(false);
            buttonStay.setEnabled(false);
        } else {
            labelStateEnemy.setText("OVERSHOT");
            labelStateEnemy.setVisible(true);
        }

    }

    public void setFinal(Player play) {
        if (play.isPlayer) {
            labelState.setText("FINAL");
            labelState.setVisible(true);
            buttonHit.setEnabled(false);
            buttonStay.setEnabled(false);

        } else {
            labelStateEnemy.setText("FINAL");
            labelStateEnemy.setVisible(true);
        }

    }

    public void roundReset() {
        labelRound.setText("Round 1");
        labelState.setVisible(false);
        labelStateEnemy.setVisible(false);
        buttonHit.setEnabled(true);
        buttonStay.setEnabled(true);
        buttonContinue.setVisible(false);
    }

    public void setRound(int r) {
        labelRound.setText("Round " + r);
    }

    // Ende Methoden

}
