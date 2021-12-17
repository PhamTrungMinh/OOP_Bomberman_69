package Bomberman.entities.block;

import Bomberman.entities.item.FlameItem;
import Bomberman.entities.item.Items;
import Bomberman.entities.item.SpeedItem;
import Bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import Bomberman.entities.Entity;
import static Bomberman.Main.block;

public class Grass extends Entity {
    public Grass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
    }
}