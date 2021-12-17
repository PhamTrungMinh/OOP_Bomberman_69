package Bomberman.levels;

import javafx.scene.image.Image;
import Bomberman.entities.enemy.Animal;
import Bomberman.graphics.CreateMap;
import Bomberman.graphics.Sprite;

import static Bomberman.Main.*;
import static Bomberman.Main.enemy;
import static Bomberman.control.Menu.*;
import static Bomberman.entities.enemy.Bomber.swapKill;
import static Bomberman.entities.block.Bomb.isBomb;
import static Bomberman.entities.item.SpeedItem.speed;
import static Bomberman.Sound.SoundManager.isSoundDied;
import static Bomberman.Sound.SoundManager.isSoundTitle;

public class Level2 {
    public Level2() {
        enemy.clear();
        block.clear();
        swapKill = 1;
        new CreateMap("res/levels/Level2.txt");
        player.setLife(true);
        player.setX(32);
        player.setY(32);
        speed = 1;
        isSoundDied = false;
        isSoundTitle = false;
        timeNumber = 120;
        Score = 0;
        isBomb = 0;

        player.setImg(Sprite.player_right_2.getFxImage());
        Image transparent = new Image("images/transparent.png");
        authorView.setImage(transparent);

        for (Animal animal : enemy) {
            animal.setLife(true);
        }
    }
}
