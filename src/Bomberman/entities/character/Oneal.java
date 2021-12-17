package Bomberman.entities.character;

import javafx.scene.image.Image;
import Bomberman.control.Move;
import Bomberman.graphics.Sprite;
import static Bomberman.control.Menu.Score;
import static Bomberman.Main.*;

public class Oneal extends Animal {
    private static int swapKill = 1;
    private static int countKill = 0;

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    public Oneal(int isMove, int swap, String direction, int count, int countToRun) {
        super(4, 1, "up", 0, 0);
    }

    public Oneal(boolean life) {
        super(life);
    }

    public Oneal() {
    }

    private void killOneal(Animal animal) {
        if (countKill % 8 == 0) {
            if (swapKill == 1) {
                animal.setImg(Sprite.oneal_dead.getFxImage());
                swapKill = 2;
            } else if (swapKill == 2) {
                animal.setImg(Sprite.player_dead3.getFxImage());
                swapKill = 3;
            } else {
                animal.setLife(false);
                enemy.remove(animal);
                swapKill = 1;
                Score = Score +200;
            }
        }
    }

    @Override
    public void update() {
        countKill++;
        for (Animal animal : enemy) {
            if (animal instanceof Oneal && !animal.life)
                killOneal(animal);
        }

        if (this.y % 16 == 0 && this.x % 16 == 0) {
            if (player.getX() < this.x) {
                Move.left(this);
            }
            if (player.getX() > this.x) {
                Move.right(this);
            }
            if (player.getY() > this.y) {
                Move.down(this);
            }
            if (player.getY() < this.y) {
                Move.up(this);
            }
        }
    }
}
