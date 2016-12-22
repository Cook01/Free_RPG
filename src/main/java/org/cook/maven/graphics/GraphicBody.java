package org.cook.maven.graphics;

import javafx.scene.canvas.GraphicsContext;
import org.cook.maven.game.Body;

import java.util.HashMap;

public class GraphicBody {
    private Sprite activeSprite;
    private HashMap<String, Sprite> skin;

    private int xOnMap;
    private int yOnMap;

    private int width;
    private int height;

    public GraphicBody(String skinName, int x, int y){
        skin = new HashMap<String, Sprite>();

        Sprite standLeft = new Sprite("/img/sprite/" + skinName + "/Stand/" + skinName + "_STAND_LEFT.png", 0.1);
        Sprite standRight = new Sprite("/img/sprite/" + skinName + "/Stand/" + skinName + "_STAND_RIGHT.png", 0.1);
        Sprite standUp = new Sprite("/img/sprite/" + skinName + "/Stand/" + skinName + "_STAND_UP.png", 0.1);
        Sprite standDown = new Sprite("/img/sprite/" + skinName + "/Stand/" + skinName + "_STAND_DOWN.png", 0.1);

        Sprite walkLeft = new Sprite("/img/sprite/" + skinName + "/Walk/" + skinName + "_WALK_LEFT.png", 0.1);
        Sprite walkRight = new Sprite("/img/sprite/" + skinName + "/Walk/" + skinName + "_WALK_RIGHT.png", 0.1);
        Sprite walkUp = new Sprite("/img/sprite/" + skinName + "/Walk/" + skinName + "_WALK_UP.png", 0.1);
        Sprite walkDown = new Sprite("/img/sprite/" + skinName + "/Walk/" + skinName + "_WALK_DOWN.png", 0.1);

        skin.put("STAND_LEFT", standLeft);
        skin.put("STAND_RIGHT", standRight);
        skin.put("STAND_UP", standUp);
        skin.put("STAND_DOWN", standDown);

        skin.put("WALK_LEFT", walkLeft);
        skin.put("WALK_RIGHT", walkRight);
        skin.put("WALK_UP", walkUp);
        skin.put("WALK_DOWN", walkDown);

        xOnMap = x;
        yOnMap = y;

        width = skin.get("STAND_DOWN").getSpriteSheet().getSpriteWidth();
        height = skin.get("STAND_DOWN").getSpriteSheet().getSpriteHeight();

        activeSprite = skin.get("STAND_DOWN");
    }

    public void goLeft(int speed, String skinToDisplay){
        xOnMap -= speed;
        activeSprite = skin.get(skinToDisplay);
    }
    public void goRight(int speed, String skinToDisplay){
        xOnMap += speed;
        activeSprite = skin.get(skinToDisplay);
    }
    public void goUp(int speed, String skinToDisplay){
        yOnMap -= speed;
        activeSprite = skin.get(skinToDisplay);
    }
    public void goDown(int speed, String skinToDisplay){
        yOnMap += speed;
        activeSprite = skin.get(skinToDisplay);
    }

    public void stand(String codeDir) {
        activeSprite = skin.get("STAND_" + codeDir);
    }

    public void drawGraphicBody(GraphicsContext gc, double t, Camera camera){
        activeSprite.drawFrame(gc, xOnMap, yOnMap, camera, t);
    }

    public int getXOnMap(){
        return xOnMap;
    }

    public int getYOnMap(){
        return yOnMap;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
