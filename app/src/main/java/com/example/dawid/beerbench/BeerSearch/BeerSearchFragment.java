package com.example.dawid.beerbench.BeerSearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dawid.beerbench.Models.Beer;
import com.example.dawid.beerbench.R;
import com.example.dawid.beerbench.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Dawid on 21.03.2016.
 */
public class BeerSearchFragment extends Fragment{

    private ArrayList<Beer> mQueryList = new ArrayList<>();
    private ProgressBar mProgressBar;
    private TextView mSearchText;
    private ImageView mSearchBottle;
    private RecyclerView mRecyclerView;

    private static final String NO_CONNECTION_ERROR = "Cannot fetch the data, check your internet connection!";
    private static final String UNAVAILABLE = "Unavailable";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.beer_search, container, false);
        mSearchBottle = (ImageView) v.findViewById(R.id.search_bottle);
        mSearchText = (TextView) v.findViewById(R.id.searchText);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mProgressBar = (ProgressBar) v.findViewById(R.id.progressBar3);
        mProgressBar.setVisibility(View.INVISIBLE);
        mProgressBar.setIndeterminate(true);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.setGroupVisible(R.id.main_menu_group, true);

        MenuItem item = menu.findItem(R.id.search);
        SearchView mSearchView = (SearchView) item.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    mQueryList.clear();
                    String URL = null;
                    try {
                        URL = "https://api.brewerydb.com/v2/search?q=" + URLEncoder.encode(query, "UTF-8") + "&type=beer&key=e2ea0d33e8d787362e34af750b66b157&format=json";
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        Log.e("URLENCODER", e.toString());
                    }
                    mSearchBottle.setVisibility(View.INVISIBLE);
                    mSearchText.setVisibility(View.INVISIBLE);
                    mProgressBar.setVisibility(View.VISIBLE);
                    downloadBeers(URL);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void downloadBeers(String url) {
        RequestQueue queue = VolleySingleton.getInstance().getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseBeerJsonData(response);
                mProgressBar.setVisibility(View.INVISIBLE);
                BeerSearchAdapter adapter = new BeerSearchAdapter(mQueryList);
                mRecyclerView.setVisibility(View.VISIBLE);
                mRecyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley error ", error.toString());
                Snackbar.make(getView(), NO_CONNECTION_ERROR, Snackbar.LENGTH_LONG).show();
                mRecyclerView.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
                mSearchBottle.setVisibility(View.VISIBLE);
                mSearchText.setVisibility(View.VISIBLE);
            }
        });
        queue.add(request);
    }

    private void parseBeerJsonData(JSONObject response) {
        try {
            JSONArray data = response.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject record = data.getJSONObject(i);
                Beer beer = new Beer();

                beer.setId(record.getString("id"));
                beer.setName(record.has("name") ? record.getString("name") : UNAVAILABLE);
                beer.setNameDisplay(record.has("nameDisplay") ? record.getString("nameDisplay") : UNAVAILABLE);
                beer.setDescription(record.has("description") ? record.getString("description") : UNAVAILABLE);
                beer.setIbu(record.has("ibu") ? record.getString("ibu") : UNAVAILABLE);
                beer.setAbv(record.has("abv") ? record.getString("abv") : UNAVAILABLE);

                if (record.has("labels")) {
                    JSONObject labels = record.getJSONObject("labels");
                    beer.setLabelSmall(labels.has("icon") ? labels.getString("icon") : UNAVAILABLE);
                    beer.setLabelMedium(labels.has("medium") ? labels.getString("medium") : UNAVAILABLE);
                }

                if (record.has("style")) {
                    JSONObject style = record.getJSONObject("style");
                    beer.setStyleName(style.has("name") ? style.getString("name") : UNAVAILABLE);
                }

                mQueryList.add(beer);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSONException", e.toString());
            Snackbar.make(getView(), "No results found.", Snackbar.LENGTH_LONG).show();
            mSearchBottle.setVisibility(View.VISIBLE);
            mSearchText.setVisibility(View.VISIBLE);
        }
    }
}
