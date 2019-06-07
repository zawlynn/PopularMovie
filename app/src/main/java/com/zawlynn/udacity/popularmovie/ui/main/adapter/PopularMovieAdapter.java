package com.zawlynn.udacity.popularmovie.ui.main.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zawlynn.udacity.popularmovie.R;
import com.zawlynn.udacity.popularmovie.model.Movie;
import com.zawlynn.udacity.popularmovie.ui.main.diff.MovieItemCallback;
import com.zawlynn.udacity.popularmovie.ui.main.viewholder.PopuarViewHolder;
import com.zawlynn.udacity.popularmovie.utils.OnItemClick;

public class PopularMovieAdapter extends ListAdapter<Movie, PopuarViewHolder> {
    private OnItemClick click_event;
    public PopularMovieAdapter(OnItemClick itemClick) {
        super(new MovieItemCallback());
        click_event=itemClick;
    }

    @NonNull
    @Override
    public PopuarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLayoutView =LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new PopuarViewHolder(itemLayoutView,click_event);
    }

    @Override
    public void onBindViewHolder(@NonNull final PopuarViewHolder holder, int i) {
        holder.bind(getItem(i));
    }

}
