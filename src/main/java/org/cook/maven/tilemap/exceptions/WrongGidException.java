package org.cook.maven.tilemap.exceptions;

public class WrongGidException extends Exception{
    private final int gid;

    public WrongGidException(int gid) {
        this.gid = gid;
    }

    public String toSting(){
        return "Gid : " + gid + " not found";
    }
}
