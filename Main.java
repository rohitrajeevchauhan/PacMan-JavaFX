import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Game game = new Game();
        Scene scene = new Scene(game, 560, 620);
        stage.setScene(scene);
        stage.setTitle("Pac-Man JavaFX");
        stage.show();
        game.start();
    }

    public static void main(String[] args) {
        launch();
    }
}
