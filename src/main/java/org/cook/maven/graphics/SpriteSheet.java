package org.cook.maven.graphics;

import javafx.scene.image.Image;
import org.apache.commons.io.FilenameUtils;
import org.cook.maven.utils.OptParser;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;


public class SpriteSheet {
    private int columns;

    private Image image;

    private int imageHeight;
    private int imageWidth;

    private int margin;

    private int spacing;

    private String name;

    private int spriteCount;

    private int spriteHeight;
    private int spriteWidth;

    private String transparentcolor;

    public SpriteSheet(String spriteSheetName){
        String spriteSheetInfoName = spriteSheetName.replaceAll(FilenameUtils.getExtension(spriteSheetName), "opt");

        InputStream spriteSheetStream = this.getClass().getResourceAsStream(spriteSheetName);
        this.image = new Image(spriteSheetStream);

        Scanner optScanner = new Scanner(this.getClass().getResourceAsStream(spriteSheetInfoName));
        HashMap optMap = OptParser.parse(optScanner);

        this.imageWidth = (int) image.getWidth();
        this.imageHeight = (int) image.getHeight();

        this.margin = Integer.valueOf((String)optMap.get("margin"));
        this.spacing = Integer.valueOf((String)optMap.get("spacing"));

        this.spriteWidth = Integer.valueOf((String)optMap.get("spriteWidth"));
        this.spriteHeight = Integer.valueOf((String)optMap.get("spriteHeight"));

        this.name = (String) optMap.get("name");

        int x = 0;
        int countX = 0;
        x += margin;
        do{
            x += spriteWidth;
            x += spacing;
            countX ++;
        } while(x < imageWidth-margin);

        int y = 0;
        int countY = 0;
        y += margin;
        do{
            y += spriteHeight;
            y += spacing;
            countY ++;
        } while(y < imageHeight-margin);

        this.columns = countX;
        this.spriteCount = countX*countY;
    }

    public int getSpriteCount() {
        return spriteCount;
    }

    public void setSpriteCount(int spriteCount) {
        this.spriteCount = spriteCount;
    }

    public int getSpriteWidth(){
        return spriteWidth;
    }

    public int getSpriteHeight(){
        return spriteHeight;
    }

    public int getColumns(){
        return columns;
    }

    public Image getImage(){
        return image;
    }

    public int getSpacing(){
        return spacing;
    }
}
