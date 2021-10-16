package com.example.moviedb.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.moviedb.fragment.NowPlayingFragment;
import com.example.moviedb.fragment.PopularFragment;
import com.example.moviedb.fragment.TopRatedFragment;
import com.example.moviedb.fragment.UpcomingFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new NowPlayingFragment();
            case 1:
                return new TopRatedFragment();
            case 2:
                return new PopularFragment();
            case 3:
                return new UpcomingFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "NowPLaying";
                break;
            case 1:
                title = "TopRated";
                break;
            case 2:
                title = "Popular";
                break;
            case 3:
                title = "Upcoming";
                break;
        }
        return title;
    }


}
