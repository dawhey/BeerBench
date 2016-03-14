package com.example.dawid.beerbench.StylesList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.dawid.beerbench.Models.Category;
import com.example.dawid.beerbench.Models.Style;
import com.example.dawid.beerbench.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by Dawid on 12.03.2016.
 */
public class StylesListFragment extends Fragment {

    public static ArrayList<Category> mCategories = new ArrayList<>();
    private StylesExpandableAdapter mStylesExpandableAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    private static final String URL = "https://api.brewerydb.com/v2/menu/styles?key=e2ea0d33e8d787362e34af750b66b157&format=json";
    private static final String NO_CONNECTION_ERROR = "Cannot fetch the data, check your internet connection!";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Browse styles");
        downloadStyles(URL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.style_list_fragment, container, false);
        mProgressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mProgressBar.setIndeterminate(true);
        AppCompatActivity a = (AppCompatActivity) getActivity();
        return v;
    }

    public void parseCategoriesJsonData(JSONObject response) {
        try {
            JSONArray data = response.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject record = data.getJSONObject(i);
                int categoryId = record.getInt("categoryId");

                int styleId = record.getInt("id");
                String styleName = record.getString("name");
                Style styleToAdd = new Style(styleId, styleName);

                int exists = checkIfCategoryExists(categoryId);
                if (exists == -1) {
                    String categoryName = record.getJSONObject("category").getString("name");
                    Category categoryToAdd = new Category(categoryId, categoryName);
                    categoryToAdd.styles.add(styleToAdd);
                    mCategories.add(categoryToAdd);
                }
                else {
                    mCategories.get(exists).styles.add(styleToAdd);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSONException", e.toString());
        }
    }

    public int checkIfCategoryExists(int categoryId) {
        for (int i = 0; i < mCategories.size(); i++) {
            if (mCategories.get(i).getId() == categoryId)
                return i; //category exists already, its index is returned
        }
        return -1; //category doesn't exists yet
    }

    private ArrayList<ParentObject> generateCategories(ArrayList<Category> categories) {
        ArrayList<ParentObject> parentObjects = new ArrayList<>();
        for (Category c : categories) {
            parentObjects.add(c);
        }
        return parentObjects;
    }

    private void downloadStyles(String url) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseCategoriesJsonData(response);
                mStylesExpandableAdapter = new StylesExpandableAdapter(getContext(), generateCategories(mCategories));
                mStylesExpandableAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
                mStylesExpandableAdapter.setParentClickableViewAnimationDefaultDuration();
                mStylesExpandableAdapter.setParentAndIconExpandOnClick(true);
                mProgressBar.setVisibility(View.INVISIBLE);
                mRecyclerView.setAdapter(mStylesExpandableAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley error ", error.toString());
                Snackbar.make(getView(), NO_CONNECTION_ERROR, Snackbar.LENGTH_LONG).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

        queue.add(request);
    }
}
