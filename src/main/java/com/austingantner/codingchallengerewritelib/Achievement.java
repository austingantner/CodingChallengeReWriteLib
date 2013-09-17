package com.austingantner.codingchallengerewritelib;

/**
 *
 * @author austingantner
 */
public class Achievement {

    public final int ID;
    public final String name;
    public final String description;
    public final String evalString;

    public Achievement(int ID, String name, String description, String evalString) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.evalString = evalString;
    }
}
