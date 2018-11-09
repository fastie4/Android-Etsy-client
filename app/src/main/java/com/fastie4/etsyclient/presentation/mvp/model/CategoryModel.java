package com.fastie4.etsyclient.presentation.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fastie4.etsyclient.domain.Category;
import com.fastie4.etsyclient.domain.mapper.AbstractMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class CategoryModel implements Parcelable {
    private String name;
    private String shortName;

    public CategoryModel() {
    }

    protected CategoryModel(Parcel in) {
        name = in.readString();
        shortName = in.readString();
    }

    public static final Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {
        @Override
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        @Override
        public CategoryModel[] newArray(int size) {
            return new CategoryModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @NonNull
    @Override
    public String toString() {
        return getShortName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(shortName);
    }

    public static class Mapper extends AbstractMapper<CategoryModel, Category> {
        @Inject
        public Mapper() {
        }

        @Override
        public CategoryModel transformR(Category category) {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setName(category.getName());
            categoryModel.setShortName(category.getShortName());
            return categoryModel;
        }

        @Override
        public Category transformL(CategoryModel from) {
            throw new RuntimeException();
        }
    }
}