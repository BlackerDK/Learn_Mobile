package com.example.demo_custom_listview_lab3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demo_custom_listview_lab3.databinding.ActivityB2Binding;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

public class B2Activity extends AppCompatActivity {
    private ActivityB2Binding binding;
    private static final int PICK_IMAGE = 1;
    private List<Fruit> datas;
    private int editPos = -1;
    Uri selectedImage = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityB2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        datas = new ArrayList<>();
        Fruit f1 = new Fruit("Chuối", "Chuối một nải");
        f1.setImg(R.drawable.chuoi );
        datas.add(f1);
        Fruit f2 = new Fruit("Chanh", "Chanh siêu ngọt");
        f2.setImg(R.drawable.chanh);
        datas.add(f2);
        Fruit f3 = new Fruit("Cam", "Cam nguyên quả");
        f3.setImg(R.drawable.cam);
        datas.add(f3);
        Fruit f4 = new Fruit("Khế", "Khế siêu ngon");
        f4.setImg(R.drawable.khe );
        datas.add(f4);
        FruitAdapter fruitAdapter = new FruitAdapter(datas, R.layout.fruit_layout,this);
        binding.lvb2.setAdapter(fruitAdapter);

        setDefaultState();
        binding.ivSelectImg.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });
        binding.btnB2Update.setOnClickListener(v-> {
            if(editPos>=0 && editPos<datas.size()){
                Fruit fu = datas.get(editPos);
                fu.setName(binding.etTitle.getText().toString());
                fu.setDes(binding.etDes.getText().toString());
                if(selectedImage!=null){
                    fu.setImg(selectedImage);
                }
                datas.set(editPos, fu);
                fruitAdapter.notifyDataSetChanged();
                setDefaultState();
            }
        });
        binding.btnB2Delete.setOnClickListener(v-> {
            if(editPos>=0 && editPos<datas.size()){
                datas.remove(editPos);
                fruitAdapter.notifyDataSetChanged();
                setDefaultState();
            }
        });
        binding.lvb2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                datas.remove(position);
                fruitAdapter.notifyDataSetChanged();
                return false;
            }
        });

        binding.lvb2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit f = datas.get(position);
                binding.etTitle.setText(f.getName());
               binding.etDes.setText(f.getDes());
               if(f.getImageType() == ImageType.RESOURCE){
                   binding.ivSelectImg.setImageResource(f.getImg());
               }else{
                   binding.ivSelectImg.setImageURI(f.getUri());
               }

               editPos = position;
            }
        });

        binding.btnB2Add.setOnClickListener(v -> {
            String title = binding.etTitle.getText().toString();
            String des = binding.etDes.getText().toString();
            if(title.trim().length()>0&&des.trim().length()>0){
                Fruit f = new Fruit(title,des);
                if(selectedImage!=null){
                    f.setImg(selectedImage);
                }else if(editPos != -1){
                    if(editPos>=0&& editPos<datas.size()){
                        Fruit temp = datas.get(editPos);
                        if(temp.getImageType()==ImageType.RESOURCE){
                            f.setImg(temp.getImg());
                        }else{
                            f.setImg(temp.getUri());
                        }
                    }
                }else {

                    showMessage("Vui lòng chọn ảnh");
                    return;
                }
                datas.add(f);
                fruitAdapter.notifyDataSetChanged();
                setDefaultState();
            }else {
                showMessage("Vui lòng điển đầy đủ các trường");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setDefaultState(){
        binding.ivSelectImg.setImageResource(R.drawable.img_empty);
        selectedImage = null;
        binding.etDes.setText("");
        binding.etTitle.setText("");
        editPos=-1;
    }

    private void showMessage(String message){
        Snackbar snackbar = Snackbar.make(binding.btnB2Add,message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
             selectedImage = data.getData();
            binding.ivSelectImg.setImageURI(selectedImage);
        }
    }
}