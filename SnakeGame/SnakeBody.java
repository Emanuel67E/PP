import java.awt.*;

public class SnakeBody extends Rectangle {
    SnakeBody next = null;
    int id;

    SnakeBody(int x, int y, int width, int height, int id) {
        super(x, y, width, height);
        this.id = id;
    }

    public void follow(int Xahead, int Yahead) {
            if(next != null) {
                next.follow(x, y);
            }
            x = Xahead;
            y = Yahead;
    }

    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
        if(next != null) {
            next.draw(g);
        }
    }

    public void addBody(int Xahead, int Yahead, int width, int height, int id) {
        if(next == null) {
            next = new SnakeBody(Xahead, Yahead, width, height, id);
        } else {
            next.addBody(x, y, width, height, id);
        }
    }

    public int collision(int HeadX, int HeadY) {
        if(id == 1) {
            if(HeadX >= x-15 && HeadX <= x+15 && HeadY >= y-15 && HeadY <= y+15) {
                return 1;
            }
        } 
        if(next != null) {
            return next.collision(HeadX, HeadY);
        } else {
            return 0;
        }
    }
}