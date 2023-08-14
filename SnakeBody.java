import java.awt.*;

public class SnakeBody extends Rectangle {
    int id;
    SnakeBody next = null;

    SnakeBody(int x, int y, int width, int height, int id) {

        super(x, y, width, height);
        this.id = id;
    }

    public void follow(int closeX, int closeY) {
        /*if(closeX > x+22) {
            x += 4;
            if(closeY > y) {
                y += 2;
            } else if(closeY < y) {
                y -= 2;
            }
        } else if(closeX < x-22) {
            x -= 4;
            if(closeY > y) {
                y += 2;
            } else if(closeY < y) {
                y -= 2;
            }
        } else if(closeY > y+22) {
            y += 4;
            if(closeX > x) {
                x += 2;
            } else if(closeX < x) {
                x -= 2;
            }
        } else if(closeY < y-22) {
            y -= 4;
            if(closeX > x) {
                x += 2;
            } else if(closeX < x) {
                x -= 2;
            }
        }*/
        if(next != null) {
            next.follow(x, y);
        }

        if(id == 1) {
            x = closeX;
            y = closeY;
        } else if(id == 0) {
            if(closeX > x+22) {
                x += 4;
                if(closeY > y) {
                    y += 2;
                } else if(closeY < y) {
                    y -= 2;
                }
            } else if(closeX < x-22) {
                x -= 4;
                if(closeY > y) {
                    y += 2;
                } else if(closeY < y) {
                    y -= 2;
                }
            } else if(closeY > y+22) {
                y += 4;
                if(closeX > x) {
                    x += 2;
                } else if(closeX < x) {
                    x -= 2;
                }
            } else if(closeY < y-22) {
                y -= 4;
                if(closeX > x) {
                    x += 2;
                } else if(closeX < x) {
                    x -= 2;
                }
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
        if(next != null) {
            next.draw(g);
        }
    }

    public void addBody(int x, int y, int width, int height, int id) {
        if(next == null) {
            next = new SnakeBody(x, y, width, height, id);
        } else {
            next.addBody(x, y, width, height, id);
        }
    }
}