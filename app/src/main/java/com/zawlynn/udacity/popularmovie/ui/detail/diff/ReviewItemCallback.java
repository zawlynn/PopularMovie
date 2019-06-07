package com.zawlynn.udacity.popularmovie.ui.detail.diff;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.zawlynn.udacity.popularmovie.model.Review;
import com.zawlynn.udacity.popularmovie.model.Trailer;


public class ReviewItemCallback extends  DiffUtil.ItemCallback<Review>{
    public boolean areItemsTheSame(@NonNull Review oldItem, @NonNull Review newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Review oldItem, @NonNull Review newItem) {
        return oldItem.equals(newItem);
    }
}
