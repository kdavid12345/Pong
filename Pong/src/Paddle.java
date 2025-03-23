import java.awt.*;

public class Paddle {
    private int x, y, velocityUp = 0, velocityDown = 0;
    private final int width = 20, height = 100;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void reset(int startX, int startY){
        x = startX;
        y = startY;
    }

    public void move() {
        y -= velocityUp;
        y += velocityDown;
        if (y < 0) y = 0;
        if (y > 500) y = 500;
    }

    public void moveAI(Ball ball) {
        y = ball.getY() - height / 2;
        if (y < 0) y = 0;
        if (y > 500) y = 500;
    }

    public void setVelocityUp(int velocityUp) {
        this.velocityUp = velocityUp;
    }

    public void setVelocityDown(int velocityDown) {
        this.velocityDown = velocityDown;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
