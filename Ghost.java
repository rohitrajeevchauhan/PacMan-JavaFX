import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;

public class Ghost {
    private int row, col;
    private Image sprite = new Image("blinky.png");

    public Ghost(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public void update(Map map, PacMan pacman) {
        if (pacman.getBounds().getMinX()/20 > col) col++;
        else if (pacman.getBounds().getMinX()/20 < col) col--;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(sprite, col*20, row*20, 20, 20);
    }

    public Rectangle2D getBounds() { return new Rectangle2D(col*20, row*20, 20, 20); }
}
