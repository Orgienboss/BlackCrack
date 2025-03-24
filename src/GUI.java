// * This code was generated with love by Window Builder VS Code extension. * //

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class GUI {
  public void start() {
    JFrame frame = new JFrame("Game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 400);

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    // Fill the layout with placeholders
    for (int row = 0; row < 20; row++) {
      for (int col = 0; col < 15; col++) {
        constraints.gridx = col;
        constraints.gridy = row;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;

        Component filler = Box.createRigidArea(new Dimension(0, 0));
        panel.add(filler, constraints);
      }
  }

        
    constraints.weightx = 0;
    constraints.weighty = 0;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.NORTHWEST;

    JLabel label_0 = new JLabel("Score");
    constraints.gridx = -9;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    constraints.gridheight = 2;
    panel.add(label_0, constraints);

    JButton button_0 = new JButton("Hit");
    button_0.setPreferredSize(new Dimension(54, 27));

    constraints.gridx = -3;
    constraints.gridy = 22;
    constraints.gridwidth = 2;
    constraints.gridheight = 2;
    panel.add(button_0, constraints);

    JButton button_1 = new JButton("Stay");
    button_1.setPreferredSize(new Dimension(54, 27));

    constraints.gridx = 2;
    constraints.gridy = 22;
    constraints.gridwidth = 2;
    constraints.gridheight = 2;
    panel.add(button_1, constraints);

    JLabel label_1 = new JLabel("Score");
    constraints.gridx = -1;
    constraints.gridy = 18;
    constraints.gridwidth = 2;
    constraints.gridheight = 2;
    panel.add(label_1, constraints);

    frame.add(panel);
    frame.setVisible(true);
  }
}
