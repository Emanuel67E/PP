import java.awt.*;

public class Fruit extends Rectangle {
    Fruit(int x, int y) {
        super(x, y, 25, 25);
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }
}