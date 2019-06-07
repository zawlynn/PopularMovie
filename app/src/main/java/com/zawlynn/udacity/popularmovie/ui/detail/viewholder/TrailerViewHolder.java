package com.zawlynn.udacity.popularmovie.ui.detail.viewholder;

import android.content.Context;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zawlynn.udacity.popularmovie.R;

import com.zawlynn.udacity.popularmovie.model.Trailer;

import com.zawlynn.udacity.popularmovie.utils.CommonUtils;
import com.zawlynn.udacity.popularmovie.utils.GlideApp;
import com.zawlynn.udacity.popularmovie.utils.OnItemClick;
import com.zawlynn.udacity.popularmovie.utils.OnTrailerClick;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailerViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.img_thumb)
    public ImageView img_thumb;
    @BindView(R.id.img_play)
    public ImageView img_play;
    private Context context;
    OnTrailerClick itemClick;
    public TrailerViewHolder(View itemLayoutView, final OnTrailerClick click) {
        super(itemLayoutView);
        ButterKnife.bind(this,itemLayoutView);
        context=itemLayoutView.getContext();
        itemClick=click;

    }

    public void bind(final Trailer movie) {
        String url = CommonUtils.getInstance().getMovieThumb(movie.getKey());
        GlideApp.with(context)
                .load(url).override(300, 300)
                .into(img_thumb);

        img_thumb.setOnClickListener(v -> itemClick.onClick(movie));
    }
}
