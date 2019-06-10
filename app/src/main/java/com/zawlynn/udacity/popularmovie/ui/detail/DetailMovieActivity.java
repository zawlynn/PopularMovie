package com.zawlynn.udacity.popularmovie.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zawlynn.udacity.popularmovie.R;
import com.zawlynn.udacity.popularmovie.constants.Constants;
import com.zawlynn.udacity.popularmovie.data.database.entity.Movie;
import com.zawlynn.udacity.popularmovie.model.Trailer;
import com.zawlynn.udacity.popularmovie.ui.detail.adapter.MovieReviewAdapter;
import com.zawlynn.udacity.popularmovie.ui.detail.adapter.MovieTrailerAdapter;
import com.zawlynn.udacity.popularmovie.ui.detail.viewmodel.ActivityDetailViewmodel;
import com.zawlynn.udacity.popularmovie.utils.CommonUtils;
import com.zawlynn.udacity.popularmovie.utils.GlideApp;
import com.zawlynn.udacity.popularmovie.utils.OnTrailerClick;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity implements OnTrailerClick, View.OnClickListener {
    private static final String TAG = "DetailMovieActivity";
    @BindView(R.id.tv_release_date)
    TextView tv_release_date;
    @BindView(R.id.tv_rating)
    TextView tv_rating;
    @BindView(R.id.tv_overview)
    TextView tv_overview;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.movie_detail_poster)
    ImageView movie_detail_poster;
    @BindView(R.id.recTrailer)
    RecyclerView recTrailer;
    @BindView(R.id.recReview)
    RecyclerView recReview;
    @BindView(R.id.img_fav)
    ImageView img_fav;
    ActivityDetailViewmodel viewmodel;
    MovieTrailerAdapter adapter;
    MovieReviewAdapter review_adapter;
    boolean isFavourite = false;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        movie = Objects.requireNonNull(getIntent()
                .getExtras()).getParcelable(Constants.DATA);
        viewmodel = ViewModelProviders.of(this).get(ActivityDetailViewmodel.class);
        viewmodel.get_trailers().observe(this, trailers -> {
            if (trailers != null) {
                adapter.submitList(trailers);
            }
        });
        viewmodel.get_reviews().observe(this, reviews -> {
            if (reviews != null) {
                review_adapter.submitList(reviews);
            }
        });

        initUI(movie);
        viewmodel.getVideoLists(getApplicationContext(), movie.getId());
        viewmodel.getReviewLists(getApplicationContext(), movie.getId());
        img_fav.setOnClickListener(this);
    }

    private void initUI(Movie movie) {
        String url = CommonUtils.getInstance()
                .getMoviePoster(movie.getPoster_path());
        GlideApp.with(DetailMovieActivity.this)
                .load(url)
                .into(movie_detail_poster);
        adapter = new MovieTrailerAdapter(this);
        review_adapter = new MovieReviewAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false);
        recTrailer.setLayoutManager(layoutManager);
        recTrailer.setAdapter(adapter);

        LinearLayoutManager layoutManagerReview = new LinearLayoutManager(getApplicationContext());
        recReview.setLayoutManager(layoutManagerReview);
        recReview.setAdapter(review_adapter);

        tv_release_date.setText(movie.getRelease_date());
        tv_overview.setText(movie.getOverview());
        tv_rating.setText(movie.getVote_average() + "/10");
        tv_title.setText(movie.getTitle());

        if (movie.isFavourite()) {
            img_fav.setImageDrawable(getResources().getDrawable(R.mipmap.ic_favourite));
            isFavourite = true;
        } else {
            img_fav.setImageDrawable(getResources().getDrawable(R.mipmap.ic_unfavourite));
            isFavourite = false;
        }
    }

    @Override
    public void onClick(Trailer movie) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + movie.getKey()));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(Constants.YOUTUBE_VIDEO_URL + movie.getKey()));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }

    @Override
    public void onClick(View v) {
        if (movie.isFavourite()) {
            img_fav.setImageDrawable(getResources().getDrawable(R.mipmap.ic_unfavourite));
            movie.setFavourite(false);
        } else {
            img_fav.setImageDrawable(getResources().getDrawable(R.mipmap.ic_favourite));
           movie.setFavourite(true);
        }
        viewmodel.setFavourte(movie);
    }
}
