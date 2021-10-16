package com.example.moviedb.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviedb.reponsitory.Repository;
import com.example.moviedb.model.MovieResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NowPlayingViewModel extends ViewModel {

    private final Repository repository = new Repository();

    private MutableLiveData<MovieResponse> nowPlaying = new MutableLiveData(null);

    public MutableLiveData getNowPlaying() {
        return nowPlaying;
    }

    public void fetchNowPlaying() {
        repository.getNowPlaying().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        nowPlaying.setValue((MovieResponse) o);
                    }
                });
    }
}
