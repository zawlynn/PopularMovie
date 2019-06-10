package com.zawlynn.udacity.popularmovie.constants;

public class Constants {
    public static final String DATA = "data";
    private static String BASE_URL = "https://api.themoviedb.org/3/";
    public static String API_KEY = "cc459211c0d5f88fe3da30b7e9643115";
    public static String BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w185";
    public static String BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780";
    public static String YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v=";
    public static String YOUTUBE_THUMBNAIL_URL = "https://img.youtube.com/vi/";
    public static String POPULAR=BASE_URL+"movie/popular";
    public static String TOP_RATED=BASE_URL+"movie/top_rated";
    public static String VIDEO_TEASER=BASE_URL+"movie/%d/videos";
    public static String REVIEW=BASE_URL+"movie/%d/reviews";
    public static String THUMB_MQ="/mqdefault.jpg";
    public static String THUMB_HQ="/hqdefault.jpg";
}
