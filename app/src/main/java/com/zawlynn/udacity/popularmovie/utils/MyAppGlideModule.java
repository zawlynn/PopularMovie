package com.zawlynn.udacity.popularmovie.utils;

/**
 * Created by Joseph Lim on 11/14/2017.
 */

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

@GlideModule
public final class MyAppGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(@NonNull Context context, GlideBuilder builder) {
        builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_RGB_565));
    }
}