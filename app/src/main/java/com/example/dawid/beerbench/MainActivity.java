package com.example.dawid.beerbench;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.example.dawid.beerbench.StylesList.StylesListFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private MyApplication mApplication;
    private Fragment mStylesListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawer.setDrawerListener(mDrawerToggle);
        mApplication = (MyApplication)this.getApplicationContext();

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mStylesListFragment = new StylesListFragment();
            fragmentTransaction.add(R.id.container, mStylesListFragment, "stylesList");
            fragmentTransaction.commit();
        }
        else {
            mStylesListFragment = getSupportFragmentManager().findFragmentById(R.id.container);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mApplication.setCurrentActivity(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        VolleySingleton.getInstance().getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }

    @Override
    protected void onPause() {
        clearReferences();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Fragment details = getSupportFragmentManager().findFragmentByTag("detailsFragment");
        if (!details.isVisible())
        {
            moveTaskToBack(true);
        }
    }

    private void clearReferences(){
        Activity currActivity = mApplication.getCurrentActivity();
        if (this.equals(currActivity))
            mApplication.setCurrentActivity(null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                if (!mDrawer.isDrawerOpen(GravityCompat.START))
                    mDrawer.openDrawer(GravityCompat.START);
                else
                    mDrawer.closeDrawer(Gravity.LEFT);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


}
