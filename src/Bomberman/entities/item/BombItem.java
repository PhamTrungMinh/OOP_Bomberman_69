package Bomberman.entities.item;

import Bomberman.entities.Entity;
import Bomberman.entities.block.Bomb;
import Bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import static Bomberman.Main.*;
import static Bomberman.Main.player;

public class BombItem extends Items {
    public BombItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public BombItem(boolean received) {
        super(received);
    }

    public BombItem() {
    }

    @Override
    public void update() {
        for (Entity entity : block)
            if (entity instanceof BombItem && !this.received)
                if (listKill[entity.getX() / 32][entity.getY() / 32] == 4)
                    entity.setImg(Sprite.powerup_bombs.getFxImage());

        if (!this.received)
            if (player.getX() == this.x && player.getY() == this.y) {
                this.setImg(Sprite.grass.getFxImage());
                this.received = true;
                Bomb.numberBomb += 1;
            }
    }
}
