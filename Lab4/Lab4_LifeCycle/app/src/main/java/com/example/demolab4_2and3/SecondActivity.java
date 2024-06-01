package com.example.demolab4_2and3;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.se161734.lap4_senddataactivity.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //

        Intent intent  = getIntent();
        if(intent.hasExtra("sendString")){
            binding.tvStringData.setText(intent.getStringExtra("sendString"));
        }
        if(intent.hasExtra("sendInt")){
            binding.tvIntData.setText(intent.getIntExtra("sendInt",10)+"");
        }
        if(intent.hasExtra("sendObject")){
            binding.tvObjectData.setText((intent.getSerializableExtra("sendObject")).toString());
        }
        if(intent.hasExtra("sendArr")){
            String[] arr = intent.getStringArrayExtra("sendArr");
            binding.tvArrData.setText(arr[0]);
        }


        if(intent.hasExtra("sendBundle")){
            Bundle bundle = intent.getBundleExtra("sendBundle");
            binding.tvStringData.setText(bundle.getString("sendString"));
            binding.tvIntData.setText(bundle.getInt("sendInt",10)+"");
            binding.tvObjectData.setText((bundle.getSerializable("sendObject")).toString());
            String[] arr = bundle.getStringArray("sendArr");
            binding.tvArrData.setText(arr[0]);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}