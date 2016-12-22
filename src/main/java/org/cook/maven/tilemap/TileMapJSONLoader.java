package org.cook.maven.tilemap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;

public class TileMapJSONLoader {


    public static TileMap loadFromJSONFile(String fileName){

        JSONParser parser = new JSONParser();

        try{
            InputStream fileStream = TileMapJSONLoader.class.getResourceAsStream(fileName);
            InputStreamReader reader = new InputStreamReader(fileStream);

            java.lang.Object obj = parser.parse(reader);
            JSONObject jsonMap = (JSONObject)obj;

            return new TileMap(jsonMap);
        }catch (Exception e){
            e.printStackTrace();
            return new TileMap();
        }
    }
}
