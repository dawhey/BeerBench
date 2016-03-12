package com.example.dawid.beerbench.Models;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawid on 12.03.2016.
 */
public class Category implements ParentObject {

    private int mId;
    private String mName;
    public List<Object> styles;

    public Category(int id, String name) {
        this.mId = id;
        this.mName = name;
        styles = new ArrayList<>();
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

    @Override
    public List<Object> getChildObjectList() {
        return styles;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        styles = list;
    }
}
