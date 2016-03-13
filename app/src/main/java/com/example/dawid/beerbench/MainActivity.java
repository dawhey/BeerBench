package com.example.dawid.beerbench;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dawid.beerbench.StylesList.StylesListFragment;

public class MainActivity extends AppCompatActivity {

    private MyApplication mApplication;
    private Fragment mStylesListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApplication = (MyApplication)this.getApplicationContext();

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mStylesListFragment = new StylesListFragment();
            fragmentTransaction.add(R.id.container, mStylesListFragment, "stylesList");
            fragmentTransaction.commit();
        }
        else {
            mStylesListFragment = (StylesListFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mApplication.setCurrentActivity(this);
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
        StylesListFragment myFragment = (StylesListFragment)getSupportFragmentManager().findFragmentByTag("stylesList");
        if (myFragment != null && myFragment.isVisible()) {
            super.onBackPressed();
        }
    }

    private void clearReferences(){
        Activity currActivity = mApplication.getCurrentActivity();
        if (this.equals(currActivity))
            mApplication.setCurrentActivity(null);
    }
}
