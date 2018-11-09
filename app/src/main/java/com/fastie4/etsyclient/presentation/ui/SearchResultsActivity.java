package com.fastie4.etsyclient.presentation.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.fastie4.etsyclient.R;
import com.fastie4.etsyclient.presentation.ui.fragment.SearchListingsFragment;

public class SearchResultsActivity extends AppCompatActivity {
    public static final String EXTRA_CATEGORY = "com.fastie4.etsyclient.category";
    public static final String EXTRA_QUERY = "com.fastie4.etsyclient.query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        String query = getIntent().getStringExtra(EXTRA_QUERY);
        String category = getIntent().getStringExtra(EXTRA_CATEGORY);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.search_results_container, SearchListingsFragment.newInstance(query, category))
                    .commit();
        }
    }
}
