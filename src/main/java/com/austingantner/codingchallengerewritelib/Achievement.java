package com.austingantner.codingchallengerewritelib;

/**
 *
 * @author austingantner
 */
public class Achievement {

    public int ID;
    public String name;
    public String description;
    public String evalString;

    public Achievement(int ID, String name, String description, String evalString) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.evalString = evalString;
    }
}
