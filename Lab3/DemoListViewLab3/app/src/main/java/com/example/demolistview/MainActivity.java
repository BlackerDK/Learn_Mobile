package com.example.demolistview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ListView listV;
Button btnAdd;
Button btnUpdate;
EditText edIput;
private int selectedItemPosition = -1;
ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        listV=(ListView) findViewById(R.id.listview);
        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnUpdate=(Button) findViewById(R.id.btnUpdate);
        edIput=(EditText) findViewById(R.id.ed_input);
        arrayList = new ArrayList<>();
        arrayList.add("Android");
        arrayList.add("C#");
        arrayList.add("Java");
        arrayList.add("Laptop");
        ArrayAdapter adapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayList
        );
        listV.setAdapter(adapter);
        btnAdd.setOnClickListener( view -> {
            String item = edIput.getText().toString();
            if (!item.isEmpty()) {
                arrayList.add(item);
                adapter.notifyDataSetChanged();
                edIput.setText("");
            }
        });
        listV.setOnItemClickListener((parent, view, position, id) -> {
            selectedItemPosition = position;
            String selectedItem = arrayList.get(position);
            edIput.setText(selectedItem);
        });

        btnUpdate.setOnClickListener(view -> {
            String updatedItem = edIput.getText().toString();
            if (selectedItemPosition != -1 && !updatedItem.isEmpty()) {
                arrayList.set(selectedItemPosition, updatedItem);
                adapter.notifyDataSetChanged();
                edIput.setText("");
                selectedItemPosition = -1;
            } else {
                Toast.makeText(MainActivity.this, "Chọn một mục và nhập nội dung để cập nhật", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btnDelete).setOnClickListener(view -> {
            if (selectedItemPosition != -1) {
                arrayList.remove(selectedItemPosition);
                adapter.notifyDataSetChanged();
                edIput.setText("");
                selectedItemPosition = -1;
                Toast.makeText(MainActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Chọn một mục để xóa", Toast.LENGTH_SHORT).show();
            }
        });
    }
}