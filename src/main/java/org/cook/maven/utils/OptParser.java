package org.cook.maven.utils;

import java.util.HashMap;
import java.util.Scanner;

public class OptParser {
    public static HashMap parse(Scanner optScanner) {
        HashMap result = new HashMap();

        while(optScanner.hasNextLine()){
            String line = optScanner.nextLine();

            String[] lineSplit = line.split(":");
            result.put(lineSplit[0], lineSplit[1]);
        }

        return result;
    }
}
