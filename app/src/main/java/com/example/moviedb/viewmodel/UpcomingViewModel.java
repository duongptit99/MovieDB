package com.example.moviedb.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviedb.reponsitory.Repository;
import com.example.moviedb.model.MovieResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UpcomingViewModel extends ViewModel {

    private final Repository repository = new Repository();

    private MutableLiveData<MovieResponse> upcoming = new MutableLiveData<>();

    public MutableLiveData getUpcoming(){return upcoming;}

    public void fetchUpcoming(){
        repository.getUpcoming().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(o -> {
                    upcoming.setValue((MovieResponse) o);
                });

    }
}
