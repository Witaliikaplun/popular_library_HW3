package com.example.popular_library_hw3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ViewModel {
    public static final String PACH = "/data/data/com.example.popular_library_hw3/";
    Model model;
    Bitmap bitmap;
    FileOutputStream fos;
    File file;

    public ViewModel(){
        model = new Model(this);
        model.loadImage();
    }

    public void convertObservable(){
        Observable<Bitmap> observable = Observable.just(bitmap = BitmapFactory.decodeFile("/data/data/com.example.popular_library_hw3/img.jpg"));
        Observer<Bitmap> observer = new Observer<Bitmap>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Bitmap bitmap) {
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

}
