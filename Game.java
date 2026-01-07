import javafx.scene.layout.Pane;
import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import java.util.*;

public class Game extends Pane {
    private Canvas canvas = new Canvas(560,620);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private PacMan pacman;
    private List<Ghost> ghosts;
    private Map map;
    private GameState state = GameState.MENU;
    private long startTime;

    public Game() {
        getChildren().add(canvas);
        map = new Map();
        pacman = new PacMan(14,23);
        ghosts = new ArrayList<>();
        ghosts.add(new Ghost(14,11));
        ghosts.add(new Ghost(13,14));
        ghosts.add(new Ghost(15,14));
        ghosts.add(new Ghost(14,15));
        startTime = System.currentTimeMillis();

        setOnKeyPressed(e -> handleInput(e.getCode()));
        setFocusTraversable(true);
    }

    private void handleInput(KeyCode key) {
        if(state==GameState.MENU && key==KeyCode.ENTER) state=GameState.PLAYING;
        else if(state==GameState.GAME_OVER && key==KeyCode.R) { new Game(); }
        else pacman.handleKey(key);
    }

    public void start() {
        new AnimationTimer() {
            public void handle(long now) { update(); draw(); }
        }.start();
    }

    private void update() {
        if(state!=GameState.PLAYING) return;
        pacman.update(map);
        ghosts.forEach(g->g.update(map,pacman));
        for(Ghost g:ghosts) if(pacman.getBounds().intersects(g.getBounds())) state=GameState.GAME_OVER;
    }

    private void draw() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,560,620);
        if(state==GameState.MENU) {
            gc.setFill(Color.YELLOW);
            gc.fillText("PAC-MAN",250,250);
            gc.fillText("Press ENTER to Start",210,300);
            return;
        }
        map.draw(gc);
        pacman.draw(gc);
        ghosts.forEach(g->g.draw(gc));
        gc.setFill(Color.WHITE);
        gc.fillText("Time: "+(System.currentTimeMillis()-startTime)/1000+"s",450,20);
        if(state==GameState.GAME_OVER) {
            gc.fillText("GAME OVER",240,300);
            gc.fillText("Press R to Restart",210,330);
        }
    }
}
