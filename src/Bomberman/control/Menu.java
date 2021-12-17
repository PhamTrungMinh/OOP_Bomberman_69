package Bomberman.control;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import Bomberman.levels.*;

import static Bomberman.Main.*;

public class Menu {
    public static ImageView statusGame;
    public static Text level, score, time;
    public static int Score = 0, timeNumber = 120;

    public static void createMenu(Group root) {
        level = new Text("Level: 1");
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.WHITE);
        level.setX(416);
        level.setY(20);
        score = new Text("Score: 0");
        score.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        score.setFill(Color.WHITE);
        score.setX(512);
        score.setY(20);
        time = new Text("Times: 120");
        time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        time.setFill(Color.WHITE);
        time.setX(608);
        time.setY(20);

        Image newGame = new Image("images/newGame.png");
        statusGame = new ImageView(newGame);
        statusGame.setX(300);
        statusGame.setY(300);
        statusGame.setScaleX(0.5);
        statusGame.setScaleY(0.5);

        Pane pane = new Pane();
        pane.getChildren().addAll(level, score, time);
        pane.setMinSize(992, 30);
        pane.setMaxSize(900, 580);
        pane.setStyle("-fx-background-color: #353535");

        root.getChildren().add(pane);
        statusGame.setOnMouseClicked(event -> {
            new Level1();
            running = true;
            statusGame.setImage(null);
            updateMenu();
        });

    }

    public static void updateMenu() {
        level.setText("Level: " + _level);
        score.setText("Score: " + Score);
        if (!player.isLife()) {
            Image playGame = new Image("images/playGame.png");
            statusGame.setImage(playGame);
        }
    }
}
