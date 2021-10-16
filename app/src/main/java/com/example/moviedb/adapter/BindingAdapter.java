package com.example.moviedb.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.example.moviedb.model.MovieResponse;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter("android:imageURL")
    public static void setImageURL(ImageView imageView, String URL){
        try {
            imageView.setAlpha(0f);
            Picasso.get().load("https://image.tmdb.org/t/p/w500"+URL).noFade().into(imageView, new Callback() {
                @Override
                public
                void onSuccess() {
                    imageView.animate().setDuration(300).alpha(1f).start();
                }

                @Override
                public
                void onError(Exception e) {

                }
            });
        }catch (Exception e){

        }
    }
    @androidx.databinding.BindingAdapter("android:text")
    public static void setFloat(TextView textView, float value){
        textView.setText(String.valueOf(value));
    }

    @androidx.databinding.BindingAdapter("android:textInt")
    public static void setInt(TextView textView, int value){
        textView.setText(String.valueOf(value));
    }
}
