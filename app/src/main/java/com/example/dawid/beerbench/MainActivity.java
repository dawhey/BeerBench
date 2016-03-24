package com.example.dawid.beerbench;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.example.dawid.beerbench.BeerSearch.BeerSearchFragment;
import com.example.dawid.beerbench.StylesList.StylesListFragment;

public class MainActivity extends AppCompatActivity {

    Menu menu;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView nvDrawer;
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
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
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
        if (details != null) {
            if (!details.isVisible()) {
                moveTaskToBack(true);
            }
        }
        else
            super.onBackPressed();

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
                    mDrawer.closeDrawer(GravityCompat.START);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position
        Fragment fragment = null;

        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = StylesListFragment.class;
                menu.setGroupVisible(R.id.main_menu_group, false);
                break;
            case R.id.nav_second_fragment:
                fragmentClass = BeerSearchFragment.class;
                menu.setGroupVisible(R.id.main_menu_group, true);
                break;
            default:
                fragmentClass = StylesListFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        menu.setGroupVisible(R.id.main_menu_group, false);
        return true;
    }
}
