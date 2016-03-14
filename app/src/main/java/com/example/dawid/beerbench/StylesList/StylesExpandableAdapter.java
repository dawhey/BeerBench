package com.example.dawid.beerbench.StylesList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.dawid.beerbench.Models.Category;
import com.example.dawid.beerbench.Models.Style;
import com.example.dawid.beerbench.MyApplication;
import com.example.dawid.beerbench.R;

import java.util.List;

/**
 * Created by Dawid on 12.03.2016.
 */
public class StylesExpandableAdapter extends ExpandableRecyclerAdapter<CategoryParentViewHolder, StyleChildViewHolder> {

    private LayoutInflater mInflater;

    public StylesExpandableAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);

        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CategoryParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.category_item, viewGroup, false);
        return new CategoryParentViewHolder(view);
    }

    @Override
    public StyleChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.style_item, viewGroup, false);
        return new StyleChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(final CategoryParentViewHolder categoryParentViewHolder, int i, Object o) {
        Category category = (Category) o;
        categoryParentViewHolder.mCategoryTextView.setText(category.getName());
    }

    @Override
    public void onBindChildViewHolder(StyleChildViewHolder styleChildViewHolder, int i, Object o) {
        Style style = (Style) o;
        styleChildViewHolder.mStyleTextView.setText(style.getName());
    }
}
