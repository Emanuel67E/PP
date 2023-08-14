import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = 500; //(int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int SNAKE_WIDTH = 25;
    static final int SNAKE_HEIGHT = 25;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    int randomX;
    int randomY;
    SnakeHead snakeHead;
    SnakeBody snakeBody;
    Fruit fruit;

    GamePanel(){
        newSnake();
        random = new Random();
        newFruit();
        newSnakeBody();
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newSnake() {
        snakeHead = new SnakeHead(60, 60, SNAKE_WIDTH, SNAKE_HEIGHT);
    }
    public void newSnakeBody() {
        snakeBody = new SnakeBody(40, 30, SNAKE_WIDTH, SNAKE_HEIGHT, 0);
        snakeBody.addBody(10, 30, SNAKE_WIDTH, SNAKE_HEIGHT, 1);
    }
    public void newFruit() {
        randomX = (int)( random.nextFloat() * (GAME_WIDTH-SNAKE_WIDTH) );
        randomY = (int)( random.nextFloat() * (GAME_HEIGHT-SNAKE_HEIGHT) );
        fruit = new Fruit(randomX, randomY);
    }
    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g) {
        snakeHead.draw(g);
        snakeBody.draw(g);
        fruit.draw(g);
    }
    public void move() {
        snakeHead.move();
        snakeBody.follow(snakeHead.x, snakeHead.y);
    }
    public void checkCollision() {

        // Collision with walls
        if(snakeHead.y <= 0) {
            snakeHead.y = 0;
        }
        if(snakeHead.y >= GAME_HEIGHT-SNAKE_HEIGHT) {
            snakeHead.y = GAME_HEIGHT-SNAKE_HEIGHT;
        }
        if(snakeHead.x <= 0) {
            snakeHead.x = 0;
        }
        if(snakeHead.x >= GAME_WIDTH-SNAKE_WIDTH) {
            snakeHead.x = GAME_WIDTH-SNAKE_WIDTH;
        }

        // Collision with fruit
        if(snakeHead.x >= fruit.x-15 && snakeHead.x <= fruit.x+15 && snakeHead.y >= fruit.y-15 && snakeHead.y <= fruit.y+15) {
            randomX = (int)( random.nextFloat() * (GAME_WIDTH-SNAKE_WIDTH) );
            randomY = (int)( random.nextFloat() * (GAME_HEIGHT-SNAKE_HEIGHT) );
            fruit = new Fruit(randomX, randomY);
            snakeBody.addBody(snakeHead.x, snakeHead.y, SNAKE_WIDTH, SNAKE_HEIGHT, 1);
        }
    }

    public void run() {
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e) {

            snakeHead.keyPressed(e);
        }
    }
}
