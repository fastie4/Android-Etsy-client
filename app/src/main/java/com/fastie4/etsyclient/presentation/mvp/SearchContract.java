package com.fastie4.etsyclient.presentation.mvp;

import com.fastie4.etsyclient.presentation.mvp.model.CategoryModel;

import java.util.List;

public class SearchContract {
    public interface Presenter {
        void search(String query);
        void loadCategories();
    }
    public interface View {
        void showCategories(List<CategoryModel> categories);
        void showProgress();
        void hideProgress();
    }
}
