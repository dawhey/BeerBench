package com.example.dawid.beerbench;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dawid.beerbench.StylesList.StylesListFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment mStylesListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mStylesListFragment = new StylesListFragment();
            fragmentTransaction.add(R.id.container, mStylesListFragment);
            fragmentTransaction.commit();
        }
        else {
            mStylesListFragment = (StylesListFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        }
    }
}
