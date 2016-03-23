package com.example.dawid.beerbench.Models;

/**
 * Created by Dawid on 23.03.2016.
 */
public class Beer {

    private String id;
    private String name;
    private String nameDisplay;
    private String description;
    private String abv;
    private String ibu;
    private String labelSmall;
    private String labelMedium;
    private String styleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameDisplay() {
        return nameDisplay;
    }

    public void setNameDisplay(String nameDisplay) {
        this.nameDisplay = nameDisplay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public String getLabelSmall() {
        return labelSmall;
    }

    public void setLabelSmall(String labelSmall) {
        this.labelSmall = labelSmall;
    }

    public String getLabelMedium() {
        return labelMedium;
    }

    public void setLabelMedium(String labelMedium) {
        this.labelMedium = labelMedium;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }
}
