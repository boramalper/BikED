package org.boramalper.labs.biked;

/**
 * Created by bora on 07.10.2017.
 */

class Route {
    public String name, title, level, description;
    public String[] tags;
    public float length;

    // title is name stylised!

    Route(String name, String title, String level, float length, String[] tags, String description) {
        this.name = name;
        this.title = title;
        this.level = level;
        this.length = length;
        this.tags = tags;
        this.description = description;

    }
}
