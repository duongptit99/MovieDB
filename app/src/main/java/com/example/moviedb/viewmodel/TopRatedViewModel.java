package com.example.moviedb.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviedb.reponsitory.Repository;
import com.example.moviedb.model.MovieResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TopRatedViewModel extends ViewModel {
    private final Repository repository = new Repository();

    private MutableLiveData<MovieResponse> topRated = new MutableLiveData<>();

    public MutableLiveData getTopRated(){
        return topRated;
    }

    public void fetchTopRated(){
        repository.getTopRated().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(o -> topRated.setValue((MovieResponse) o));
    }
}
