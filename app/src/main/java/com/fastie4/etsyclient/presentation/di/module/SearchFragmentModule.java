package com.fastie4.etsyclient.presentation.di.module;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.fastie4.etsyclient.R;
import com.fastie4.etsyclient.presentation.mvp.model.CategoryModel;
import com.fastie4.etsyclient.presentation.mvp.SearchContract;
import com.fastie4.etsyclient.presentation.mvp.presenter.SearchPresenterImpl;
import com.fastie4.etsyclient.presentation.ui.fragment.SearchFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchFragmentModule {
    @Provides
    Context context(SearchFragment fragment) {
        return fragment.getActivity();
    }

    @Provides
    ArrayAdapter<CategoryModel> adapter(Context context) {
        return new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item);
    }

    @Provides
    SearchContract.Presenter presenter(SearchPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    SearchContract.View view(SearchFragment fragment) {
        return fragment;
    }
}
