package com.example.dawid.beerbench.BeerSearch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dawid.beerbench.Models.Beer;
import com.example.dawid.beerbench.MyApplication;
import com.example.dawid.beerbench.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Dawid on 23.03.2016.
 */
public class BeerSearchAdapter extends RecyclerView.Adapter<BeerSearchAdapter.ViewHolder> {
    private ArrayList<Beer> mDataset;
    private static RecyclerViewClickListener itemListener;


    public BeerSearchAdapter(ArrayList<Beer> myDataset, RecyclerViewClickListener listener) {
        mDataset = myDataset;
        itemListener = listener;
    }

    @Override
    public BeerSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mNameView.setText(mDataset.get(position).getNameDisplay());
        holder.mStyleNameView.setText(mDataset.get(position).getStyleName());
        if (mDataset.get(position).getLabelSmall() != null)
            Picasso.with(MyApplication.getContext()).load(mDataset.get(position).getLabelSmall()).into(holder.mIconView);
        else
            holder.mIconView.setImageResource(R.drawable.nolabel);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView mNameView;
        public TextView mStyleNameView;
        public ImageView mIconView;

        public ViewHolder(View v) {
            super(v);
            mNameView = (TextView) v.findViewById(R.id.name_text);
            mStyleNameView = (TextView) v.findViewById(R.id.style_text);
            mIconView = (ImageView) v.findViewById(R.id.iconview);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition());

        }
    }

    public interface RecyclerViewClickListener
    {

        public void recyclerViewListClicked(View v, int position);
    }
}