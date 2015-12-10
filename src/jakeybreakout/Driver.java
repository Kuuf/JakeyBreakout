package jakeybreakout;

import com.michaelcotterell.game.Game;
import javafx.application.Application;
import javafx.stage.Stage;

public class Driver extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game game = new BreakoutGame(primaryStage);
        primaryStage.setTitle("Breakout");
        primaryStage.setScene(game.getScene());
        primaryStage.setResizable(false);
        primaryStage.setFocused(true);
        primaryStage.show();
        game.run();
    } // start

    public static void main(String[] args) {

        launch(args);
    } // main

} // Driver

