package Bomberman.entities.block;

import javafx.scene.image.Image;
import Bomberman.entities.Entity;
import Bomberman.graphics.Sprite;

import static Bomberman.Main.block;
import static Bomberman.Main.listKill;

public class Brick extends Entity {

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    private void checkHidden() {
        for (Entity entity : block) {
            if (entity instanceof Brick)
                if (listKill[entity.getX() / 32][entity.getY() / 32] == 4) {
                    entity.setImg(Sprite.grass.getFxImage());
                }
        }
    }

    @Override
    public void update() {
        checkHidden();
    }
}