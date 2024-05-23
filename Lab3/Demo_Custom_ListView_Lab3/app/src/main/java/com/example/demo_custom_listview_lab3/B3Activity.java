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

import com.example.demo_custom_listview_lab3.databinding.ActivityB3Binding;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;
import java.util.List;

public class B3Activity extends AppCompatActivity {

    private ActivityB3Binding binding;
    private static final int PICK_IMAGE = 1;
    private static final int PICK_IMAGE_FLAG = 2;
    private List<Foodball> datas;
    private int editPos = -1;
    Uri selectedImage = null;
    Uri selectedFlag = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityB3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        datas = new ArrayList<>();
        datas.add(new Foodball("L. Messi","vị trí tiền đạo, sinh 24/06/1987 (37 tuổi)", R.drawable.messi,R.drawable.flag_of_argentina));
        datas.add(new Foodball("Cristiano Ronaldo","vị trí tiền đạo, sinh 05/02/1985 (39 tuổi)", R.drawable.cristiano_ronaldo,R.drawable.flag_of_portugal));
        datas.add(new Foodball("Neymar","vị trí tiền đạo, sinh 05/02/1992 (32 tuổi)", R.drawable.neymar,R.drawable.flag_of_brazil));
        datas.add(new Foodball("Bruno Fernandes","vị trí Tiền vệ, sinh 08/09/1994 (30 tuổi)", R.drawable.bruno_fernandes,R.drawable.flag_of_portugal));
        
        FootBallAdapter footballAdapter = new FootBallAdapter(datas, R.layout.football_layout,this);
        binding.lvb2.setAdapter(footballAdapter);

        setDefaultState();
        binding.ivSelectImg.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });
        binding.ivSelectFlag2.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE_FLAG);
        });
        binding.btnB2Update.setOnClickListener(v-> {
            if(editPos>=0 && editPos<datas.size()){
                Foodball fu = datas.get(editPos);
                fu.setName(binding.etTitle.getText().toString());
                fu.setDes(binding.etDes.getText().toString());
                if(selectedImage!=null){
                    fu.setAvatart(selectedImage);
                }
                if(selectedFlag!=null){
                    fu.setFlag(selectedFlag);
                }
                datas.set(editPos, fu);
                footballAdapter.notifyDataSetChanged();
                setDefaultState();
            }
        });
        binding.btnB2Delete.setOnClickListener(v-> {
            if(editPos>=0 && editPos<datas.size()){
                datas.remove(editPos);
                footballAdapter.notifyDataSetChanged();
                setDefaultState();
            }
        });

        binding.lvb2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                datas.remove(position);
                footballAdapter.notifyDataSetChanged();
                return false;
            }
        });

        binding.lvb2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Foodball f = datas.get(position);
                binding.etTitle.setText(f.getName());
                binding.etDes.setText(f.getDes());
                if(f.getAvatart() instanceof Integer){
                    binding.ivSelectImg.setImageResource((Integer) f.getAvatart());
                }else{
                    binding.ivSelectImg.setImageURI((Uri) f.getAvatart());
                }

                if(f.getFlag() instanceof Integer){
                    binding.ivSelectFlag2.setImageResource((Integer) f.getFlag());
                }else{
                    binding.ivSelectFlag2.setImageURI((Uri) f.getFlag());
                }

                editPos = position;
            }
        });

        binding.btnB2Add.setOnClickListener(v -> {
            String title = binding.etTitle.getText().toString();
            String des = binding.etDes.getText().toString();
            if(title.trim().length()>0&&des.trim().length()>0){
                Foodball f = new Foodball(title,des,null,null);
                if(selectedImage!=null){
                    f.setAvatart(selectedImage);
                }else if(editPos != -1){
                    if(editPos>=0&& editPos<datas.size()){
                        Foodball temp = datas.get(editPos);
                       f.setAvatart(temp.getAvatart());
                    }
                }else {
                    showMessage("Vui lòng chọn ảnh");
                    return;
                }

                if(selectedFlag!=null){
                    f.setFlag(selectedFlag);
                }else if(editPos != -1){
                    if(editPos>=0&& editPos<datas.size()){
                        Foodball temp = datas.get(editPos);
                        f.setFlag(temp.getFlag());
                    }
                }else {
                    showMessage("Vui lòng chọn ảnh");
                    return;
                }
                datas.add(f);
                footballAdapter.notifyDataSetChanged();
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
        binding.ivSelectFlag2.setImageResource(R.drawable.img_empty);
        selectedImage = null;
        selectedFlag = null;
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
        }else if (requestCode == PICK_IMAGE_FLAG && resultCode == RESULT_OK && data != null) {
            selectedFlag = data.getData();
            binding.ivSelectFlag2.setImageURI(selectedFlag);
        }
    }
}