package com.fastie4.etsyclient.presentation.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.fastie4.etsyclient.R;
import com.fastie4.etsyclient.presentation.mvp.model.CategoryModel;
import com.fastie4.etsyclient.presentation.mvp.SearchContract;
import com.fastie4.etsyclient.presentation.ui.SearchResultsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchFragment extends Fragment implements SearchContract.View {
    private static final String SAVED_CATEGORIES = "categories";
    private Listener mListener;
    @Inject
    Context mContext;
    @Inject
    SearchContract.Presenter mPresenter;
    @Inject
    ArrayAdapter<CategoryModel> mAdapter;

    @BindView(R.id.search_query)
    EditText mSearchQuery;
    @BindView(R.id.category_spinner)
    Spinner mSpinner;
    @BindView(R.id.search_progress)
    ProgressBar mProgressBar;
    @BindView(R.id.search_group)
    View mSearchGroup;

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        mSpinner.setAdapter(mAdapter);

        if (savedInstanceState != null) {
            ArrayList<CategoryModel> categoryModels =
                    savedInstanceState.getParcelableArrayList(SAVED_CATEGORIES);
            if (categoryModels != null) {
                mAdapter.addAll(categoryModels);
                return view;
            }
        }
        mSearchGroup.setVisibility(View.GONE);
        mPresenter.loadCategories();
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        int count = mAdapter.getCount();
        if (count > 0) {
            ArrayList<CategoryModel> categoryModels = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                categoryModels.add(mAdapter.getItem(i));
            }
            outState.putParcelableArrayList(SAVED_CATEGORIES, categoryModels);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        mListener = (Listener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick(R.id.search_button)
    void startSearchActivity() {
        String category = mAdapter.getItem(mSpinner.getSelectedItemPosition()).getName();
        Intent intent = new Intent();
        intent.putExtra(SearchResultsActivity.EXTRA_CATEGORY, category);
        intent.putExtra(SearchResultsActivity.EXTRA_QUERY, mSearchQuery.getText().toString());
        mListener.startSearchResultsActivity(intent);
    }

    @Override
    public void showCategories(List<CategoryModel> categories) {
        mAdapter.clear();
        mAdapter.addAll(categories);
        mAdapter.notifyDataSetChanged();
        mSearchGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    public interface Listener {
        void startSearchResultsActivity(Intent intent);
    }
}
