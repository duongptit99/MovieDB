package com.example.moviedb.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviedb.reponsitory.Repository;
import com.example.moviedb.model.MovieResponse;
import com.example.moviedb.model.SimilarResponse;
import com.example.moviedb.model.TrailerResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

public class DetailViewModel extends ViewModel {
    private Repository repository = new Repository();
    private MutableLiveData<Object> objectMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<MovieResponse.Movie> detailMovie = new MutableLiveData<>();
    private MutableLiveData<TrailerResponse> detailVideo = new MutableLiveData<>();
    private MutableLiveData<SimilarResponse> detailSimilar = new MutableLiveData<>();

    public LiveData<MovieResponse.Movie> fetchDetailMovie(int movieID){
        repository.getDetail(movieID).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    detailMovie.setValue((MovieResponse.Movie) o);
                });
        return detailMovie;
    }

    public LiveData<TrailerResponse> fetchDetailVideo(int movieID){
        repository.getVideo(movieID).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    detailVideo.setValue((TrailerResponse) o);
                });
        return detailVideo;
    }

    public LiveData<SimilarResponse> fetchDetailSimilar(int movieID){
        repository.getSimilar(movieID).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    detailSimilar.setValue((SimilarResponse) o);
                });
        return detailSimilar;
    }
//    zip
//    public MutableLiveData<MovieResponse.Movie> mergeData(int moviID){
//        Observable<MovieResponse.Movie> movieObservable = repository.getDetail(moviID);
//        Observable<TrailerResponse> trailerObservable = repository.getVideo(moviID);
//        Observable<SimilarResponse> similarObservable = repository.getSimilar(moviID);
//
//        Observable.zip(movieObservable, trailerObservable, similarObservable
//                ,new Function3<MovieResponse.Movie, TrailerResponse, SimilarResponse, Object>() {
//                    @NonNull
//                    @Override
//                    public Object apply(@NonNull MovieResponse.Movie movie
//                            , @NonNull TrailerResponse trailerResponse
//                            , @NonNull SimilarResponse similarResponse) throws Exception {
//
//
//                        return objectMutableLiveData.setValue(movie);
//                    }
//                });
//
//    }

}
