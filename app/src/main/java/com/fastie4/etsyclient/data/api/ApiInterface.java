package com.fastie4.etsyclient.data.api;

import com.fastie4.etsyclient.data.api.model.CategoryResponse;
import com.fastie4.etsyclient.data.api.model.ListingImagesResponse;
import com.fastie4.etsyclient.data.api.model.ListingsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("v2/taxonomy/categories")
    Observable<CategoryResponse> getCategories();

    @GET("v2/listings/active")
    Observable<ListingsResponse> getActiveListings(@Query("category") String category,
                                                   @Query("keywords") String keywords,
                                                   @Query("limit") int limit,
                                                   @Query("offset") int offset);

    @GET("v2/listings/{listing_id}/images")
    Observable<ListingImagesResponse> getListingImages(@Path("listing_id") int listingId);
}