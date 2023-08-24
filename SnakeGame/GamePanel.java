import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    static final int GAME_WIDTH = 504; //1008;
    static final int GAME_HEIGHT = 504;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int SNAKE_WIDTH = 24;
    static final int SNAKE_HEIGHT = 24;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    int randomX;
    int randomY;
    SnakeHead snakeHead;
    SnakeBody snakeBody;
    Fruit fruit;
    Score score;

    GamePanel(){
        newSnake();
        random = new Random();
        newFruit();
        newSnakeBody();
        score = new Score();
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newSnake() {
        snakeHead = new SnakeHead(60, 20, SNAKE_WIDTH, SNAKE_HEIGHT);
    }
    public void newSnakeBody() {
        snakeBody = new SnakeBody(40, 20, SNAKE_WIDTH, SNAKE_HEIGHT, 0);
        for(int i=0; i<6; ++i) {
            snakeBody.addBody(20, 20, SNAKE_WIDTH, SNAKE_HEIGHT, 0);
        }
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
        score.draw(g);
    }
    public void move() {
        if(snakeHead.tomove == 1) {
            snakeHead.move();
            snakeBody.follow(snakeHead.x, snakeHead.y);
            if(snakeHead.vertical > 0) {
                --snakeHead.vertical;
            }
            if(snakeHead.horizontal > 0) {
                --snakeHead.horizontal;
            }
        }
    }
    public void checkCollision() {

        // Collision with walls
        if(snakeHead.y <= -15) {
            snakeHead.tomove = 0;
            //System.out.println("Collision with wall");
        }
        if(snakeHead.y >= GAME_HEIGHT-SNAKE_HEIGHT+15) {
            snakeHead.tomove = 0;
            //System.out.println("Collision with wall");
        }
        if(snakeHead.x <= -15) {
            snakeHead.tomove = 0;
            //System.out.println("Collision with wall");
        }
        if(snakeHead.x >= GAME_WIDTH-SNAKE_WIDTH+15) {
            snakeHead.tomove = 0;
            //System.out.println("Collision with wall");
        }

        // Collision with fruit
        if(snakeHead.x >= fruit.x-15 && snakeHead.x <= fruit.x+15 && snakeHead.y >= fruit.y-15 && snakeHead.y <= fruit.y+15) {
            randomX = (int)( random.nextFloat() * (GAME_WIDTH-SNAKE_WIDTH) );
            randomY = (int)( random.nextFloat() * (GAME_HEIGHT-SNAKE_HEIGHT) );
            fruit = new Fruit(randomX, randomY);
            for(int i=0; i<5; ++i) {
                snakeBody.addBody(snakeHead.x, snakeHead.y, SNAKE_WIDTH, SNAKE_HEIGHT, 1);
            }
            ++score.score;
        }

        // Collision with itself
        int collided = snakeBody.collision(snakeHead.x, snakeHead.y);
        if(collided == 1) {
            snakeHead.tomove = 0;
            //System.out.println("Collision with itself");
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
