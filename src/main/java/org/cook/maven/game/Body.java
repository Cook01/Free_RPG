package org.cook.maven.game;

import org.cook.maven.graphics.GraphicBody;

public class Body {
    private int walkSpeed;

    private Soul soul;
    private GraphicBody gBody;

    public Body(String skinName, boolean isPlayer, int x, int y) {
        walkSpeed = 4;

        gBody = new GraphicBody(skinName, x, y);

        if(isPlayer){
            soul = new PlayerSoul(this);
        } else {
            soul = new Soul();
        }

    }

    public GraphicBody getGBody(){
        return gBody;
    }

    public void walkLeft(){
        gBody.goLeft(walkSpeed, "WALK_LEFT");
    }
    public void walkRight(){
        gBody.goRight(walkSpeed, "WALK_RIGHT");
    }
    public void walkUp(){
        gBody.goUp(walkSpeed, "WALK_UP");
    }
    public void walkDown(){
        gBody.goDown(walkSpeed, "WALK_DOWN");
    }

    public void stand(String codeDir){
        gBody.stand(codeDir);
    }

    public Soul getSoul() {
        return soul;
    }
}
