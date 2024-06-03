package com.example.lab5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lab5.databinding.ActivityB1Binding;

import java.util.ArrayList;

public class B1Activity extends AppCompatActivity {
    private ActivityB1Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityB1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("duykhanh", "Đổ Duy Khanh", "duykhanhvip28@gmail.com"));
        userList.add(new User("duykhanh", "Đổ Duy Khanh", "duykhanhvip28@gmail.com"));
        userList.add(new User("duykhanh", "Đổ Duy Khanh", "duykhanhvip28@gmail.com"));
        userList.add(new User("duykhanh", "Đổ Duy Khanh", "duykhanhvip28@gmail.com"));
        userList.add(new User("duykhanh", "Đổ Duy Khanh", "duykhanhvip28@gmail.com"));
        userList.add(new User("duykhanh", "Đổ Duy Khanh", "duykhanhvip28@gmail.com"));
        userList.add(new User("duykhanh", "Đổ Duy Khanh", "duykhanhvip28@gmail.com"));
        userList.add(new User("duykhanh", "Đổ Duy Khanh", "duykhanhvip28@gmail.com"));
        userList.add(new User("duykhanh", "Đổ Duy Khanh", "duykhanhvip28@gmail.com"));
        UserAdaper userAdaper = new UserAdaper(userList);
        binding.rv1.setAdapter(userAdaper);
        binding.rv1.setLayoutManager(new LinearLayoutManager(this));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}