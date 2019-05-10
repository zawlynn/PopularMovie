package com.zawlynn.udacity.popularmovie.ui.detail;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.widget.ImageView;
import android.widget.TextView;

import com.zawlynn.udacity.popularmovie.R;
import com.zawlynn.udacity.popularmovie.constants.Constants;
import com.zawlynn.udacity.popularmovie.model.Movie;
import com.zawlynn.udacity.popularmovie.utils.CommonUtils;
import com.zawlynn.udacity.popularmovie.utils.GlideApp;

import java.util.Objects;

public class DetailMovieActivity extends AppCompatActivity {
    private static final String TAG = "DetailMovieActivity";
    TextView tv_release_date;
    TextView tv_rating;
    TextView tv_overview;
    TextView tv_title;
    ImageView movie_detail_poster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Movie movie = Objects.requireNonNull(getIntent().getExtras()).getParcelable(Constants.DATA);
        initUI(movie);
    }
    private void initUI(Movie movie){
        tv_rating=findViewById(R.id.tv_rating);
        tv_overview=findViewById(R.id.tv_overview);
        tv_release_date=findViewById(R.id.tv_release_date);
        tv_title=findViewById(R.id.tv_title);
        movie_detail_poster=findViewById(R.id.movie_detail_poster);
        String url = CommonUtils.getInstance().getMoviePoster(movie.getPoster_path());
        GlideApp.with(DetailMovieActivity.this)
                .load(url)
                .into(movie_detail_poster);

        tv_release_date.setText(movie.getRelease_date());
        tv_overview.setText(movie.getOverview());
        tv_rating.setText(movie.getVote_average()+"/10");
        tv_title.setText(movie.getTitle());
    }
}
