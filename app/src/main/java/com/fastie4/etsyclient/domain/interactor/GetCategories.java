package com.fastie4.etsyclient.domain.interactor;

import com.fastie4.etsyclient.domain.Category;
import com.fastie4.etsyclient.domain.repository.CategoryRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetCategories {
    private final CategoryRepository mCategoryRepository;

    @Inject
    GetCategories(CategoryRepository repository) {
        mCategoryRepository = repository;
    }

    public Observable<List<Category>> execute() {
        return mCategoryRepository.getCategories();
    }
}