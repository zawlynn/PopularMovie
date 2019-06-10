package com.zawlynn.udacity.popularmovie.ui.detail.diff;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.zawlynn.udacity.popularmovie.model.Trailer;


public class TrailerItemCallback extends  DiffUtil.ItemCallback<Trailer>{
    public boolean areItemsTheSame(@NonNull Trailer oldItem, @NonNull Trailer newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Trailer oldItem, @NonNull Trailer newItem) {
        return oldItem.equals(newItem);
    }
}
