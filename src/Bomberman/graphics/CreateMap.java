package Bomberman.graphics;

import Bomberman.entities.Entity;
import Bomberman.entities.block.Brick;
import Bomberman.entities.block.Grass;
import Bomberman.entities.block.Wall;
import Bomberman.entities.character.Animal;
import Bomberman.entities.character.Balloon;
import Bomberman.entities.character.Oneal;
import Bomberman.entities.item.FlameItem;
import Bomberman.entities.item.SpeedItem;

import static Bomberman.Main.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CreateMap {
    public CreateMap(String level) {
        System.out.println(System.getProperty("user.dir"));
        final File fileName = new File(level);
        try (FileReader inputFile = new FileReader(fileName)) {
            Scanner sc = new Scanner(inputFile);
            String line = sc.nextLine();
            System.out.println(line);
            StringTokenizer tokens = new StringTokenizer(line);
            _level = Integer.parseInt(tokens.nextToken());
            _height = Integer.parseInt(tokens.nextToken());
            _width = Integer.parseInt(tokens.nextToken());

            while (sc.hasNextLine()) {
                idObjects = new int[_width][_height];
                listKill = new int[_width][_height];
                for (int i = 0; i < _height; ++i) {
                    String s = sc.nextLine();
                    for (int j = 0; j < _width; j++) {
                        Entity entity = null;
                        char c = s.charAt(j);
                        switch (c) {
                            case '#':
                                entity = new Wall(j, i, Sprite.wall.getFxImage());
                                idObjects[j][i] = 2;
                                break;
                            case '*':
                                entity = new Brick(j, i, Sprite.brick.getFxImage());
                                idObjects[j][i] = 3;
                                break;
                            case 's':
                                entity = new SpeedItem(j, i, Sprite.brick.getFxImage());
                                idObjects[j][i] = 6;
                                break;
                            case 'f':
                                entity = new FlameItem(j, i, Sprite.brick.getFxImage());
                                idObjects[j][i] = 7;
                                break;
                            case '1':
                                entity = new Grass(j, i, Sprite.grass.getFxImage());
                                Animal enemy1 = new Balloon(j, i, Sprite.ballom_left1.getFxImage());
                                enemy.add(enemy1);
                                break;
                            case '2':
                                entity = new Grass(j, i, Sprite.grass.getFxImage());
                                Animal enemy4 = new Oneal(j, i, Sprite.oneal_right1.getFxImage());
                                enemy.add(enemy4);
                                break;
                            case 'p':
                                portalX = j;
                                portalY = i;
                                entity = new Grass(j, i, Sprite.grass.getFxImage());
                                break;
                            default:
                                entity = new Grass(j, i, Sprite.grass.getFxImage());
                                idObjects[j][i] = 0;
                        }
                        block.add(entity);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
