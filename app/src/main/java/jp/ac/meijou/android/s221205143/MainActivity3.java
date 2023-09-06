package jp.ac.meijou.android.s221205143;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.Optional;

import jp.ac.meijou.android.s221205143.databinding.ActivityMain2Binding;
import jp.ac.meijou.android.s221205143.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;

    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK:
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret"))
                                .map(text -> "Result:" + text)
                                .ifPresent(text -> binding.textView2.setText(text));
                        break;
                    case RESULT_CANCELED:
                        binding.textView2.setText("Result: Canceled");
                        break;
                    default:
                        binding.textView2.setText("Result: Unknown(" + result.getResultCode() + ")");
                        break;
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonExplicit.setOnClickListener(view -> {
            var intent = new Intent(this, SubActivity.class);
            startActivity(intent);
        });

        binding.buttonImplicit.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });

        binding.buttonSend.setOnClickListener(view -> {
            var intent = new Intent(this, SubActivity.class);
            intent.putExtra("text", binding.editText.getText().toString());
            startActivity(intent);
        });

        binding.button5.setOnClickListener(view -> {
            var intent = new Intent(this, SubActivity.class);
            getActivityResult.launch(intent);
        });
    }
}