package jakeybreakout;

import com.michaelcotterell.game.Game;
import com.michaelcotterell.game.GameTime;
import javafx.geometry.Bounds;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import static javafx.scene.shape.StrokeType.INSIDE;

public class Paddle extends Rectangle {

    public int hp = 3;
    public static double x = 0;
    public double speed;
    public static boolean canMove = true;

    public Bounds ballBounds = BreakoutGame.ball.getBoundsInParent();
    public Bounds paddleBounds = this.getBoundsInParent();

    public Paddle() {
        setHeight(10);
        setWidth(100);
        setFill(Color.WHITE);
        setLayoutX(350);
        setLayoutY(380);
        System.out.print(x);
        Reflection reflection = new Reflection();
        reflection.setTopOffset(1);
        setEffect(reflection);
        setStroke(Color.BLACK);
        setStrokeWidth(1);
        setStrokeType(INSIDE);

    }

    //sets paddle back to center
    public void reset() {
        setTranslateX(0);

    }
    //boolean object for if ball has collided with another object
    public boolean collision() {
        return BreakoutGame.ball.getBoundsInParent().intersects(this.getBoundsInParent());
    }

    //update method for paddle
    public void update(Game game, GameTime gameTime) {
        speed = BreakoutGame.ball.speed + 2;

        //updates paddle if allowed to move
        if (canMove) {
            //moves paddle left when user presses left arrow
            if (game.getKeyManager().isKeyPressed(KeyCode.LEFT)) {

                if (x >= -300) {
                    x -= (speed * 1.5);
                }
                this.setTranslateX(x);

            }
            //moves paddle right when user presses right arrow

            if (game.getKeyManager().isKeyPressed(KeyCode.RIGHT)) {
                if (x <= 300) {
                    x += (speed * 1.5);
                }
                this.setTranslateX(x);
                //System.out.println("Paddle Speed: " + (7 + speed));
            }
        }
        //bounces ball off of paddle
        if (collision()) {
            BreakoutGame.ball.bounceOffHorizontalWall();
        }

        //animating paddle back to middle after level ends
        if (BreakoutGame.resetPaddle) {

            if ((Math.abs(x - 4) > 0)) {
                if (x < 0) {
                    x += 4;
                }
                if (x > 0) {
                    x -= 4;
                }

                setTranslateX(x);
                if (Math.abs(x) <= 4) {
                    BreakoutGame.resetPaddle = false;
                }
            }
        }

    } // update
} // Brick
