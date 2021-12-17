package Bomberman.levels;

import javafx.scene.image.Image;
import Bomberman.entities.character.Animal;
import Bomberman.graphics.CreateMap;
import Bomberman.graphics.Sprite;

import static Bomberman.Main.*;
import static Bomberman.control.Menu.*;
import static Bomberman.entities.character.Bomber.swapKill;
import static Bomberman.entities.block.Bomb.isBomb;
import static Bomberman.entities.block.Bomb.powerBomb;
import static Bomberman.entities.item.SpeedItem.speed;
import static Bomberman.Sound.SoundManager.isSoundDied;
import static Bomberman.Sound.SoundManager.isSoundTitle;

public class Level1 {
    public Level1() {
        enemy.clear();
        block.clear();
        swapKill = 1;
        powerBomb = 0;
        new CreateMap("res/levels/Level1.txt");
        player.setLife(true);
        player.setX(32);
        player.setY(32);
        isSoundDied = false;
        isSoundTitle = false;
        timeNumber = 120;
        Score = 0;
        isBomb = 0;
        speed = 1;

        player.setImg(Sprite.player_right_2.getFxImage());
        Image transparent = new Image("images/transparent.png");
        authorView.setImage(transparent);


        for (Animal animal : enemy) {
            animal.setLife(true);
        }
    }
}
