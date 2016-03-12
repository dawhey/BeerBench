package com.example.dawid.beerbench.Models;

/**
 * Created by Dawid on 12.03.2016.
 */
public class Style {

    private int mId;
    private String mName;
    private String mShortName;
    private String mDescription;
    private String mIbuMin;
    private String mIbuMax;
    private String mAlcMin;
    private String mAlcMax;
    private String mPlatMin;
    private String mPlatMax;


    public Style(int id, String name) {
        this.mId = id;
        this.mName = name;
    }

    public Style(int mId, String mName, String mShortName, String mDescription, String mIbuMin, String mIbuMax, String mAlcMin, String mAlcMax, String mPlatMin, String mPlatMax) {
        this.mId = mId;
        this.mName = mName;
        this.mShortName = mShortName;
        this.mDescription = mDescription;
        this.mIbuMin = mIbuMin;
        this.mIbuMax = mIbuMax;
        this.mAlcMin = mAlcMin;
        this.mAlcMax = mAlcMax;
        this.mPlatMin = mPlatMin;
        this.mPlatMax = mPlatMax;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getShortName() {
        return mShortName;
    }

    public void setShortName(String mShortName) {
        this.mShortName = mShortName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getIbuMin() {
        return mIbuMin;
    }

    public void setIbuMin(String mIbuMin) {
        this.mIbuMin = mIbuMin;
    }

    public String getIbuMax() {
        return mIbuMax;
    }

    public void setIbuMax(String mIbuMax) {
        this.mIbuMax = mIbuMax;
    }

    public String getAlcMin() {
        return mAlcMin;
    }

    public void setAlcMin(String mAlcMin) {
        this.mAlcMin = mAlcMin;
    }

    public String getAlcMax() {
        return mAlcMax;
    }

    public void setAlcMax(String mAlcMax) {
        this.mAlcMax = mAlcMax;
    }

    public String getPlatMin() {
        return mPlatMin;
    }

    public void setPlatMin(String mPlatMin) {
        this.mPlatMin = mPlatMin;
    }

    public String getPlatMax() {
        return mPlatMax;
    }

    public void setPlatMax(String mPlatMax) {
        this.mPlatMax = mPlatMax;
    }
}