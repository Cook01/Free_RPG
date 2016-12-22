package org.cook.maven.graphics;

import org.cook.maven.game.Body;

public class Camera {
    private int minX;
    private int minY;

    private int width;
    private int height;

    private boolean isFollowingABody;
    private Body bodyToFollow;

    public Camera(int xMin, int yMin, int width, int height){
        this.minX = xMin;
        this.minY = yMin;

        this.width = width;
        this.height = height;

        isFollowingABody = false;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxX() {
        return minX + width;
    }

    public int getMaxY() {
        return minY + height;
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

    public void setMaxX(int maxX) {
        minX = maxX - width;
    }

    public void setMaxY(int maxY) {
        minY = maxY - height;
    }

    public boolean isOnCamera(int xOnMap, int yOnMap) {
        return ((xOnMap >= minX && yOnMap >= minY) && (xOnMap <= minX+width && yOnMap <= minY+height));
    }

    public void followBody(Body body) {
        this.bodyToFollow = body;
        isFollowingABody = true;
    }

    public void update(int mapBorderMinX, int mapBorderMinY, int mapBorderMaxX, int mapBorderMaxY){
        minX = bodyToFollow.getGBody().getXOnMap() - ((width-bodyToFollow.getGBody().getWidth())/2);
        minY = bodyToFollow.getGBody().getYOnMap() - ((width-bodyToFollow.getGBody().getHeight())/2);

        if(minX < mapBorderMinX){
            minX = mapBorderMinX;
        }
        if(getMaxX() > mapBorderMaxX){
            setMaxX(mapBorderMaxX);
        }
        if(minY < mapBorderMinY){
            minY = mapBorderMinY;
        }
        if(getMaxY() > mapBorderMaxY){
            setMaxY(mapBorderMaxY);
        }
    }
}
