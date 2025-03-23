import java.awt.*;
import java.util.Random;

public class Ball {
    private int x, y, velocityX, velocityY;
    private final int size = 20;
    private final Random random;

    public Ball(int x, int y) {
        random = new Random();
        reset(x, y);
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, size, size);
    }

    public void reset(int startX, int startY) {
        x = startX;
        y = startY;
        int startDir = random.nextInt(0, 2);
        velocityX = random.nextInt(3, 6);
        if (startDir == 0) {
            velocityX = -velocityX;
        }
        velocityY = random.nextInt(2, 5);
    }

    public void move() {
        x += velocityX;
        y += velocityY;
    }

    public void bounceX() {
        if (velocityX > 0) {
            velocityX = -(velocityX + 1);
        } else {
            velocityX = -(velocityX - 1);
        }
    }

    public void bounceY() {
        if (velocityY > 0) {
            velocityY = -(velocityY + 1);
        } else {
            velocityY = -(velocityY - 1);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public boolean collides(Paddle paddle) {
        return getBounds().intersects(paddle.getBounds());
    }
}