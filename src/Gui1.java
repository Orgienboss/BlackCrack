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
    private Label label1 = new Label();
    private Panel panel1 = new Panel(null);
    private Panel panel2 = new Panel(null);
    private Label label2 = new Label();
    private Label label3 = new Label();
    private Button button1 = new Button();
    private Button button2 = new Button();
    private Label label4 = new Label();
    private Label label5 = new Label();
    private Label label6 = new Label();

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
        label1.setBounds(256, 24, 407, 52);
        label1.setText("BLACKDUNGEON");
        label1.setAlignment(Label.CENTER);
        label1.setFont(new Font("Distant Galaxy", Font.BOLD, 48));
        cp.add(label1);

        panel1.setBounds(25, 152, 190, 270);
        panel1.setBackground(new Color(167, 138, 127));
        cp.add(panel1);

        panel2.setBounds(670, 152, 190, 270);
        panel2.setBackground(new Color(167, 138, 127));
        cp.add(panel2);

        label2.setBounds(90, 420, 110, 20);
        label2.setText("Gauge: ");
        cp.add(label2);

        label3.setBounds(240, 160, 110, 20);
        label3.setText("Score: ");
        cp.add(label3);

        button1.setBounds(240, 360, 75, 25);
        button1.setLabel("Hit");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                button1_ActionPerformed(evt);
            }
        });
        cp.add(button1);

        button2.setBounds(240, 400, 73, 25);
        button2.setLabel("Stay");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                button2_ActionPerformed(evt);
            }
        });
        cp.add(button2);

        label4.setBounds(240, 180, 170, 20);
        label4.setText("Card drawn: ");
        cp.add(label4);

        label5.setBounds(240, 195, 200, 40);
        label5.setText("");
        label5.setVisible(false);
        label5.setFont(new Font("Distant Galaxy", Font.ITALIC, 20));
        cp.add(label5);

        label6.setBounds(256, 70, 407, 52);
        label6.setFont(new Font("Distant Galaxy", Font.CENTER_BASELINE, 20));
        label6.setText("Round 1");
        label6.setAlignment(1);
        cp.add(label6);

        setResizable(false);
        setVisible(true);
    }

    public void setScore(String s) {
        label3.setText("Score: " + s);
    }

    public void setGauge(String s) {
        label2.setText("Gauge: " + s);

    }

    public void setCarddrawn(String s) {
        label4.setText("Card drawn: " + s);

    }

    // Anfang Methoden
    public void button1_ActionPerformed(ActionEvent evt) {

    }

    public void button2_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void overShot() {
        label5.setText("OVERSHOT");
        label5.setVisible(true);
        button1.setEnabled(false);
        button2.setEnabled(false);
    }

    public void setFinal() {
        label5.setText("FINAL");
        label5.setVisible(true);
        button1.setEnabled(false);
        button2.setEnabled(false);
    }

    public void roundReset() {
        label6.setText("Round 1");
        label5.setVisible(false);
        button1.setEnabled(true);
        button2.setEnabled(true);
    }

    public void setRound(int r) {
        label6.setText("Round " + r);
    }

    // Ende Methoden

}
