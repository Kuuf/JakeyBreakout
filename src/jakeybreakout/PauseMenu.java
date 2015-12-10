/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jakeybreakout;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author jacobwall
 */
public class PauseMenu extends Pane {

    public PauseMenu() {
        pausedText.setText("Paused");
        pressToUnpause.setText("Press SPACE or ESC to Resume");
        clickToExit.setText("Exit to Main Menu");
        getChildren().addAll(pauseLayer, pausedText, pressToUnpause, clickToExit);
    }

    //translucent black background for pause screen
    public Rectangle pauseLayer = new Rectangle(0, 0, 800, 450) {
        {
            setFill(Color.web("000944"));
            setOpacity(.5);

        }
    };

    //main paused screen text
    public Text pausedText = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 100));
            setFill(Color.WHITE);
            setLayoutX(240);
            setLayoutY(230);
            setStroke(Color.WHITE);
            setStrokeWidth(3);
            DropShadow ds = new DropShadow();
            ds.setColor(Color.BLACK);
            ds.setSpread(.5);
            setEffect(ds);
        }
    };

    //press space or esc to unpause button
    public Text pressToUnpause = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 18));
            setFill(Color.WHITE);
            setLayoutX(290);
            setLayoutY(300);
            setStroke(Color.WHITE);
            setStrokeWidth(1);
            DropShadow ds = new DropShadow();
            ds.setColor(Color.BLACK);
            ds.setSpread(.2);
            setEffect(ds);
        }
    };

    //clickable exit button
    public Text clickToExit = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 16));
            setFill(Color.WHITE);
            setLayoutX(340);
            setLayoutY(340);
            setStroke(Color.WHITE);
            setStrokeWidth(1);
            DropShadow ds = new DropShadow();
            ds.setColor(Color.BLACK);
            ds.setSpread(.2);
            setEffect(ds);
            //sets text to yellow on mouse hover
            setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setFill(Color.web("FCD12A"));
                    setStroke(Color.web("FCD12A"));
                }
            });
            //sets back to white after mouse leaves text
            setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setFill(Color.WHITE);
                    setStroke(Color.WHITE);
                }
            });
            //exits game and calls method to reset parameters 
            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    BreakoutGame.endGame();
                    BreakoutGame.startGame = false;

                }
            });

        }
    };

}
