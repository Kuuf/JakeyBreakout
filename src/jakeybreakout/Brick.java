/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jakeybreakout;

import com.michaelcotterell.game.Game;
import com.michaelcotterell.game.GameTime;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Brick extends Rectangle {

    public int hp = 3;
    public Color color = Color.web("FCD12A");
    public int colorCounter = 0;
    public boolean canCollide = true;

    public boolean vertical = false;
    public boolean horizontal = false;
    public boolean isLeft = false;
    public boolean isTop = false;
    public int brickX;
    public int brickY;
    public static boolean isColliding = false;

    public Brick() {
        setTranslateX(50);
        setTranslateY(30);
        setHeight(20);
        setWidth(98);
        setFill(color);
        setStroke(Color.BLACK);
        DropShadow ds = new DropShadow();
        ds.setColor(color);
        Reflection reflection = new Reflection();

        this.setStrokeWidth(1);

    }

    /**
     * Detects what side the ball hits the brick and sends it to Ball.java
     *
     * @return
     */
    private boolean collision() {

        //object of intersection between ball and brick
        Shape intersection = Rectangle.intersect(BreakoutGame.ball, this);

        //if brick is dead, then it returns a false collision value
        if (!canCollide) {
            return false;
        }
        //if ball hits a brick
        if (BreakoutGame.ball.getBoundsInParent().intersects(getBoundsInParent())) {
            //helper for when colliding with 2 blocks at once
            isColliding = true;

            colorCounter += 1;
            setColor();

            //variables representing ball hitting each side
            boolean isTop = false;
            boolean isBottom = false;
            boolean isLeft = false;
            boolean isRight = false;

            //finding dimension values for intersection
            double intersectionWidth = Math.abs(intersection.getBoundsInParent().getMaxX()
                    - intersection.getBoundsInParent().getMinX());
            double intersectionHeight = Math.abs(intersection.getBoundsInParent().getMaxY()
                    - intersection.getBoundsInParent().getMinY());
           
            /**
             * algorithm that finds out which side or corner ball hits
             */
            //top
            if (intersection.getBoundsInParent().getMinY() == this.getBoundsInParent().getMinY()) {
                isTop = true;
            } //top
            //bottom
            else if (intersection.getBoundsInParent().getMaxY() == this.getBoundsInParent().getMaxY()) {
                isBottom = true;
            }//bottom
            //left
            if (intersection.getBoundsInParent().getMinX() == this.getBoundsInParent().getMinX()) {
                isLeft = true;
            } // left
            //right
            if (intersection.getBoundsInParent().getMaxX() == this.getBoundsInParent().getMaxX()) {
                isRight = true;
            } // right
            //bottom right & left corners
            if ((isBottom && isLeft) || (isBottom && isRight)) {

                //ball moving downward
                if (BreakoutGame.ball.vY < 0) {
                    BreakoutGame.ball.bounceOffVerticalWall();
                } //ball moving upward
                else {
                    //hits more of bottom than side
                    if (intersectionWidth > intersectionHeight) {
                        BreakoutGame.ball.bounceOffHorizontalWall();
                    } //hits more of side than bottom
                    else {
                        BreakoutGame.ball.bounceOffVerticalWall();
                    }
                }
            } // bottom right & left corners
            // top right & left corners
            else if ((isTop && isRight) || (isTop && isLeft)) {
                isColliding = false;
                //ball moving downward
                if (BreakoutGame.ball.vY < 0) {
                    //hits more of top than side
                    if (intersectionWidth > intersectionHeight) {
                        BreakoutGame.ball.bounceOffHorizontalWall();
                    }//hits more of side than top
                    else {
                        BreakoutGame.ball.bounceOffVerticalWall();
                    }
                } // ball moving upward
                else {
                    BreakoutGame.ball.bounceOffVerticalWall();
                }
            } // top right & left corner

            //if didn't hit any corners
            if (isTop) {
                isColliding = false;
                BreakoutGame.ball.bounceOffHorizontalWall();
            }
            if (isBottom) {
                isColliding = false;
                BreakoutGame.ball.bounceOffHorizontalWall();
            }
            if (isLeft) {
                isColliding = false;
                BreakoutGame.ball.bounceOffVerticalWall();
            }
            if (isRight) {
                isColliding = false;
                BreakoutGame.ball.bounceOffVerticalWall();
            }

        }

        //returns if ball hit brick
        return BreakoutGame.ball.getBoundsInParent().intersects(getBoundsInParent());
    } // collision

    /**
     * sets color of brick
     *
     * @param color
     */
    public void setColor() {
        //normal state, setting brick to yellow
        if (colorCounter == 0) {

            color = Color.web("FCD12A");
            setFill(color);

        }
        //setting brick to orange
        if (colorCounter == 1) {

            color = Color.web("FFA800");
            setFill(color);
            

        }
        //seting brick to red
        if (colorCounter == 2) {
            color = Color.web("FB4603");
            setFill(color);
        }
        /**setting brick to background color and removing collision properties
         * also adds 1 to score and subtracts 1 from brickCount object
         */
        if (colorCounter == 3) {
            color = Color.web("000944");
            setFill(color);
            setStroke(color);
            canCollide = false;
            BreakoutGame.brickCount -= 1;
            BreakoutGame.score += 1;
        }

    } // setColor

    public void update(Game game, GameTime gameTime) {
        /**
         * honestly not sure why this needs to be here, but if it's not
         * then the collision doesn't work correctly
         */
        
        if (collision() == true) {

        }
      
    } // update

} // Brick
