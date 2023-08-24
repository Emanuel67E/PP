import java.awt.*;

public class Score extends Rectangle {
    int score;

    Score() {
        this.score = 0;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Consolas", Font.PLAIN, 30));
        g.drawString(String.valueOf(score), 50, 35);
    }
}