package jp.ac.meijou.android.s221205143;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Optional;

import jp.ac.meijou.android.s221205143.databinding.ActivityMainBinding;
import jp.ac.meijou.android.s221205143.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonOk.setOnClickListener(view -> {
            var intent = new Intent();
            intent.putExtra("ret", "maijo");
            setResult(RESULT_OK, intent);
            finish();
        });

        binding.buttonCancel.setOnClickListener(View -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}