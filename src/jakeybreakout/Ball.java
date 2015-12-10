/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jakeybreakout;

import com.michaelcotterell.game.Game;
import com.michaelcotterell.game.GameTime;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static javafx.scene.shape.StrokeType.INSIDE;

public class Ball extends Rectangle {

    public double speed = 5;
    public static double posX = 0;
    public static double posY = 0;
    public double vX = speed;
    public double vY = speed + 1;
    public static int bounceNum = 0;
    public boolean canMove = true;
    double maxSpeed;
    public boolean justReset = false;

    public Ball() {
        setFill(Color.WHITE);
        setHeight(10);
        setWidth(10);
        setLayoutX(395);
        setLayoutY(200);
        setStroke(Color.BLACK);
        setStrokeType(INSIDE);
        setStrokeWidth(1);

    }

    //generates starting direction of ball, either left or right

    public void generateStartingDirection() {
        Random rand = new Random();
        int randomNum = rand.nextInt(2) + 1;

        if (randomNum == 1) {
            vX = -vX;
        }

    }
    //resets ball parameters and postions

    public void reset() {
        setTranslateX(0);
        setTranslateY(0);
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
        posX = 0;
        posY = 0;
        bounceNum = 0;
        vX = 5;
        vY = 5;
        speed = 5;

    }

    //returns speed of ball
    public double getSpeed() {
        return speed;
    }

    //sets speed of ball
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //update method calls this in order to move ball
    public void move() {

        //bouncing off a vertical object, could be a wall or bottom of brick etc
        if (Math.abs(posX + vX) + 50 > 400.0) {
            bounceOffVerticalWall();
        }
        //bouncing off horizontal object, could be ceiling or side of brick etc
        if ((Math.abs(posY + vY) + 10 > 220.0) || posY + vY < -170) {
            bounceOffHorizontalWall();
        }

        //sets new y value according to velocity
        posY = posY + vY;
        this.setTranslateY(posY);

        /**
         * lets ball fall straight when beginning level 
         * so it has to hit the paddle
         */
        if (bounceNum > 0) {

            posX = posX + vX;
            this.setTranslateX(posX);
        }

    }

    //flips x values when hitting vertical wall
    public void bounceOffVerticalWall() {
        vX = -vX;

    }

    //flips y values when hitting horzontal wall
    public void bounceOffHorizontalWall() {
        //if game is just starting
        if (bounceNum == 0) {
            generateStartingDirection();
            BreakoutGame.paddle.canMove = true;
            bounceNum += 1;
        } //if misses the paddle, resets ball and paddle for next round
        else if (posY > 185) {
            BreakoutGame.lives -= 1;
            BreakoutGame.paddle.canMove = false;
            BreakoutGame.resetPaddle = true;
            double tempVX = vX;
            double tempVY = vY;

            bounceNum = 0;
            setFill(Color.web("000944"));
            setStroke(Color.web("000944"));
            vX = 0;
            vY = 0;
            posX = 0;
            posY = 0;
            setTranslateX(0);
            setTranslateY(0);
            if (BreakoutGame.lives != 0) {
                execute(new TimerTask() {
                    @Override
                    public void run() {
                        setFill(Color.WHITE);
                        setStroke(Color.BLACK);

                    }
                }, 1000);
                execute(new TimerTask() {
                    @Override
                    public void run() {

                        vX = tempVX;
                        vY = tempVY;

                        BreakoutGame.resetPaddle = false;

                    }
                }, 2000);
            }
        } else {
            //when hitting paddle
            //necessary to fix random bug. u kno how it iz
            if (posY > 160) {
                if (vY > 0) {
                    vY = -vY;
                }
            } else {
                vY = -vY;
            }
        }

    }

    //called when using timed functions
    public void execute(TimerTask task, long time) {
        new Timer() {
            {
                schedule(task, time);
            }
        };

    } // execute

    //updates ball, allowing it to move
    public void update(Game game, GameTime gameTime) {
        if (canMove) {
            move();
        }

        //System.out.println(speed);
    }

}
