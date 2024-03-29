package Bomberman;

import Bomberman.Sound.SoundManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Bomberman.control.Menu;
import Bomberman.control.Move;
import Bomberman.entities.Entity;
import Bomberman.entities.character.Animal;
import Bomberman.entities.character.Bomber;
import Bomberman.entities.block.Bomb;
import Bomberman.entities.block.Portal;
import Bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static Bomberman.control.Menu.*;
import static Bomberman.entities.block.Portal.*;
import static Bomberman.levels.NextLevel.*;
import static Bomberman.Sound.SoundManager.updateSound;

public class Main extends Application {

    /**
     * The default size of the window
     * H: 480px W: 800px
     */
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static int _width = 0;
    public static int _height = 0;
    public static int _level = 1;

    public static List<Entity> block = new ArrayList<>(); //Contains fixed entities
    public static List<Animal> enemy = new ArrayList<>();       //Contains enemy entities
    public static int[][] idObjects;
    public static int[][] listKill;     //Array containing dead positions
    public static Animal player;
    public static boolean running;
    public static ImageView authorView;
    public static int portalX, portalY;

    private GraphicsContext gc;
    private Canvas canvas;
    private long lastTime;

    public static Stage mainStage = null;


    public static void main(String[] args) {
        Application.launch(Main.class);
    }

    @Override
    public void start(Stage stage) {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        canvas.setTranslateY(32);
        gc = canvas.getGraphicsContext2D();
        Image author = new Image("images/intro.png");
        authorView = new ImageView(author);
        authorView.setX(-500);
        authorView.setY(-180);
        authorView.setScaleX(0.5);
        authorView.setScaleY(0.5);
        Group root = new Group();
        Menu.createMenu(root);
        root.getChildren().add(canvas);
        root.getChildren().add(authorView);
        root.getChildren().add(statusGame);
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            if (player.isLife())
                switch (event.getCode()) {
                    case UP:
                        new SoundManager("sound/sound_leg.wav", "leg");
                        Move.up(player);
                        break;
                    case DOWN:
                        new SoundManager("sound/sound_leg.wav", "leg");
                        Move.down(player);
                        break;
                    case LEFT:
                        new SoundManager("sound/sound_leg.wav", "leg");
                        Move.left(player);
                        break;
                    case RIGHT:
                        new SoundManager("sound/sound_leg.wav", "leg");
                        Move.right(player);
                        break;
                    case SPACE:
                        new SoundManager("sound/set_boom.wav", "set");
                        Bomb.putBomb();
                        break;
                }
        });
        stage.setScene(scene);
        stage.setTitle("Bomberman");
        mainStage = stage;
        mainStage.show();

        lastTime = System.currentTimeMillis();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (running) {
                    render();
                    update();
                    time();
                    updateMenu();
                }
            }
        };
        timer.start();
        player = new Bomber(1, 1, Sprite.player_right_2.getFxImage());
        player.setLife(false);
    }

    public void update() {
        block.forEach(Entity::update);
        enemy.forEach(Entity::update);
        player.update();

        player.setCountToRun(player.getCountToRun() + 1);
        if (player.getCountToRun() == 4) {
            Move.checkRun(player);
            player.setCountToRun(0);
        }

        for (Animal a : enemy) {
            a.setCountToRun(a.getCountToRun() + 1);
            if (a.getCountToRun() == 8) {
                Move.checkRun(a);
                a.setCountToRun(0);
            }
        }

        if (enemy.size() == 0 && !isPortal && !wait) {
            Entity portal = new Portal(portalX, portalY, Sprite.portal.getFxImage());
            block.add(portal);
            if (player.getX() / 32 == portal.getX() / 32 && player.getY() / 32 == portal.getY() / 32) {
                wait = true;
                waitingTime = System.currentTimeMillis();
            }
        }
        waitToLevelUp();
        updateSound();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        block.forEach(g -> g.render(gc));
        enemy.forEach(g -> g.render(gc));
        player.render(gc);
    }

    public void time() {
        long now = System.currentTimeMillis();
        if (now - lastTime > 1000) {
            lastTime = System.currentTimeMillis();
            time.setText("Time: " + timeNumber);
            timeNumber--;
            if (timeNumber < 0)
                player.setLife(false);
        }
    }

    public static Entity getEntityAt(int x, int y) {
        for (Entity e: block) {
            if (e.getX() == x && y == e.getY()) {
                return e;
            }
        }
        return null;
    }
}
