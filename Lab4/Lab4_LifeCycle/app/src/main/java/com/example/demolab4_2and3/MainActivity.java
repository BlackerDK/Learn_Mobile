package com.example.demolab4_2and3;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demolab4_2and3.databinding.ActivityMainBinding;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //

        binding.btnSendString.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("sendString", "This is string data");
            startActivity(intent);
        });
        binding.btnSendInt.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("sendInt", 1000);
            startActivity(intent);
        });
        binding.btnSendObject.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("sendObject", new Person("long", 50, "Đinh quán, Đồng Nai"));
            startActivity(intent);
        });
        String[] arr = new String[]{"string 1","string 2", "string 3", "string 4"};
        binding.btnSendArray.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("sendArr",arr);
            startActivity(intent);
        });
        binding.btnSendBundle.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("sendObject", new Person("long", 50, "Đinh quán, Đồng Nai"));
            bundle.putInt("sendInt", 1000);
            bundle.putString("sendString", "this is string data");
            bundle.putStringArray("sendArr", arr);
            intent.putExtra("sendBundle",bundle);
            startActivity(intent);
        });
        Log.i(TAG, "Call onCreate()");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "Call onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Call onDestroy()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Call onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Call onPause()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Call onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Call onStop()");
    }
}