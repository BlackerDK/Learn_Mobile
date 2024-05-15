package com.example.demolab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtNoiDung;
    Button btnClick;
    TextView txtMin;
    TextView txtMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Ánh xạ
        txtNoiDung = (TextView) findViewById(R.id.textviewNoiDung);
        txtMin = (TextView) findViewById(R.id.txtMin);
        txtMax = (TextView) findViewById(R.id.txtMax);
        btnClick =(Button) findViewById(R.id.buttonClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int Min = Integer.parseInt(txtMin.getText().toString());
                int Max = Integer.parseInt(txtMax.getText().toString());
                int number = random.nextInt(Max - Min + 1)+ Min;
                TextView TxtNoiDung= (TextView) findViewById(R.id.textviewNoiDung);
                txtNoiDung.setText("Result:  "+number);
            }
        });

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}