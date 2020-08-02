package com.example.popular_library_hw3;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ViewModel viewModel;
    DialogBuilderFragment dialogBuilderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //создание ссылки на viewmodel---------------
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
         dialogBuilderFragment = new DialogBuilderFragment(viewModel);

        ImageView iv1 = findViewById(R.id.iv1);
        ImageView iv2 = findViewById(R.id.iv2);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        viewModel.getLiveDatabitmap1().observe(this, bitmap -> iv1.setImageBitmap(bitmap));

        viewModel.getLiveDatabitmap2().observe(this, bitmap -> iv2.setImageBitmap(bitmap));

        btn1.setOnClickListener(view -> viewModel.convertObservable());

        btn2.setOnClickListener(view -> dialogBuilderFragment.show(this.getSupportFragmentManager(),
                "dialogCustom"));

    }
}