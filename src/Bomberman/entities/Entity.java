package Bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import Bomberman.graphics.Sprite;

public abstract class Entity {
    protected int x;

    protected int y;

    protected Image img;

    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity() {}

    public double distanceTo(Entity other) {
        return Math.sqrt(Math.pow(getX() - other.getX(), 2) +
                Math.pow(getY() - other.getY(), 2));
    }

    public boolean isCollidedWith(Entity other) {
        return distanceTo(other) < 32;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();
}
