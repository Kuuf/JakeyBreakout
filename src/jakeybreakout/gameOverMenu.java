/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jakeybreakout;

import java.util.Timer;
import java.util.TimerTask;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author jacobwall
 */
public class gameOverMenu extends Pane {

    public static Color lightGreen = Color.web("00AD57");
    public static Color darkGreen = Color.web("005738");
    public static Color darkPurple = Color.web("360052");
    public static Color darkRed = Color.web("520000");
    public static Color color = Color.PURPLE;
    public static Color shadowColor = darkPurple;

    public gameOverMenu() {
        gameOverText.setText("Game Over");
        toPlayAgain.setText("Play Again");
        backToMenu.setText("Back to Main Menu");
        score.setText("Final Score: " + BreakoutGame.score);
        getChildren().addAll(bg, gameOverText, toPlayAgain, backToMenu, score);
    }

    /**
     * game over screen title text
     */
    public Text gameOverText = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 100));
            setFill(Color.WHITE);
            setLayoutX(140);
            setLayoutY(180);
            setStroke(Color.WHITE);
            setStrokeWidth(3);
            DropShadow ds = new DropShadow();
            ds.setColor(shadowColor);

            ds.setSpread(.5);
            setEffect(ds);
        }
    };

    //displays final score
    public static Text score = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 20));
            setFill(Color.WHITE);
            setLayoutX(336);
            setLayoutY(230);
            setStroke(Color.WHITE);
            setStrokeWidth(1);

            DropShadow ds = new DropShadow();
            ds.setColor(shadowColor);
            ds.setSpread(.5);
            setEffect(ds);
        }
    };

    //clickable play again text
    public Text toPlayAgain = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 25));
            setFill(Color.WHITE);
            setLayoutX(335);
            setLayoutY(280);
            setStroke(Color.WHITE);
            setStrokeWidth(1);
            DropShadow ds = new DropShadow();
            ds.setColor(shadowColor);

            ds.setSpread(.2);
            setEffect(ds);
            setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setFill(Color.web("FCD12A"));
                    setStroke(Color.web("FCD12A"));
                }
            });
            setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setFill(Color.WHITE);
                    setStroke(Color.WHITE);
                }
            });
            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    BreakoutGame.hideGameOverMenu();
                    BreakoutGame.mainMenu.toBack();
                    BreakoutGame.resetPaddle = true;
                    

                    execute(new TimerTask() {
                        @Override
                        public void run() {
                            BreakoutGame.startGame = true;
                            BreakoutGame.resetGame = true;

                        }
                    }, 3000);

                }
            });
        }
    };

    //clickable text that sends user to main menu
    public Text backToMenu = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 25));
            setFill(Color.WHITE);
            setLayoutX(290);
            setLayoutY(330);
            setStroke(Color.WHITE);
            setStrokeWidth(1);
            DropShadow ds = new DropShadow();
            ds.setColor(shadowColor);
            ds.setSpread(.2);
            setEffect(ds);
            setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setFill(Color.web("FCD12A"));
                    setStroke(Color.web("FCD12A"));
                }
            });
            setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setFill(Color.WHITE);
                    setStroke(Color.WHITE);
                }
            });
            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    BreakoutGame.hideGameOverMenu();
                }
            });
        }
    };

    //background color
    public Rectangle bg = new Rectangle(0, 0, 800, 450) {
        {
            //Stop[] stops = new Stop[]{new Stop(0, Color.RED), new Stop(1, Color.web("A60000"))};
            Stop[] stops = new Stop[]{new Stop(0, color), new Stop(1, shadowColor)};
            LinearGradient lg1 = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
            setFill(lg1);
            //setFill(Color.RED);
            setOpacity(.5);
        }
    };

    //method called when needing a timed task
    public void execute(TimerTask task, long time) {
        new Timer() {
            {
                schedule(task, time);
            }
        };

    } // execute

}
