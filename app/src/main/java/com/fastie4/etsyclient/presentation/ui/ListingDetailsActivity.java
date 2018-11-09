package com.fastie4.etsyclient.presentation.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.text.HtmlCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastie4.etsyclient.R;
import com.fastie4.etsyclient.presentation.mvp.ListingDetailsContract;
import com.fastie4.etsyclient.presentation.mvp.model.ListingModel;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class ListingDetailsActivity extends AppCompatActivity
        implements ListingDetailsContract.View {
    public static final String EXTRA_LISTING = "com.fastie4.etsyclient.listing";
    @Inject
    ListingDetailsContract.Presenter mPresenter;
    @BindView(R.id.listing_details_image)
    ImageView mImage;
    @BindView(R.id.listing_details_title)
    TextView mTitle;
    @BindView(R.id.listing_details_price)
    TextView mPrice;
    @BindView(R.id.listing_details_description)
    TextView mDescription;
    ListingModel mListingModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_details);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.listing_details_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());

        mListingModel = getIntent().getParcelableExtra(EXTRA_LISTING);

        mTitle.setText(HtmlCompat.fromHtml(mListingModel.getTitle(), HtmlCompat.FROM_HTML_MODE_LEGACY));
        mPrice.setText(getString(R.string.price_currency, mListingModel.getPrice()));
        mDescription.setText(mListingModel.getDescription());
        if (mListingModel.getImageSmallUrl() != null) {
            Picasso.get()
                    .load(mListingModel.getImageFullUrl())
                    .into(mImage);
        }
    }

    @OnClick(R.id.listing_details_favorite)
    void favourite() {
        mListingModel.setFavorite(!mListingModel.isFavorite());
        mPresenter.favourite(mListingModel);
        String message;
        if (mListingModel.isFavorite()) {
            message = getString(R.string.add_to_favorites);
        } else {
            message = getString(R.string.remove_from_favorites);
        }
        Snackbar.make(mDescription, message, Snackbar.LENGTH_SHORT).show();
    }
}