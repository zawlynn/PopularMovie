package com.zawlynn.udacity.popularmovie.ui.main.viewholder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zawlynn.udacity.popularmovie.R;
import com.zawlynn.udacity.popularmovie.constants.Constants;
import com.zawlynn.udacity.popularmovie.model.Movie;
import com.zawlynn.udacity.popularmovie.utils.CommonUtils;
import com.zawlynn.udacity.popularmovie.utils.GlideApp;
import com.zawlynn.udacity.popularmovie.utils.OnItemClick;

public class PopuarViewHolder extends RecyclerView.ViewHolder {
    private ImageView item_poster_post;
    private TextView item_poster_title;
    private ProgressBar progress;
    private Context context;
    OnItemClick itemClick;
    public PopuarViewHolder(View itemLayoutView, final OnItemClick click) {
        super(itemLayoutView);
        context=itemLayoutView.getContext();
        item_poster_title=itemLayoutView.findViewById(R.id.item_poster_title);
        item_poster_post=itemLayoutView.findViewById(R.id.item_poster_post);
        progress=itemLayoutView.findViewById(R.id.progress);
        itemClick=click;
    }

    public void bind(final Movie movie) {
        item_poster_title.setText(movie.getTitle());
        String url = CommonUtils.getInstance().getMoviePoster(movie.getPoster_path());
        GlideApp.with(context)
                .load(url).override(300, 300)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progress.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progress.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(item_poster_post);

        item_poster_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onClick(movie);
            }
        });
    }
}
