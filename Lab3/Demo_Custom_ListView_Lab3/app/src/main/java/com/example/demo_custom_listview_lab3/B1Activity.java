package com.example.demo_custom_listview_lab3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demo_custom_listview_lab3.databinding.ActivityB1Binding;
import java.util.ArrayList;
import java.util.List;

public class B1Activity extends AppCompatActivity {
    List<String> data ;
    private ActivityB1Binding binding;
    private int editPos = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityB1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        data = new ArrayList<>();
        data.add("Android");
        data.add("PHP");
        data.add("IOS");
        data.add("Unity");
        data.add("ASP.NET");
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        binding.lvb1.setAdapter(adapter);

        binding.btnAdd.setOnClickListener(v -> {
            String text = binding.editText.getText().toString();
            if(text.trim().length()>0){
                data.add(text);
                adapter.notifyDataSetChanged();
            }
        });

        binding.btnDelete.setOnClickListener(v -> {
            if(editPos>=0 && editPos<data.size()){
                data.remove(editPos);
                binding.editText.setText("");
                adapter.notifyDataSetChanged();
                editPos = -1;
            }
        });
        binding.lvb1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editPos = position;
                binding.editText.setText(data.get(position));
            }
        });

        binding.lvb1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                data.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        binding.btnUpdate.setOnClickListener(v -> {
            if(editPos>=0 && editPos<data.size()){
                data.set(editPos,binding.editText.getText().toString());
                binding.editText.setText("");
                adapter.notifyDataSetChanged();
                editPos = -1;
            }
        });

        binding.btnAdd.setOnClickListener(v -> {
            String text = binding.editText.getText().toString();
            if(text.trim().length()>0){
                data.add(text);
                adapter.notifyDataSetChanged();
                binding.editText.setText("");
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}