package com.example.moviedb.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.moviedb.R;
import com.example.moviedb.adapter.ViewPagerAdapter;
import com.example.moviedb.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        binding.viewpager.setAdapter(viewPagerAdapter);
        binding.viewpager.setOffscreenPageLimit(3);
        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        binding.bottonNavigation.getMenu().findItem(R.id.menu_now_playing).setChecked(true);
                        break;
                    case 1:
                        binding.bottonNavigation.getMenu().findItem(R.id.menu_top_rated).setChecked(true);
                        break;
                    case 2:
                        binding.bottonNavigation.getMenu().findItem(R.id.menu_popular).setChecked(true);
                        break;
                    case 3:
                        binding.bottonNavigation.getMenu().findItem(R.id.menu_upcoming).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.bottonNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_now_playing:
                    binding.viewpager.setCurrentItem(0);
                    break;
                case R.id.menu_top_rated:
                    binding.viewpager.setCurrentItem(1);
                    break;
                case R.id.menu_popular:
                    binding.viewpager.setCurrentItem(2);
                    break;
                case R.id.menu_upcoming:
                    binding.viewpager.setCurrentItem(3);
                    break;
            }
            return true;
        });

        searchView();
    }

    private void searchView() {
    }

}