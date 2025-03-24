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
    // Ende Attribute

    public Gui1(String title) {
        super(title);
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

        label2.setBounds(64, 440, 110, 20);
        label2.setText("Gauge: ");
        cp.add(label2);

        label3.setBounds(232, 160, 110, 28);
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
        label4.setBounds(232, 232, 110, 20);
        label4.setText("Card drawn: ");
        cp.add(label4);
        // Ende Komponenten
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

    // Ende Methoden

    public static void main(String[] args) {
        new Gui1("Gui1");
    }
}
