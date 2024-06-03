package com.example.lab5;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lab5.databinding.ActivityBai2Binding;
import com.example.lab5.databinding.EditDialogBinding;

import java.util.ArrayList;
import java.util.List;

public class Bai2Activity extends AppCompatActivity {
    private ActivityBai2Binding binding;
    private EditDialogBinding dialogBinding;
    private Dialog dialog;

    private List<Course> courses;
    private  CourseAdapter courseAdapter;

    private final int IMG_BANNER_FLAG = 1;
    private final int IMG_ICON_FLAG = 2;

    private Uri selectedBanner;
    private Uri selectedIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityBai2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //
        dialogBinding = EditDialogBinding.inflate(getLayoutInflater());
        dialog = new Dialog(this);
        dialog.setContentView(dialogBinding.getRoot());
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(Resources.getSystem().getDisplayMetrics().widthPixels, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getColor(R.color.zxing_transparent)));
        dialogBinding.btnQuit.setOnClickListener(v -> dialog.dismiss());
        dialogBinding.ivSelectBanner.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, IMG_BANNER_FLAG);
        });
        dialogBinding.ivSelectIcon.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, IMG_ICON_FLAG);
        });
        resetDialog();
        //
        courses = new ArrayList<>();
        courses.add(new Course(R.drawable.recycle_view,"Recycle View","Khóa học recycle view căn bản sẽ giúp bạn học được các tạo và sử dụng recycle view trong android",R.drawable.android_icon));
        courses.add(new Course(R.drawable.swift_ui,"Swift UI Căn bản","Khóa học swift ui căn bản sẽ giúp bạn học được các tạo các ui trong ios",R.drawable.ios_icon));
        courses.add(new Course(R.drawable.fragment,"Fragment trong android?","Khóa học Fragment sẽ giúp bạn học được các tạo và sử dụng Fragment trong android",R.drawable.android_icon));
        courseAdapter = new CourseAdapter(courses);
        courseAdapter.setOnLongClickListener((item,index) -> {
            showDialog((Course) item, index);

        });
        binding.rv2.setAdapter(courseAdapter);
        binding.rv2.setLayoutManager(new LinearLayoutManager(this));
        binding.tvAdd.setOnClickListener(v -> showDialog());
        //

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private  void showDialog(Course c, int index){
        dialogBinding.etTitle.setText(c.getTitle());
        dialogBinding.etDes.setText(c.getDes());
        dialogBinding.btnDelete.setVisibility(View.VISIBLE);
        if(c.getIcon() instanceof Uri){
            dialogBinding.ivSelectIcon.setImageURI((Uri) c.getIcon());
        }else if(c.getIcon() instanceof Integer){
            dialogBinding.ivSelectIcon.setImageResource((Integer) c.getIcon());
        }

        if(c.getImg() instanceof Uri ){
            dialogBinding.ivSelectBanner.setImageURI((Uri) c.getImg());
        }else if(c.getImg() instanceof Integer){
            dialogBinding.ivSelectBanner.setImageResource((Integer) c.getImg());
        }
        dialogBinding.btnDelete.setOnClickListener(v -> {
            courses.remove(c);
            courseAdapter.notifyDataSetChanged();
            dialog.dismiss();
            resetDialog();
        });
        dialogBinding.btnSave.setOnClickListener(v -> {
            Course course = new Course();
            course.setImg(selectedBanner!=null?selectedBanner:c.getImg());
            course.setIcon(selectedIcon!=null?selectedIcon:c.getIcon());
            course.setTitle(dialogBinding.etTitle.getText().toString());
            course.setDes(dialogBinding.etDes.getText().toString());
            courses.set(index,course);
            courseAdapter.notifyDataSetChanged();
            dialog.dismiss();
            resetDialog();
        });

        dialog.show();
    }

    private void showDialog(){

        dialogBinding.btnSave.setOnClickListener(v -> {
            Course course = new Course();
            course.setImg(selectedBanner);
            course.setIcon(selectedIcon);
            course.setTitle(dialogBinding.etTitle.getText().toString());
            course.setDes(dialogBinding.etDes.getText().toString());
            courses.add(course);
            courseAdapter.notifyDataSetChanged();
            dialog.dismiss();
            resetDialog();
        });
        dialogBinding.btnDelete.setVisibility(View.INVISIBLE);
        dialog.show();
    }

    private void resetDialog(){
        dialogBinding.ivSelectBanner.setImageResource(R.drawable.empty);
        dialogBinding.ivSelectIcon.setImageResource(R.drawable.empty);
        dialogBinding.etTitle.setText("");
        dialogBinding.etDes.setText("");
        selectedIcon=null;
        selectedBanner=null;
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_BANNER_FLAG && resultCode == RESULT_OK && data != null) {
            selectedBanner = data.getData();
            dialogBinding.ivSelectBanner.setImageURI(selectedBanner);
        }else if (requestCode == IMG_ICON_FLAG && resultCode == RESULT_OK && data != null) {
            selectedIcon = data.getData();
            dialogBinding.ivSelectIcon.setImageURI(selectedIcon);
        }
    }
}