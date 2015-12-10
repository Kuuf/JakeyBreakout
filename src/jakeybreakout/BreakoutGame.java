package jakeybreakout;

import com.michaelcotterell.game.Game;
import com.michaelcotterell.game.GameTime;
import java.util.Timer;
import java.util.ArrayList;
import java.util.TimerTask;
import javafx.animation.FadeTransition;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BreakoutGame extends Game {

    public InfoBar infoBar = new InfoBar();
    public static Ball ball = new Ball();
    public static ArrayList<Brick> bricks = new ArrayList();
    public static int speed = 2;
    public static int level = 1;
    public static int brickCount = 28;
    public static boolean loadingLevel = false;
    public static int score = 0;
    public static int lives = 3;
    public static boolean gameOver = false;
    public static boolean testMode = false;
    public static MainMenu mainMenu = new MainMenu();
    public static Instructions instructions = new Instructions();
    public static boolean startGame = false;
    public double tempVX = 0;
    public double tempVY = 0;
    public boolean isPaused = false;
    public static PauseMenu pauseMenu = new PauseMenu();
    public static boolean resetGame = false;
    public static gameOverMenu gameOverMenu = new gameOverMenu();
    public static boolean resetPaddle = false;
    public static boolean commandFromMainMenu = false;

    public static Paddle paddle = new Paddle();
    /**
     * Creates and fills the rows of bricks at the top
     */
    public static FlowPane brickBlock = new FlowPane() {
        {

            setPrefWrapLength(700);
            for (int i = 0; i < brickCount; i++) {
                bricks.add(new Brick());
            }
            int x = 50;
            int y = 30;

            for (Brick brick : bricks) {
                getChildren().add(brick);

            }

        }
    };

    /**
     * performs certain actions after each level
     * called when needing a timed task
     */
    public void execute(TimerTask task, long time) {
        new Timer() {
            {
                schedule(task, time);
            }
        };

    } // execute

    /**
     * sets parameters for next level, resets bricks, also displays level number
     */
    public TimerTask nextLevel = new TimerTask() {
        @Override
        public void run() {
            for (Brick brick : bricks) {
                brick.canCollide = true;
                brick.colorCounter = 0;
                brick.setColor();
            }
            BreakoutGame.level += 1;
            BreakoutGame.ball.speed += 1;

            levelText.setFill(Color.web("000944"));
            levelText.setStroke(Color.web("000944"));
        }
    };

    /**
     * text to appear before each level
     */
    private Text levelText = new Text() {
        {
            setTranslateX(375);
            setTranslateY(200);
            setFill(Color.WHITE);
            setFont(javafx.scene.text.Font.font("Kohinoor Devanagari", FontWeight.BOLD, 20));
            setStroke(Color.WHITE);
            setStrokeWidth(1);

        }
    };
    /**
     * text to appear when game is over
     */
    private Text gameOverText = new Text() {
        {
            setTranslateX(375);
            setTranslateY(200);
            setFill(Color.WHITE);
        }
    };
    // rectangle to hold the background
    private Rectangle bg = new Rectangle(0, 0, 800, 450) {
        {
            setFill(Color.web("000944"));

        }
    };

    /**
     * method for outside classes to call to show instruction screen
     */
    public static void showInstructions() {
        instructions.toFront();
    }

    /**
     * method for outside classes to call to hide instruction screen
     */
    public static void hideInstructions() {
        instructions.toBack();
    }

    /**
     * method for outside classes to call to game over menu
     */
    public static void hideGameOverMenu() {
        gameOverMenu.toBack();
        mainMenu.toFront();
    }

    /**
     * resets parameters after player loses, also displays game over screen
     */
    public void gameOver() {
        ball.vX = 0;
        ball.vY = 0;
        ball.speed = 0;
        paddle.canMove = false;
        gameOverMenu.setOpacity(.0001);

        FadeTransition ft = new FadeTransition(Duration.millis(3000), gameOverMenu);
        ft.setFromValue(.0001);
        ft.setToValue(1);
        gameOverMenu.toFront();

        ft.play();

    } // gameOver

    /**
     * resets parameters of game
     */
    public void resetGame() {
        isPaused = false;
        resetGame = false;
        if (!commandFromMainMenu) {
            resetPaddle = true;
        }
        BreakoutGame.commandFromMainMenu = false;

        ball.reset();
        score = 0;
        lives = 3;
        level = 1;
        gameOver = false;
        gameOverMenu.toBack();

        //resets bricks at top
        for (Brick brick : bricks) {
            brick.canCollide = true;
            brick.colorCounter = 0;
            brick.setColor();
        }

        //allows objects to move and then begins game
        execute(new TimerTask() {
            @Override
            public void run() {
                paddle.canMove = true;
                ball.canMove = true;
                startGame = true;

            }
        }, 150);
    } // resetGame

    /**
     * pauses the game
     */
    public void pauseGame() {
        execute(new TimerTask() {
            @Override
            public void run() {
                ball.canMove = false;
                isPaused = true;
                paddle.canMove = false;

            }
        }, 100);
        pauseMenu.toFront();
    }

    /**
     * ends the game and sets certain parameters accordingly
     */
    public static void endGame() {
        pauseMenu.toBack();
        mainMenu.toFront();
        brickCount = 28;
        gameOver = true;
        startGame = false;
    }

    /**
     * resumes game from pause menu
     */
    public void resumeGame() {
        execute(new TimerTask() {
            @Override
            public void run() {
                ball.canMove = true;
                isPaused = false;
                paddle.canMove = true;

            }
        }, 200);
        pauseMenu.toBack();
    }

    /**
     * for testing purposes prints out level and speed info
     */
    public void getLevelInfo() {
        System.out.println("Level: " + level);
        System.out.println("Speed: " + paddle.speed);
    }

    /**
     * Constructs a new Breakout game.
     *
     * @param stage the primary stage
     */
    public BreakoutGame(Stage stage) {
        super(stage, "BreakoutGame", 60, 800, 450);

        stage.setFocused(true);

        getSceneNodes().getChildren().addAll(gameOverMenu, mainMenu, pauseMenu, bg, paddle,
                instructions, levelText, brickBlock, infoBar, ball, gameOverText);
        mainMenu.toFront();
        instructions.toBack();
    } // TestGame

    @Override
    public void update(Game game, GameTime gameTime) {

        //lets paddle follow ball exactly for testing
        if (resetGame) {
            resetGame();
        }
        
        //updates the paddle, allowing it to move
        paddle.update(game, gameTime);

        if (startGame && !gameOver) {
            mainMenu.toBack();

            //pauses game when pressing space or ESC
            if (game.getKeyManager().isKeyPressed(KeyCode.SPACE)
                    || game.getKeyManager().isKeyPressed(KeyCode.ESCAPE)) {
                if (isPaused == false) {
                    pauseGame();
                } else {
                    resumeGame();
                }
            }

            
            if (lives == 0 && !gameOver) {
                gameOver = true;
                gameOver();

            }

            //updates ball and infobar at top
            ball.update(game, gameTime);
            infoBar.update(game, gameTime);

            //updates all bricks
            for (Brick brick : bricks) {
                brick.update(game, gameTime);
            }

            // level completed
            if (brickCount == 0) {

                loadingLevel = true;
                paddle.canMove = false;
                resetPaddle = true;

                //make ball dissapear and centering it
                ball.setStroke(Color.web("000944"));
                ball.setFill(Color.web("000944"));
                ball.vX = 0;
                ball.vY = 0;
                ball.posX = 0;
                ball.posY = 0;
                lives += 1;
                //make levelText appear
                levelText.setFill(Color.WHITE);
                levelText.setStroke(Color.WHITE);
                levelText.setText("Level " + (level + 1));
                //sets up next level
                execute(new TimerTask() {
                    @Override
                    public void run() {
                        //resetting brick grid
                        for (Brick brick : bricks) {
                            brick.canCollide = true;
                            brick.colorCounter = 0;
                            brick.setColor();
                        }

                        getLevelInfo();

                        //making ball visible and increases speed
                        ball.setStroke(Color.BLACK);
                        ball.setFill(Color.WHITE);
                        ball.bounceNum = 0;
                        level += 1;

                        ball.vX = speed + (2 * (level - 1));
                        ball.vY = speed + (2 * (level - 1));

                        paddle.speed += 2;

                        //removing levelText from view
                        levelText.setFill(Color.web("000944"));
                        levelText.setStroke(Color.web("000944"));

                        //unfreezing paddle
                        paddle.canMove = true;

                        loadingLevel = false;
                        resetPaddle = false;

                    }
                }, 3000);
                //resets brick count to original value for next level
                brickCount = 28;
            }
        }
    } // update

} // BreakoutGame

