package Bomberman.entities.block;

import javafx.scene.image.Image;
import Bomberman.entities.Entity;

public class Portal extends Entity {
    public static boolean isPortal = false;

    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
}