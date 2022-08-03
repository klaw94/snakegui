package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class MyPanel extends JPanel implements ActionListener {
    int[] applePosition = new int[2];
    int height;
    int size;
    Snake snake;
    Timer timer;
    int unitSize;
    public int numApples = 0;
    JLabel text;



    public MyPanel(int size, int unitSize) {
        this.height = size - 25;
        this.size = size;
        this.unitSize = unitSize;
        setBorder(BorderFactory.createLineBorder(Color.black));
        createApple(size, unitSize);
        snake = new Snake((int) Math.floor(Math.random()*size/unitSize)*unitSize, (int) Math.floor(Math.random()*size/unitSize)*unitSize, 4, 'U');
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new MyKeyAdapter());
        this.setVisible(true);
        startGame();
    }

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw Text
        draw(g);
    }
    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillOval(applePosition[0],applePosition[1], 25, 25);
        for(int i = 0; i < snake.length; i++){
            if (i == 0){
                Color myGreen = new Color(0,153, 0);
                g.setColor(myGreen);
            } else {
                g.setColor(Color.green);
            }

            g.fillRect(snake.snakePosition.get(i)[0], snake.snakePosition.get(i)[1], 25, 25);
        }
    }

    public void startGame(){
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snake.move();
                checkCollision();
                if(checkApple()){
                    eatApple();
                }
                repaint();
            }
        });
        timer.setRepeats(true);
        timer.setInitialDelay(100);
        timer.start();
    }

    public void setText(JLabel text){
        this.text = text;
    }
    private void eatApple() {
        snake.grow();
        numApples++;
        createApple(size, unitSize);
        text.setText("Apples : " + numApples);
    }

    private boolean checkApple() {
        return snake.headPosition[0] == applePosition[0] && snake.headPosition[1] == applePosition[1];
    }

    private void checkCollision() {
        //the borders of the visible Panel are not exactly 0 and 600, that's why the -25
        if((snake.headPosition[0] == -25 || snake.headPosition[0] > size) || (snake.headPosition[1] == -25 || snake.headPosition[1] >= height - 25)){
            timer.stop();
            text.setText("You Lost!");
        }

        for(int i = 1; i < snake.length; i++){
            if(snake.headPosition[0] == snake.snakePosition.get(i)[0] && snake.headPosition[1] == snake.snakePosition.get(i)[1]){
                timer.stop();
                text.setText("You Lost!");
            }
        }
    }


    public void createApple(int size, int unitSize){
        applePosition[0] = (int) Math.floor(Math.random()*size/unitSize)*unitSize;
        if (applePosition[0] > size){
            applePosition[0] -= 25;
        } else if (applePosition[0] <= 0) {
            applePosition[0] += 25;
        }
        applePosition[1] = (int) Math.floor(Math.random()*size/unitSize)*unitSize;
        if (applePosition[1] < 0){
            applePosition[1] += 25;
        } else if (applePosition[1] > height - 25) {
            applePosition[1] -= 25;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public class MyKeyAdapter extends KeyAdapter{
        //TODO if you click to fast the snake might go in the opposite direction
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_RIGHT:
                    snake.changeDirection('R');
                    break;
                case KeyEvent.VK_LEFT:
                    snake.changeDirection('L');
                    break;
                case KeyEvent.VK_UP:
                    snake.changeDirection('U');
                    break;
                case KeyEvent.VK_DOWN:
                    snake.changeDirection('D');
                    break;
            }
        }
    }
}

