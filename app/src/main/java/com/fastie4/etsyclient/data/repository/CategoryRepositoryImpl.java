package com.fastie4.etsyclient.data.repository;

import com.fastie4.etsyclient.data.api.ApiInterface;
import com.fastie4.etsyclient.data.api.model.CategoryEntity;
import com.fastie4.etsyclient.data.api.model.mapper.ApiCategoryEntityMapper;
import com.fastie4.etsyclient.domain.Category;
import com.fastie4.etsyclient.domain.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CategoryRepositoryImpl implements CategoryRepository {

    private final ApiInterface mApi;
    private final ApiCategoryEntityMapper mMapper;

    @Inject
    public CategoryRepositoryImpl(ApiInterface api, ApiCategoryEntityMapper mapper) {
        mApi = api;
        mMapper = mapper;
    }

    @Override
    public Observable<List<Category>> getCategories() {
        return mApi.getCategories().map(categoryResponse ->
                mMapper.transformListL(categoryResponse.getResults()));
    }
}
