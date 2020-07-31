package com.example.popular_library_hw3;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Model {
    public static final String PATH = "https://images.unsplash.com/photo-1567924675637-283a6742993e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2990&q=80";
    public static final String PACH2 = "/data/data/com.example.popular_library_hw3/";
    private Target target;
    FileOutputStream fos;
    File file;
    ViewModel viewModel;

    public Model(ViewModel viewModel) {
        this.viewModel = viewModel;
        file = new File(PACH2, "img.jpg");
    }

    public void loadImage() {
        if (!file.exists()) {
            try {
                fos = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            };
            Picasso.get().load(PATH).into(target);

        }
        viewModel.convertObservable();
    }
}
