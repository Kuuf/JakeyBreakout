/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jakeybreakout;

import com.michaelcotterell.game.Game;
import com.michaelcotterell.game.GameTime;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author jacobwall
 */
public class MainMenu extends BorderPane {

    public int offset = 50;
    public Color yellow = Color.web("FCD12A");
    public Color orange = Color.web("FFA800");
    public Color red = Color.web("FB4603");

    //main menu object
    public MainMenu() {
        title.setText("Breakout");
        play.setText("Click Here to Play");
        instructions.setText("Instructions");

        getChildren().addAll(bg, playBox, instructionsBox, title, play, instructions);
        ScaleTransition st = new ScaleTransition(Duration.millis(2000), title);
        st.setByX(.1);
        st.setByY(.1);
        st.setCycleCount(40);
        st.setAutoReverse(true);

        st.play();

    }

    //displays title text
    public Text title = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 100));
            setFill(Color.WHITE);
            setLayoutX(180);
            setLayoutY(130 + offset);
            setStroke(Color.WHITE);
            setStrokeWidth(3);

        }
    };

    //displays clickable play button
    public Text play = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 25));
            setFill(Color.WHITE);
            setLayoutX(300);
            setLayoutY(200 + offset);
            // setStroke(Color.BLACK);
            setStrokeWidth(1);
            //sets color to yellow upon mouse hovering over text
            setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setFill(Color.web("FCD12A"));
                }
            });
            //sets to white after mouse stops hovering text
            setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setFill(Color.WHITE);
                }
            });
            //resets certain parameters to start game
            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    BreakoutGame.paddle.setTranslateX(0);
                    BreakoutGame.paddle.x = 0;
                    BreakoutGame.commandFromMainMenu = true;
                    BreakoutGame.startGame = true;
                    BreakoutGame.resetGame = true;
                }
            });

        }
    };
    /**
     * allows user to click along the entire x axis of the play button ,not just
     * while directly above it
     */
    public Rectangle playBox = new Rectangle() {
        {
            setHeight(25);
            setWidth(800);
            setLayoutX(0);
            setLayoutY(180 + offset);
            setFill(Color.web("000944"));

            setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    play.setFill(Color.web("FCD12A"));
                }
            });
            setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    play.setFill(Color.WHITE);
                }
            });
            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    BreakoutGame.commandFromMainMenu = true;
                    BreakoutGame.startGame = true;
                    BreakoutGame.resetGame = true;

                }
            });
        }
    };

    //displays clickable instructions text
    public Text instructions = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 25));
            setFill(Color.WHITE);
            setLayoutX(330);
            setLayoutY(250 + offset);
            setStrokeWidth(1);
            //sets color to yellow upon mouse hovering over text
            setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    instructions.setFill(Color.web("FCD12A"));
                }
            });
            //sets color to white upon mouse leaving text
            setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    instructions.setFill(Color.WHITE);
                }
            });
            //shows instructions menu when clicked
            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    BreakoutGame.showInstructions();
                }
            });

        }
    };

    /**
     * allows user to click along the entire x axis of the instructions button
     * ,not just while directly above it
     */
    public Rectangle instructionsBox = new Rectangle() {
        {
            setHeight(20);
            setWidth(800);
            setLayoutX(0);
            setLayoutY(230 + offset);
            setFill(Color.web("000944"));
            //sets color to yellow upon mouse hovering over text
            setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    instructions.setFill(Color.web("FCD12A"));
                }
            });
            //sets color to WHITE upon mouse leaving text
            setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    instructions.setFill(Color.WHITE);
                }
            });
            //shows instructions menu when clicked
            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    BreakoutGame.showInstructions();
                }
            });
        }
    };

    //background color
    public Rectangle bg = new Rectangle(0, 0, 800, 450) {
        {
            Stop[] stops = new Stop[]{new Stop(0, Color.BLACK), new Stop(1, Color.RED)};
            LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
            setFill(Color.web("000846"));

        }
    };

} // MainMenu
