package jp.ac.meijou.android.s221205143;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import jp.ac.meijou.android.s221205143.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private  PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefDataStore = PrefDataStore.getInstance(this);

        prefDataStore.getString("name").ifPresent(name -> binding.text.setText(name));

        binding.saveButton.setOnClickListener(View -> {
            var text = binding.editText.getText().toString();
            prefDataStore.setString("name", text);
            Log.d("futagami", "save: "+ text);
        });

        Log.d("futagami", "onCreate text: "+binding.text.getText());

        binding.buttonText.setOnClickListener(view -> {
            var text = binding.editText.getText().toString();
            binding.text.setText(text);
        });


        binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.text.setText(editable.toString());
            }
        });

//        binding.buttonImage.setOnClickListener(view -> {
////            binding.imageView.setImageResource(R.drawable.replay_10);
////        });
        //binding.imageView.setImageResource(R.drawable.replay_10);
    }

    protected void onStart() {
        super.onStart();
        prefDataStore.getString("name")
                .ifPresent(name -> binding.text.setText(name));
    }
}