package com.example.popular_library_hw3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ViewModel extends androidx.lifecycle.ViewModel {
    public static final String PACH = "/data/data/com.example.popular_library_hw3/";
    Model model;
    Bitmap bitmap;
    FileOutputStream fos;
    File file;

    MutableLiveData<Bitmap> liveDatabitmap1 = new MutableLiveData<>();
    MutableLiveData<Bitmap> liveDatabitmap2 = new MutableLiveData<>();

    public ViewModel(){
        model = new Model(this);
        model.loadImage();
    }



    public void convertObservable(){
        Observable<Bitmap> observable = Observable.just(bitmap = BitmapFactory.decodeFile("/data/data/com.example.popular_library_hw3/img.jpg"))
                .subscribeOn(Schedulers.io());
        Observer<Bitmap> observer = new Observer<Bitmap>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Bitmap bitmap) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                file = new File(PACH, "img.png");
                try {
                    fos = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                liveDatabitmap2.postValue(BitmapFactory.decodeFile("/data/data/com.example.popular_library_hw3/img.png"));
            }

            @Override
            public void onError(Throwable e) {
                Log.d("muLog", "onError");
            }

            @Override
            public void onComplete() {
                Log.d("myLog", "onComplete");

            }
        };
        observable.subscribe(observer);
    }

    public MutableLiveData<Bitmap> getLiveDatabitmap1() {
        return liveDatabitmap1;
    }

    public MutableLiveData<Bitmap> getLiveDatabitmap2() {
        return liveDatabitmap2;
    }
}
