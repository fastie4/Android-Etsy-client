package com.fastie4.etsyclient.data.api.model.mapper;

import com.fastie4.etsyclient.data.api.model.CategoryEntity;
import com.fastie4.etsyclient.domain.Category;
import com.fastie4.etsyclient.domain.mapper.AbstractMapper;

import javax.inject.Inject;

public class ApiCategoryEntityMapper extends AbstractMapper<CategoryEntity, Category> {
    @Inject
    public ApiCategoryEntityMapper() {}
    @Override
    public CategoryEntity transformR(Category from) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(from.getName());
        categoryEntity.setShortName(from.getShortName());
        return null;
    }

    @Override
    public Category transformL(CategoryEntity from) {
        Category category = new Category();
        category.setName(from.getName());
        category.setShortName(from.getShortName());
        return category;
    }
}
