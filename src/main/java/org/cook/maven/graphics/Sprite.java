package org.cook.maven.graphics;

import javafx.scene.canvas.GraphicsContext;

public class Sprite {
    private SpriteSheet spriteSheet;
    private double duration;


    public Sprite(String spriteSheet, double duration){
        this.spriteSheet = new SpriteSheet(spriteSheet);
        this.duration = duration;
    }

    public Sprite() {

    }

    public void drawFrame(GraphicsContext gc, int xOnMap, int yOnMap, Camera camera, double time)
    {
        if(camera.isOnCamera(xOnMap, yOnMap) || camera.isOnCamera(xOnMap + spriteSheet.getSpriteWidth(), yOnMap) || camera.isOnCamera(xOnMap, yOnMap + spriteSheet.getSpriteHeight()) || camera.isOnCamera(xOnMap + spriteSheet.getSpriteWidth(), yOnMap + spriteSheet.getSpriteHeight())){
            int index = (int)((time % (spriteSheet.getSpriteCount() * duration)) / duration);

            int xFrame = 0;
            int yFrame = 0;
            if(index%spriteSheet.getColumns() == 0){
                xFrame = (index%spriteSheet.getColumns())*spriteSheet.getSpriteWidth();
                yFrame = (index/spriteSheet.getColumns())*spriteSheet.getSpriteHeight();
            } else {
                xFrame = ((index%spriteSheet.getColumns())*(spriteSheet.getSpriteWidth() + spriteSheet.getSpacing()));
                yFrame = ((index/spriteSheet.getColumns())*(spriteSheet.getSpriteHeight()+ spriteSheet.getSpacing()));
            }


            int destX = xOnMap - camera.getMinX();
            int destY = yOnMap - camera.getMinY();

            gc.drawImage(spriteSheet.getImage(), xFrame, yFrame, spriteSheet.getSpriteWidth(), spriteSheet.getSpriteHeight(), destX, destY, spriteSheet.getSpriteWidth(), spriteSheet.getSpriteHeight());
        }
    }

    public SpriteSheet getSpriteSheet(){
        return spriteSheet;
    }
}
