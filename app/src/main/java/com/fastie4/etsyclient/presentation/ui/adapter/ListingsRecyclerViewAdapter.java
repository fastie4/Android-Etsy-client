package com.fastie4.etsyclient.presentation.ui.adapter;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastie4.etsyclient.R;
import com.fastie4.etsyclient.presentation.mvp.model.ListingModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ListingsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_LISTING = 0;
    private static final int VIEW_TYPE_LOADING = 1;
    private final List<ListingModel> mItems;
    private final OnListFragmentInteractionListener mListener;

    @Inject
    public ListingsRecyclerViewAdapter(OnListFragmentInteractionListener listener) {
        mItems = new ArrayList<>();
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LISTING) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_listing, parent, false);
            return new ListingViewHolder(view);
        } else {
            return new LoadingViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_listing_loading, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListingViewHolder) {
            ((ListingViewHolder) holder).onBind(mItems.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position) == null? VIEW_TYPE_LOADING : VIEW_TYPE_LISTING;
    }

    public void addLoading() {
        mItems.add(null);
        notifyItemInserted(mItems.size() - 1);
    }

    public void removeLoading() {
        if (mItems.size() > 0 && mItems.get(mItems.size() - 1) == null) {
            mItems.remove(mItems.size() - 1);
            notifyItemRemoved(mItems.size());
        }
    }

    public void refreshItem(ListingModel item) {
        int id = mItems.indexOf(item);
        if (id != -1) {
            mItems.set(id, item);
            notifyItemChanged(id);
        }
    }

    public void addItems(List<ListingModel> items) {
        mItems.addAll(items);
    }

    public void setItems(List<ListingModel> items) {
        mItems.clear();
        addItems(items);
    }

    public List<ListingModel> getItems() {
        return mItems;
    }

    class ListingViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final ImageView mImage;
        final TextView mTitle;
        ListingModel mItem;

        ListingViewHolder(View view) {
            super(view);
            mView = view;
            mImage = view.findViewById(R.id.listing_image);
            mTitle = view.findViewById(R.id.listing_title);
        }

        void onBind(ListingModel listingModel) {
            mItem = listingModel;
            if (mItem.getImageSmallUrl() == null) {
                if (null != mListener) {
                    mListener.loadImageUrl(mItem);
                }
                mImage.setImageDrawable(null);
            } else {
                Picasso.get()
                        .load(mItem.getImageSmallUrl())
                        .into(mImage);
            }
            mTitle.setText(HtmlCompat.fromHtml(mItem.getTitle(),
                    HtmlCompat.FROM_HTML_MODE_LEGACY));
            mView.setOnClickListener(v -> {
                if (null != mListener) {
                    mListener.onClick(mItem);
                }
            });
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnListFragmentInteractionListener {
        void onClick(ListingModel item);
        void loadFromOffset(int offset);
        void loadImageUrl(ListingModel item);
    }
}
