package com.example.dawid.beerbench.StylesList;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dawid.beerbench.Models.Category;
import com.example.dawid.beerbench.Models.Style;
import com.example.dawid.beerbench.R;
import com.example.dawid.beerbench.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by Dawid on 13.03.2016.
 */
public class StyleDetailsFragment extends Fragment {

    private Style mDownloadedStyle;
    private int mStyleId;

    private String URL;
    private static final String NO_CONNECTION_ERROR = "Cannot fetch the data, check your internet connection!";
    private static final String UNAVAILABLE = "Unavailable";

    private String mStyleName;

    private RelativeLayout mDataLayout;
    private ProgressBar mProgressBar;
    private TextView mNameTextView;
    private TextView mShortNameTextView;
    private TextView mDescTextView;
    private TextView mAlcTextView;
    private TextView mIbuTextView;
    private ImageView mSrmView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStyleId = getArguments().getInt("id");
        mStyleName = getArguments().getString("name");
        URL = "https://api.brewerydb.com/v2/style/" + mStyleId + "?key=e2ea0d33e8d787362e34af750b66b157&format=json";
        mDownloadedStyle = new Style(mStyleId, mStyleName);
        AppCompatActivity a = (AppCompatActivity) getActivity();
        a.setTitle("Style details");
        downloadStyles(URL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.style_details, container, false);

        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, new StylesListFragment(), "stylesList");
                    ft.commit();
                    return true;
                }
                return false;
            }
        });

        mDataLayout = (RelativeLayout) v.findViewById(R.id.data_layout);
        mDataLayout.setVisibility(View.INVISIBLE);

        mProgressBar = (ProgressBar) v.findViewById(R.id.progressBar2);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisibility(View.VISIBLE);
        mNameTextView = (TextView) v.findViewById(R.id.name_text_view);
        mShortNameTextView = (TextView) v.findViewById(R.id.shortname_text_view);
        mDescTextView = (TextView) v.findViewById(R.id.desc_text_view);
        mIbuTextView = (TextView) v.findViewById(R.id.ibu_text_view);
        mAlcTextView = (TextView) v.findViewById(R.id.alc_text_view);
        mSrmView = (ImageView) v.findViewById(R.id.srm_view);
        mNameTextView.setText(mStyleName);
        return v;
    }

    private void downloadStyles(String url) {
        RequestQueue queue = VolleySingleton.getInstance().getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseStyleJsonData(response);

                String desc;
                String shortName;
                String ibuRange;
                String alcRange;
                if (mDownloadedStyle.getIbuMin() == null || mDownloadedStyle.getIbuMax() == null)
                    ibuRange = UNAVAILABLE;
                else
                    ibuRange = mDownloadedStyle.getIbuMin() + " - " + mDownloadedStyle.getIbuMax();

                if (mDownloadedStyle.getAlcMin() == null || mDownloadedStyle.getAlcMax() == null)
                    alcRange = UNAVAILABLE;
                else
                    alcRange = mDownloadedStyle.getAlcMin() + "% - " + mDownloadedStyle.getAlcMax()+ "%";

                if (mDownloadedStyle.getDescription() == null)
                    desc = UNAVAILABLE;
                else
                    desc = mDownloadedStyle.getDescription();

                if (mDownloadedStyle.getShortName()  == null)
                    shortName = UNAVAILABLE;
                else
                    shortName = mDownloadedStyle.getShortName();

                if (mDownloadedStyle.getSrmMin() == null || mDownloadedStyle.getSrmMax() == null)
                    mSrmView.setBackgroundDrawable(getResources().getDrawable(R.drawable.unavailable));
                else {
                    ShapeDrawable mDrawable = new ShapeDrawable(new RectShape());
                    int h = mSrmView.getHeight();
                    int w = mSrmView.getWidth();
                    int startColor = Color.parseColor(Style.SRM_TABLE[Integer.parseInt(mDownloadedStyle.getSrmMin())]);
                    int endColor = Color.parseColor(Style.SRM_TABLE[Integer.parseInt(mDownloadedStyle.getSrmMax())]);
                    mDrawable.getPaint().setShader(new LinearGradient(0, h/2, w, h/2, startColor, endColor, Shader.TileMode.REPEAT));
                    mSrmView.setBackgroundDrawable(mDrawable);
                }

                mShortNameTextView.setText(shortName);
                mDescTextView.setText(desc);
                mIbuTextView.setText(ibuRange);
                mAlcTextView.setText(alcRange);

                mProgressBar.setVisibility(View.INVISIBLE);
                mDataLayout.setVisibility(View.VISIBLE);
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

    public void parseStyleJsonData(JSONObject response) {
        try {
            JSONObject data = response.getJSONObject("data");
            JSONObject categoryInfo = data.getJSONObject("category");
            mDownloadedStyle.setCategoryName(categoryInfo.getString("name"));
            mDownloadedStyle.setShortName(data.getString("shortName"));
            mDownloadedStyle.setDescription(data.getString("description"));
            mDownloadedStyle.setIbuMin(data.getString("ibuMin"));
            mDownloadedStyle.setIbuMax(data.getString("ibuMax"));
            mDownloadedStyle.setAlcMin(data.getString("abvMin"));
            mDownloadedStyle.setAlcMax(data.getString("abvMax"));
            mDownloadedStyle.setSrmMin(data.getString("srmMin"));
            mDownloadedStyle.setSrmMax(data.getString("srmMax"));
        } catch (JSONException e) {
            Log.e("JSONException", e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null)
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
