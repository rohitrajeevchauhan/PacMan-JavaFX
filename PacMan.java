import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;

public class PacMan {
    private int row, col;
    private int dx, dy;
    private Image sprite = new Image("pacman.png");

    public PacMan(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public void handleKey(KeyCode key) {
        switch (key) {
            case UP -> { dx = 0; dy = -1; }
            case DOWN -> { dx = 0; dy = 1; }
            case LEFT -> { dx = -1; dy = 0; }
            case RIGHT -> { dx = 1; dy = 0; }
        }
    }

    public void update(Map map) {
        if (!map.isWall(row + dy, col + dx)) {
            row += dy;
            col += dx;
        }
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(sprite, col*20, row*20, 20, 20);
    }

    public Rectangle2D getBounds() { return new Rectangle2D(col*20, row*20, 20, 20); }
}
