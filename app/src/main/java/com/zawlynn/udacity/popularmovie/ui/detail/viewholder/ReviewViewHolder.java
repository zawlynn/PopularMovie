package com.zawlynn.udacity.popularmovie.ui.detail.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zawlynn.udacity.popularmovie.R;
import com.zawlynn.udacity.popularmovie.model.Review;
import com.zawlynn.udacity.popularmovie.model.Trailer;
import com.zawlynn.udacity.popularmovie.utils.CommonUtils;
import com.zawlynn.udacity.popularmovie.utils.GlideApp;
import com.zawlynn.udacity.popularmovie.utils.OnTrailerClick;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.content)
    public TextView content;
    @BindView(R.id.author)
    public TextView author;

    public ReviewViewHolder(View itemLayoutView) {
        super(itemLayoutView);
        ButterKnife.bind(this,itemLayoutView);
    }

    public void bind(final Review review) {
        content.setText(review.getContent());
        author.setText(review.getAuthor());
    }
}
