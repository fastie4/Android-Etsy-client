package com.fastie4.etsyclient.presentation.ui;

import com.fastie4.etsyclient.R;
import com.fastie4.etsyclient.presentation.ui.fragment.SavedListingsFragment;
import com.fastie4.etsyclient.presentation.ui.fragment.SearchListingsFragment;
import com.fastie4.etsyclient.presentation.ui.fragment.base.ListingsFragment;
import com.fastie4.etsyclient.presentation.ui.fragment.SearchFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import dagger.android.AndroidInjection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements SearchFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    @Override
    public void startSearchResultsActivity(Intent intent) {
        intent.setClass(this, SearchResultsActivity.class);
        startActivity(intent);
    }

    public class PagerAdapter extends FragmentPagerAdapter {

        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new SearchFragment();
            }
            return new SavedListingsFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
