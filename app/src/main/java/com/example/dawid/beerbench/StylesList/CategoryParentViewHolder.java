package com.example.dawid.beerbench.StylesList;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.dawid.beerbench.R;

/**
 * Created by Dawid on 12.03.2016.
 */
public class CategoryParentViewHolder extends ParentViewHolder{

    public TextView mCategoryTextView;

    public CategoryParentViewHolder(View itemView) {
        super(itemView);

        mCategoryTextView = (TextView) itemView.findViewById(R.id.category_name);
    }
}
