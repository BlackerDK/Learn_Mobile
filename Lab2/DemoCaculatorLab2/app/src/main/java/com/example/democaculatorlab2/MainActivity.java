package com.example.democaculatorlab2;

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
    Button btnCong;
    Button btnTru;
    Button btnNhan;
    Button btnChia;
    TextView txtNumber1;
    TextView txtNumber2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Ánh xạ
        txtNoiDung = (TextView) findViewById(R.id.textviewNoiDung);
        txtNumber1 = (TextView) findViewById(R.id.txtNumber1);
        txtNumber2 = (TextView) findViewById(R.id.txtNumber2);
        btnCong =(Button) findViewById(R.id.buttonCong);
        btnTru =(Button) findViewById(R.id.buttonTru);
        btnChia =(Button) findViewById(R.id.buttonChia);
        btnNhan =(Button) findViewById(R.id.buttonNhan);
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNumber1.getText().toString().isEmpty() || txtNumber2.getText().toString().isEmpty()){
                    txtNoiDung= (TextView) findViewById(R.id.textviewNoiDung);
                    txtNoiDung.setText("Phải nhập 2 số");
                }else{
                    int Number1 = Integer.parseInt(txtNumber1.getText().toString());
                    int Number2 = Integer.parseInt(txtNumber2.getText().toString());
                    int Result = Number1 + Number2;
                    txtNoiDung= (TextView) findViewById(R.id.textviewNoiDung);
                    txtNoiDung.setText("" +  Result);
                }
            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNumber1.getText().toString().isEmpty() || txtNumber2.getText().toString().isEmpty()){
                    txtNoiDung= (TextView) findViewById(R.id.textviewNoiDung);
                    txtNoiDung.setText("Phải nhập 2 số");
                }else{
                    int Number1 = Integer.parseInt(txtNumber1.getText().toString());
                    int Number2 = Integer.parseInt(txtNumber2.getText().toString());
                    int Result = Number1 - Number2;
                    txtNoiDung= (TextView) findViewById(R.id.textviewNoiDung);
                    txtNoiDung.setText("" +  Result);
                }
            }
        });
        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNumber1.getText().toString().isEmpty() || txtNumber2.getText().toString().isEmpty()){
                    txtNoiDung= (TextView) findViewById(R.id.textviewNoiDung);
                    txtNoiDung.setText("Phải nhập 2 số");
                }else{
                    double Number1 = Double.parseDouble(txtNumber1.getText().toString());
                    double Number2 = Double.parseDouble(txtNumber2.getText().toString());
                    double Result = Number1 / Number2;
                    if(Number1 == 0){
                        txtNoiDung= (TextView) findViewById(R.id.textviewNoiDung);
                        txtNoiDung.setText("Number1 phải lớn hơn 0 ");
                    }else{
                        txtNoiDung= (TextView) findViewById(R.id.textviewNoiDung);
                        txtNoiDung.setText("" +  Result);
                    }
                }
            }
        });
        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNumber1.getText().toString().isEmpty() || txtNumber2.getText().toString().isEmpty()){
                    txtNoiDung= (TextView) findViewById(R.id.textviewNoiDung);
                    txtNoiDung.setText("Phải nhập 2 số");
                }else{
                int Number1 = Integer.parseInt(txtNumber1.getText().toString());
                int Number2 = Integer.parseInt(txtNumber2.getText().toString());
                int Result = Number1 * Number2;
                txtNoiDung= (TextView) findViewById(R.id.textviewNoiDung);
                txtNoiDung.setText("" +  Result);
                }
            }
        });
    }
}