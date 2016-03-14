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
    private String mSrmMin;
    private String mSrmMax;
    private String mCategoryName;
    public static final String[] SRM_TABLE = {
            "#FFFFFF",
            "#FFE699",
            "#FFD878",
            "#FFCA5A",
            "#FFBF42",
            "#FBB123",
            "#F8A600",
            "#F39C00",
            "#EA8F00",
            "#E58500",
            "#DE7C00",
            "#D77200",
            "#CF6900",
            "#CB6200",
            "#C35900",
            "#BB5100",
            "#B54C00",
            "#B04500",
            "#A63E00",
            "#A13700",
            "#9B3200",
            "#952D00",
            "#8E2900",
            "#882300",
            "#821E00",
            "#7B1A00",
            "#771900",
            "#701400",
            "#6A0E00",
            "#660D00",
            "#5E0B00",
            "#5A0A02",
            "#600903",
            "#520907",
            "#4C0505",
            "#470606",
            "#440607",
            "#3F0708",
            "#3B0607",
            "#3A070B",
            "#36080A"
    };


    public Style(int id, String name) {
        this.mId = id;
        this.mName = name;
    }

    public Style(int mId, String mName, String mShortName, String mDescription, String mIbuMin, String mIbuMax, String mAlcMin, String mAlcMax, String mSrmMin, String mPlatMax) {
        this.mId = mId;
        this.mName = mName;
        this.mShortName = mShortName;
        this.mDescription = mDescription;
        this.mIbuMin = mIbuMin;
        this.mIbuMax = mIbuMax;
        this.mAlcMin = mAlcMin;
        this.mAlcMax = mAlcMax;
        this.mSrmMin = mSrmMin;
        this.mSrmMax = mPlatMax;
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

    public String getSrmMin() {
        return mSrmMin;
    }

    public void setSrmMin(String mPlatMin) {
        this.mSrmMin = mPlatMin;
    }

    public String getSrmMax() {
        return mSrmMax;
    }

    public void setSrmMax(String mPlatMax) {
        this.mSrmMax = mPlatMax;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String mCategoryName) {
        this.mCategoryName = mCategoryName;
    }
}