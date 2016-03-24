package com.example.dawid.beerbench.BeerSearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dawid.beerbench.Models.Beer;
import com.example.dawid.beerbench.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Dawid on 23.03.2016.
 */
public class BeerDetailsFragment extends Fragment {


    private TextView mNameTextView;
    private TextView mBrewedTextView;
    private TextView mDescTextView;
    private TextView mStyleNameTextView;
    private TextView mAlcTextView;
    private TextView mIbuTextView;
    private ImageView mIconView;
    private ProgressBar mProgressBar;

    Beer mBeer;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = getArguments().getInt("position");
        mBeer = BeerSearchFragment.mQueryList.get(position);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.beer_details, container, false);
        mNameTextView = (TextView) v.findViewById(R.id.name_view);
        mBrewedTextView = (TextView) v.findViewById(R.id.brewed_view);
        mDescTextView = (TextView) v.findViewById(R.id.desc_view);
        mStyleNameTextView = (TextView) v.findViewById(R.id.style_view);
        mAlcTextView = (TextView) v.findViewById(R.id.alc_view);
        mIbuTextView = (TextView) v.findViewById(R.id.ibu_view);
        mIconView = (ImageView) v.findViewById(R.id.logo_view);
        mProgressBar = (ProgressBar) v.findViewById(R.id.progressBar4);


        mProgressBar.setVisibility(mBeer.getLabelMedium() != null ? View.VISIBLE : View.INVISIBLE);
        mNameTextView.setText(mBeer.getName());
        mBrewedTextView.setText(mBeer.getBreweryName());
        mDescTextView.setText(mBeer.getDescription());
        mStyleNameTextView.setText(mBeer.getStyleName());
        mAlcTextView.setText(mBeer.getAbv());
        mIbuTextView.setText(mBeer.getIbu());

        if (mBeer.getLabelMedium() != null)
            Picasso.with(getContext()).load(mBeer.getLabelMedium()).into(mIconView, new Callback() {
                @Override
                public void onSuccess() {
                    mProgressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onError() {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    Snackbar.make(getView(), "Error! Image could not be loaded.", Snackbar.LENGTH_LONG).show();
                }
            });
        else
            mIconView.setImageResource(R.drawable.nolabellarge);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.setGroupVisible(R.id.main_menu_group, false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDetach() {
        getActivity().getSupportFragmentManager().popBackStackImmediate();
        super.onDetach();
    }
}
