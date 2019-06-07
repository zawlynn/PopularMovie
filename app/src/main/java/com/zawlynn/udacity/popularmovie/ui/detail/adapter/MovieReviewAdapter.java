package com.zawlynn.udacity.popularmovie.ui.detail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.zawlynn.udacity.popularmovie.R;
import com.zawlynn.udacity.popularmovie.model.Review;
import com.zawlynn.udacity.popularmovie.model.Trailer;
import com.zawlynn.udacity.popularmovie.ui.detail.diff.ReviewItemCallback;
import com.zawlynn.udacity.popularmovie.ui.detail.diff.TrailerItemCallback;
import com.zawlynn.udacity.popularmovie.ui.detail.viewholder.ReviewViewHolder;
import com.zawlynn.udacity.popularmovie.ui.detail.viewholder.TrailerViewHolder;
import com.zawlynn.udacity.popularmovie.utils.OnTrailerClick;

public class MovieReviewAdapter extends ListAdapter<Review, ReviewViewHolder> {
    public MovieReviewAdapter() {
        super(new ReviewItemCallback());
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLayoutView =LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReviewViewHolder holder, int i) {
        holder.bind(getItem(i));
    }

}
