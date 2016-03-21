package com.example.dawid.beerbench.BeerSearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dawid.beerbench.R;

/**
 * Created by Dawid on 21.03.2016.
 */
public class BeerSearchFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.beer_search, container, false);

        return v;
    }
}
