package com.fastie4.etsyclient.domain.repository;

import com.fastie4.etsyclient.domain.Category;

import java.util.List;

import io.reactivex.Observable;

public interface CategoryRepository {
    Observable<List<Category>> getCategories();
}
