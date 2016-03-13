package com.example.dawid.beerbench.StylesList;

import android.app.FragmentManager;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.dawid.beerbench.Models.Category;
import com.example.dawid.beerbench.Models.Style;
import com.example.dawid.beerbench.MyApplication;
import com.example.dawid.beerbench.R;

/**
 * Created by Dawid on 12.03.2016.
 */
public class StyleChildViewHolder extends ChildViewHolder{

    public TextView mStyleTextView;

    public StyleChildViewHolder(final View itemView) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Clicked ", mStyleTextView.getText().toString());
                Snackbar.make(v, mStyleTextView.getText().toString(), Snackbar.LENGTH_LONG)
                        .setAction("Show", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int styleId = findStylebyName(mStyleTextView.getText().toString());
                            }
                        })
                        .setActionTextColor(MyApplication.getContext().getResources().getColor(R.color.colorAccent))
                        .show();
            }
        });
        mStyleTextView = (TextView) itemView.findViewById(R.id.style_name);
    }

    private int findStylebyName(String name) {
        for (int i = 0; i < StylesListFragment.mCategories.size(); i++) {
            for (int j = 0; j < StylesListFragment.mCategories.get(i).styles.size(); j++) {
                Object o = StylesListFragment.mCategories.get(i).styles.get(j);
                Style s = (Style) o;
                if (s.getName().compareTo(name) == 0) {
                    return s.getId();
                }
            }
        }
        return -1;
    }
}
