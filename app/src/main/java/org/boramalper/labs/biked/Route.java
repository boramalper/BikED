package org.boramalper.labs.biked;

import com.google.maps.android.data.kml.KmlLayer;

/**
 * Created by bora on 07.10.2017.
 */

class Route {
    public String name, title, level, description;
    public String[] tags;
    public float length;
    public int titleColor;
    public KmlLayer kmlLayer;

    // title is name stylised!

    Route(String name, String title, int titleColor, String level, float length, String[] tags, String description) {
        this.name = name;
        this.title = title;
        this.titleColor = titleColor;
        this.level = level;
        this.length = length;
        this.tags = tags;
        this.description = description;
        this.kmlLayer = null;
    }
}
