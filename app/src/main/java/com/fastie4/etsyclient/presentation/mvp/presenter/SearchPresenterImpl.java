package com.fastie4.etsyclient.presentation.mvp.presenter;

import com.fastie4.etsyclient.domain.interactor.GetCategories;
import com.fastie4.etsyclient.presentation.mvp.SearchContract;
import com.fastie4.etsyclient.presentation.mvp.model.CategoryModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenterImpl implements SearchContract.Presenter {
    private final SearchContract.View mView;
    @Inject
    CategoryModel.Mapper mMapper;
    private final GetCategories mGetCategories;

    @Inject
    public SearchPresenterImpl(SearchContract.View view, GetCategories getCategories) {
        mView = view;
        mGetCategories = getCategories;
    }

    @Override
    public void search(String query) {

    }

    @Override
    public void loadCategories() {
        mView.showProgress();
        mGetCategories.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> {
                    mView.hideProgress();
                    mView.showCategories(mMapper.transformListR(categories));
                });
    }
}
