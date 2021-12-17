package Bomberman.levels;

import javafx.scene.image.Image;

import static Bomberman.Main._level;
import static Bomberman.Main.authorView;
import static Bomberman.entities.block.Portal.isPortal;

public class NextLevel {
    public static boolean wait;
    public static long waitingTime;

    public static void waitToLevelUp() {
        if (wait) {
            Image waitToNext = new Image("images/levelUp.png");
            authorView.setImage(waitToNext);
            long now = System.currentTimeMillis();
            if (now - waitingTime > 3000) {
                switch (_level) {
                    case 1:
                        isPortal = false;
                        new Level2();
                        break;
                    case 2:
                        new Level1();
                }
                wait = false;
            }
        }
    }
}