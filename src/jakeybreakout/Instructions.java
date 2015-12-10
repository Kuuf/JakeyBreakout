package jakeybreakout;

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

public class Instructions extends Pane {

    public int offset = 0;

    public Instructions() {
        title.setText("Instructions");
        pressLeft.setText("Press LEFT Arrow Key to Move Paddle Left");
        pressRight.setText("Press RIGHT Arrow Key to Move Paddle Right");
        pressSpace.setText("Press SPACE or Esc to Pause the Game");
        backToMenu.setText("Back to Main Menu");
        getChildren().addAll(bg, title, pressLeft, pressRight, pressSpace, backToMenu);

    }

    //main instructions title
    public Text title = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 100));
            setFill(Color.WHITE);
            setLayoutX(120);
            setLayoutY(130 + offset);
            setStroke(Color.WHITE);
            setStrokeWidth(3);

        }
    };

    //clickable back to menu button, resets game parameters as well
    public Text backToMenu = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 18));
            setFill(Color.WHITE);
            setLayoutX(310);
            setLayoutY(400);
            setStroke(Color.WHITE);
            setStrokeWidth(1);
            DropShadow ds = new DropShadow();
            ds.setColor(Color.BLACK);
            ds.setSpread(.2);
            setEffect(ds);
            //sets color to yellow on mouse hover
            setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setFill(Color.web("FCD12A"));
                    setStroke(Color.web("FCD12A"));
                }
            });
            //sets back to white when mouse exits
            setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setFill(Color.WHITE);
                    setStroke(Color.WHITE);
                }
            });
            //returns to main menu, hiding the instructions screen
            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    BreakoutGame.hideInstructions();
                }
            });
        }
    };

    //background color
    public Rectangle bg = new Rectangle(0, 0, 800, 450) {
        {

            setFill(Color.web("000846"));

        }
    };

    //indicates instructions
    public Text pressLeft = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 18));
            setFill(Color.WHITE);
            setLayoutX(210);
            setLayoutY(200);
            setStroke(Color.WHITE);
            setStrokeWidth(1);
            DropShadow ds = new DropShadow();
            ds.setColor(Color.BLACK);
            ds.setSpread(.2);
            setEffect(ds);

        }
    };
    //indicates instructions
    public Text pressRight = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 18));
            setFill(Color.WHITE);
            setLayoutX(200);
            setLayoutY(250);
            setStroke(Color.WHITE);
            setStrokeWidth(1);
            DropShadow ds = new DropShadow();
            ds.setColor(Color.BLACK);
            ds.setSpread(.2);
            setEffect(ds);

        }
    };
    //indicates instructions
    public Text pressSpace = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 18));
            setFill(Color.WHITE);
            setLayoutX(230);
            setLayoutY(300);
            setStroke(Color.WHITE);
            setStrokeWidth(1);
            DropShadow ds = new DropShadow();
            ds.setColor(Color.BLACK);
            ds.setSpread(.2);
            setEffect(ds);

        }
    };

}
