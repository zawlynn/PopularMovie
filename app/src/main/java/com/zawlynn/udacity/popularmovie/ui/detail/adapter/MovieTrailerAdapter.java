package com.zawlynn.udacity.popularmovie.ui.detail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.zawlynn.udacity.popularmovie.R;
import com.zawlynn.udacity.popularmovie.model.Trailer;
import com.zawlynn.udacity.popularmovie.ui.detail.viewholder.TrailerViewHolder;
import com.zawlynn.udacity.popularmovie.ui.detail.diff.TrailerItemCallback;
import com.zawlynn.udacity.popularmovie.utils.OnTrailerClick;

public class MovieTrailerAdapter extends ListAdapter<Trailer, TrailerViewHolder> {
    private OnTrailerClick click_event;
    public MovieTrailerAdapter(OnTrailerClick itemClick) {
        super(new TrailerItemCallback());
        click_event=itemClick;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLayoutView =LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_trailer, parent, false);
        return new TrailerViewHolder(itemLayoutView,click_event);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrailerViewHolder holder, int i) {
        holder.bind(getItem(i));
    }

}
