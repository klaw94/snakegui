package Snake;

import javax.swing.*;
import java.awt.*;

public class GuiSnakeGame extends JPanel{
    JFrame frame;
    MyPanel fieldPanel;
    JPanel textPanel;
    JLabel textField;
    int numColumns;
    int numRows;

    int unitSize = 25;



    public GuiSnakeGame(int size){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake");
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

        fieldPanel = new MyPanel(size, unitSize);
        fieldPanel.setBackground(Color.GRAY);
        fieldPanel.setVisible(true);

        textPanel = new JPanel();
        textPanel.setVisible(true);
        textPanel.setBackground(Color.DARK_GRAY);

        textField = new JLabel();
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setForeground(Color.white);
        textField.setFont(new Font("Mv Boli", Font.BOLD, 24));
        textField.setText("Apples : " + fieldPanel.numApples);

        frame.add(textPanel, BorderLayout.NORTH);
        textPanel.add(textField);
        frame.add(fieldPanel);
        frame.setSize(new Dimension(size + 35, size + 35));
        fieldPanel.setText(textField);
        frame.revalidate();

    }



    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiSnakeGame(600);
            }
        });

    }
}
