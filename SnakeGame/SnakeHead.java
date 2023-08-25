import java.awt.*;
import java.awt.event.*;

public class SnakeHead extends Rectangle{
    int xVelocity;
    int yVelocity;
    int speed = 4;
    int vertical;
    int horizontal;
    int tomove;
    int count;

    SnakeHead(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.vertical = 0;
        this.horizontal = 0;
        this.tomove = 1;
        setXDirection(speed);
        setYDirection(0);
        this.count = 10;
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W && yVelocity == 0 && vertical == 0) {
            setYDirection(-speed);
            setXDirection(0);
            horizontal = 4;
        }
        if(e.getKeyCode()==KeyEvent.VK_S && yVelocity == 0 && vertical == 0) {
            setYDirection(speed);
            setXDirection(0);
            horizontal = 4;
        }
        if(e.getKeyCode()==KeyEvent.VK_A && xVelocity == 0 && horizontal == 0) {
            setXDirection(-speed);
            setYDirection(0);
            vertical = 4;
        }
        if(e.getKeyCode()==KeyEvent.VK_D && xVelocity == 0 && horizontal == 0) {
            setXDirection(speed);
            setYDirection(0);
            vertical = 4;
        }
    }
    public void setXDirection(int xDirection) {
        xVelocity = xDirection;
    }
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }
    public void move() {
        if(count > 0) {
            tomove = 0;
        }
        if(tomove == 1) {
            x = x + xVelocity;
            y = y + yVelocity;
        }
    }
    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }
}
