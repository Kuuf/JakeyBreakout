package jakeybreakout;

import com.michaelcotterell.game.Game;
import com.michaelcotterell.game.GameTime;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javax.swing.JPanel;

/**
 *
 * @author jacobwall
 */
public class InfoBar extends HBox {

    public int score;
    public int level;
    public int lives;
    public int offsetX = -25;
    public int offsetY = -5;

    //indicates current level
    public Text levelLabel = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 20));
            setFill(Color.WHITE);
        }
    };
    //indicates current score
    public Text scoreLabel = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 20));
            setFill(Color.WHITE);
        }
    };
    //indicates current amount of lives
    public Text lifeLabel = new Text() {
        {
            setFont(Font.font("Kohinoor Devanagari", FontWeight.BOLD, 20));
            setFill(Color.WHITE);
        }
    };

    public JPanel levelPanel = new JPanel();
    public JPanel scorePanel = new JPanel();
    public JPanel lifePanel = new JPanel();

    public InfoBar() {
        levelLabel.setTranslateX(13 + offsetX);
        levelLabel.setTranslateY(10 + offsetY);
        levelLabel.setFill(Color.WHITE);

        scoreLabel.setTranslateX(290 + offsetX);
        scoreLabel.setTranslateY(10 + offsetY);
        scoreLabel.setFill(Color.WHITE);

        lifeLabel.setTranslateX(700 + offsetX);
        lifeLabel.setTranslateY(10 + offsetY);
        lifeLabel.setFill(Color.WHITE);

        getChildren().addAll(lifeLabel, levelLabel, scoreLabel);

    }

    //updates infobar with current info
    public void update(Game game, GameTime gameTime) {
        levelLabel.setText("Level " + BreakoutGame.level);
        scoreLabel.setText(BreakoutGame.score + "");
        lifeLabel.setText(BreakoutGame.lives + " Lives");
        gameOverMenu.score.setText("Final Score: " + BreakoutGame.score);
    }

}
